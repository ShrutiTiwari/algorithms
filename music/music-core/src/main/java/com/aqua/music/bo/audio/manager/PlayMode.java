package com.aqua.music.bo.audio.manager;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.aqua.music.bo.audio.player.AudioPlayer;
import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
public enum PlayMode {
	Asynchronous {
		@Override
		public void play(Collection<? extends DynamicFrequency> frequencyList, int repeatCount) {
			executor.execute(currentPlayer().playTask(frequencyList, repeatCount));
		}

		@Override
		public void playInLoop(Collection<? extends DynamicFrequency> frequencyList) {
			executor.execute(currentPlayer().playTaskInLoop(frequencyList));

		}
	},
	Synchronous {
		@Override
		public void play(Collection<? extends DynamicFrequency> frequencyList, int repeatCount) {
			currentPlayer().playTask(frequencyList, repeatCount).run();

		}

		@Override
		public void playInLoop(Collection<? extends DynamicFrequency> frequencyList) {
			currentPlayer().playTaskInLoop(frequencyList).run();
		}
	};

	private static AudioPlayer currentPlayer() {
		return ((AudioLifeCycleManager) AudioLifeCycleManager.instance).currentAudioPlayer();
	}

	private static final ExecutorService executor = Executors.newCachedThreadPool(new AudioThreadFactory());

	abstract public void play(Collection<? extends DynamicFrequency> frequencyList, int repeatCount);

	abstract public void playInLoop(Collection<? extends DynamicFrequency> frequencyList);

	public <T> void playTask(final AudioTask<T> audioTask) {
		final AudioPlayRightsManager audioPlayRightsManager = (AudioPlayRightsManager) AudioLifeCycleManager.instance;
		Runnable audioTaskRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					audioPlayRightsManager.acquireRightToPlay();
					audioTask.beforeForLoop();

					for (T e : audioTask.forLoopParameter()) {
						if (audioPlayRightsManager.isMarkedToStopPlaying()) {
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
}