package com.aqua.music.audio.manager;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import com.aqua.music.audio.player.AudioPlayer;
import com.aqua.music.model.Frequency;

public class AudioLifeCycleManagerWithDualMode implements DualModeManager, AudioLifeCycleManager,AudioPlayRightsManager {
	private final AudioPlayer audioPlayer;
	private final Semaphore permitToPlay;
	private volatile CountDownLatch playInProgress = null;
	private final PlayMode playMode;

	private final AtomicBoolean stopCurrentPlay;

	AudioLifeCycleManagerWithDualMode(PlayMode playMode, AudioPlayer audioPlayer) {
		this.playMode = playMode;
		this.audioPlayer = audioPlayer;
		this.permitToPlay = new Semaphore(1);
		this.stopCurrentPlay = new AtomicBoolean(false);
	}

	AudioLifeCycleManagerWithDualMode(PlayMode playMode, int durationInMsec, double vol) {
		this(playMode, AudioPlayer.Factory.customizedDymanic(durationInMsec, vol));
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
	public void play(final Collection<Frequency> frequencyList) {
		playMode.play(this, frequencyList);
	}

	@Override
	public void playAsynchronously(Collection<Frequency> frequencyList) {
		audioPlayer.setAudioPlayRigthsManager(this);
		int availablePermits = permitToPlay.availablePermits();
		System.out.println("\n availablePermits=" + availablePermits);
		if (availablePermits <= 0) {
			System.out.println("Play is ongoing!! Issuing stop");
			stopCurrentPlay.set(true);
			audioPlayer.stop();
		}
		System.out.println("Playing new list in non-blocking mode...");
		playInProgress = new CountDownLatch(1);
		executor.execute(audioPlayer.playTask(frequencyList));
	}

	@Override
	public void playSynchronously(Collection<Frequency> frequencyList) {
		audioPlayer.setAudioPlayRigthsManager(this);
		audioPlayer.playTask(frequencyList).run();
	}

	@Override
	public void acquireRightToPlay() throws InterruptedException {
		if (playMode == PlayMode.Synchronous) {
			return;
		}
		permitToPlay.acquire();
		stopCurrentPlay.set(false);
	}

	@Override
	public boolean continuePlaying() {
		if (playMode == PlayMode.Synchronous) {
			return true;
		}
		return !stopCurrentPlay.get();
	}

	@Override
	public void releaseRightToPlay() {
		if (playMode == PlayMode.Synchronous) {
			return;
		}
		System.out.println("releasing right to play");
		permitToPlay.release();
	}

	AudioLifeCycleManagerWithDualMode setDurationAndVolume(int durationInMsec, double vol) {
		return new AudioLifeCycleManagerWithDualMode(this.playMode, durationInMsec, vol);
	}
}