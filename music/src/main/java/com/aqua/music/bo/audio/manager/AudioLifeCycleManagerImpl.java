package com.aqua.music.bo.audio.manager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.aqua.music.bo.audio.manager.PlayMode.DualModeManager;
import com.aqua.music.bo.audio.player.AudioPlayer;
import com.aqua.music.model.core.DynamicFrequency;

class AudioLifeCycleManagerImpl implements DualModeManager, AudioLifeCycleManager, AudioPlayRightsManager {
	private final ExecutorService executor = Executors.newCachedThreadPool(new AudioThreadFactory());
	private final Set<AudioPlayer> audioPlayerInstances = new HashSet<AudioPlayer>();
	private AudioPlayer currentAudioPlayer;

	private PlayMode currentPlayMode;

	private final Lock permitToPlay;
	private final AtomicBoolean stopCurrentPlay;

	AudioLifeCycleManagerImpl() {
		this.permitToPlay = new ReentrantLock();
		this.stopCurrentPlay = new AtomicBoolean(false);
	}

	AudioLifeCycleManagerImpl(PlayMode playMode, int durationInMsec, double vol) {
		this();
	}

	@Override
	public synchronized void acquireRightToPlay() throws InterruptedException {
		boolean acquired = permitToPlay.tryLock();
		if (!acquired) {
			logger.info("Play is ongoing!! Issuing stop");
			stopCurrentPlay.set(true);
			currentAudioPlayer.stop();
			permitToPlay.lock();
			stopCurrentPlay.set(false);
		}
	}

	@Override
	public <T> void execute(final AudioTask<T> audioTask) {
		Runnable audioTaskRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					acquireRightToPlay();
					audioTask.beforeForLoop();

					for (T e : audioTask.forLoopParameter()) {
						if (stopPlaying()) {
							break;
						}
						audioTask.forLoopBody(e);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					releaseRightToPlay();
				}
			}
		};
		executor.execute(audioTaskRunnable);
	}

	@Override
	public void play(Collection<? extends DynamicFrequency> frequencyList, AudioPlayConfig audioModeAndPlayerCombinations) {
		this.currentPlayMode = audioModeAndPlayerCombinations.playMode();
		this.currentAudioPlayer = audioModeAndPlayerCombinations.audioPlayer();
		this.audioPlayerInstances.add(currentAudioPlayer);
		currentPlayMode.play(this, frequencyList);
		
	}

	@Override
	public void playAsynchronously(Collection<? extends DynamicFrequency> frequencyList) {
		currentAudioPlayer.setAudioPlayRigthsManager(this);
		executor.execute(currentAudioPlayer.playTask(frequencyList));
	}

	@Override
	public void playSynchronously(Collection<? extends DynamicFrequency> frequencyList) {
		currentAudioPlayer.setAudioPlayRigthsManager(this);
		currentAudioPlayer.playTask(frequencyList).run();
	}

	@Override
	public void releaseRightToPlay() {
		permitToPlay.unlock();
	}

	@Override
	public boolean stopPlaying() {
		return stopCurrentPlay.get();
	}

	AudioLifeCycleManagerImpl setDurationAndVolume(int durationInMsec, double vol) {
		return new AudioLifeCycleManagerImpl(this.currentPlayMode, durationInMsec, vol);
	}
	class AudioThreadFactory implements ThreadFactory {
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