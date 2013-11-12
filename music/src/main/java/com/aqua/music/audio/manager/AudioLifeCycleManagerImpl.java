package com.aqua.music.audio.manager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.aqua.music.audio.manager.PlayMode.DualModeManager;
import com.aqua.music.audio.player.AudioPlayer;
import com.aqua.music.model.Frequency;

class AudioLifeCycleManagerImpl implements DualModeManager, AudioLifeCycleManager, AudioPlayRightsManager {
	private final Set<AudioPlayer> audioPlayerInstances = new HashSet<AudioPlayer>();
	private AudioPlayer currentAudioPlayer;

	private PlayMode currentPlayMode;
	private final ExecutorService executor = AudioExecutor.executor;

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
	public void acquireRightToPlay() throws InterruptedException {
		synchronized (stopCurrentPlay) {
			boolean acquired = permitToPlay.tryLock();
			if (!acquired) {
				System.out.println("Play is ongoing!! Issuing stop");
				stopCurrentPlay.set(true);
				currentAudioPlayer.stop();
				permitToPlay.lock();
				stopCurrentPlay.set(false);
			}
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
		AudioExecutor.executor.execute(audioTaskRunnable);
	}

	@Override
	public void play(final Collection<Frequency> frequencyList, AudioPlayConfig playconfig) {
		this.currentPlayMode = playconfig.playMode();
		this.currentAudioPlayer = playconfig.audioPlayer();
		this.audioPlayerInstances.add(currentAudioPlayer);
		currentPlayMode.play(this, frequencyList);
	}

	@Override
	public void playAsynchronously(Collection<Frequency> frequencyList) {
		currentAudioPlayer.setAudioPlayRigthsManager(this);
		executor.execute(currentAudioPlayer.playTask(frequencyList));
	}

	@Override
	public void playSynchronously(Collection<Frequency> frequencyList) {
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
}