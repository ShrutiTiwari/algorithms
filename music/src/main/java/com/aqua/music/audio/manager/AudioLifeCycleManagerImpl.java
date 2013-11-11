package com.aqua.music.audio.manager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import com.aqua.music.audio.manager.PlayMode.DualModeManager;
import com.aqua.music.audio.player.AudioPlayer;
import com.aqua.music.model.Frequency;

class AudioLifeCycleManagerImpl implements DualModeManager, AudioLifeCycleManager, AudioPlayRightsManager {
	private final Set<AudioPlayer> audioPlayerInstances = new HashSet<AudioPlayer>();

	private AudioPlayer currentAudioPlayer;
	private PlayMode currentPlayMode;

	private final Semaphore permitToPlay;
	private volatile CountDownLatch playInProgress = null;
	private final AtomicBoolean stopCurrentPlay;

	AudioLifeCycleManagerImpl() {
		this.permitToPlay = new Semaphore(1);
		this.stopCurrentPlay = new AtomicBoolean(false);
	}

	AudioLifeCycleManagerImpl(PlayMode playMode, int durationInMsec, double vol) {
		this();
	}

	@Override
	public void acquireRightToPlay() throws InterruptedException {
		permitToPlay.acquire();
		stopCurrentPlay.set(false);
	}

	@Override
	public void awaitStop() {
		if (playInProgress != null) {
			try {
				playInProgress.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("nothing to wait for");
		}
	}

	@Override
	public boolean continuePlaying() {
		return !stopCurrentPlay.get();
	}

	@Override
	public void execute(Runnable audioTask) {
		AudioExecutor.executor.execute(audioTask);
	}

	@Override
	public boolean isPlayInProgress() {
		return playInProgress != null && playInProgress.getCount() == 1;
	}

	@Override
	public void issueStop() {
		if (playInProgress != null) {
			playInProgress.countDown();
		}
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
		prepareToRunNewTask();
		executor.execute(currentAudioPlayer.playTask(frequencyList));
	}

	@Override
	public void playSynchronously(Collection<Frequency> frequencyList) {
		prepareToRunNewTask();
		currentAudioPlayer.playTask(frequencyList).run();
	}

	@Override
	public void releaseRightToPlay() {
		System.out.println("releasing right to play");
		permitToPlay.release();
	}

	AudioLifeCycleManagerImpl setDurationAndVolume(int durationInMsec, double vol) {
		return new AudioLifeCycleManagerImpl(this.currentPlayMode, durationInMsec, vol);
	}

	private void killCurrentlyRunningAudio(int availablePermits) {
		if (availablePermits <= 0) {
			System.out.println("Play is ongoing!! Issuing stop");
			stopCurrentPlay.set(true);
			currentAudioPlayer.stop();
		}
	}

	private void prepareToRunNewTask() {
		currentAudioPlayer.setAudioPlayRigthsManager(this);
		killCurrentlyRunningAudio(permitToPlay.availablePermits());
		System.out.println("Playing new list in non-blocking mode...");
		playInProgress = new CountDownLatch(1);
	}
}