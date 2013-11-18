package com.aqua.music.bo.audio.manager;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.aqua.music.bo.audio.player.AudioPlayer;
import com.aqua.music.model.core.DynamicFrequency;

public enum PlayMode {
	Asynchronous {
		@Override
		public void play(AudioPlayer currentAudioPlayer, Collection<? extends DynamicFrequency> frequencyList) {
			setup(currentAudioPlayer);
			executor.execute(currentAudioPlayer.playTask(frequencyList));
		}

		@Override
		public void playInLoop(AudioPlayer currentAudioPlayer, Collection<? extends DynamicFrequency> frequencyList) {
			setup(currentAudioPlayer);
			executor.execute(currentAudioPlayer.playTaskInLoop(frequencyList));

		}
	},
	Synchronous {
		@Override
		public void play(AudioPlayer currentAudioPlayer, Collection<? extends DynamicFrequency> frequencyList) {
			setup(currentAudioPlayer);
			currentAudioPlayer.playTask(frequencyList).run();

		}

		@Override
		public void playInLoop(AudioPlayer currentAudioPlayer, Collection<? extends DynamicFrequency> frequencyList) {
			setup(currentAudioPlayer);
			currentAudioPlayer.playTaskInLoop(frequencyList).run();
		}
	};

	private static final ExecutorService executor = Executors.newCachedThreadPool(new AudioThreadFactory());

	private static void setup(AudioPlayer currentAudioPlayer) {
		final AudioPlayRightsManager audioPlayRightsManager = (AudioPlayRightsManager) AudioLifeCycleManager.instance;
		audioPlayRightsManager.setCurrentPlayer(currentAudioPlayer);
		currentAudioPlayer.setAudioPlayRigthsManager(audioPlayRightsManager);
	}

	abstract public void play(AudioPlayer currentAudioPlayer, Collection<? extends DynamicFrequency> frequencyList);

	abstract public void playInLoop(AudioPlayer currentAudioPlayer, Collection<? extends DynamicFrequency> frequencyList);

	public <T> void playTask(final AudioTask<T> audioTask) {
		final AudioPlayRightsManager audioPlayRightsManager = (AudioPlayRightsManager) AudioLifeCycleManager.instance;
		Runnable audioTaskRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					audioPlayRightsManager.acquireRightToPlay();
					audioTask.beforeForLoop();

					for (T e : audioTask.forLoopParameter()) {
						if (audioPlayRightsManager.stopPlaying()) {
							break;
						}
						audioTask.forLoopBody(e);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					audioPlayRightsManager.releaseRightToPlay();
				}
			}
		};
		executor.execute(audioTaskRunnable);
	}

	static class AudioThreadFactory implements ThreadFactory {
		private final AtomicInteger counter = new AtomicInteger();
		private final String factoryName;

		public AudioThreadFactory() {
			this.factoryName = "AudioFactory";
		}

		@Override
		public Thread newThread(Runnable arg0) {
			Thread thread = new Thread(arg0, factoryName + "_" + counter.getAndIncrement());
			thread.setDaemon(true);
			return thread;
		}
	}

	public static void stop() {
		try {
			AudioLifeCycleManager.instance.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}