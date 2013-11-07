package com.aqua.music.audio.player;

import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import com.aqua.music.model.Frequency;

class AudioPlayCoordinator implements DualModePlayer {
	private final Semaphore permitToPlay = new Semaphore(1);
	private final AtomicBoolean stopCurrentPlay = new AtomicBoolean(false);
	private final AudioGenerator audioGenerator;
	private final PlayMode playMode;

	AudioPlayCoordinator(PlayMode playMode) {
		this.playMode = playMode;
		this.audioGenerator = new AudioGeneratorBasedOnMathSinAngle();
	}

	AudioPlayCoordinator(PlayMode playMode, int durationInMsec, double vol) {
		this.playMode = playMode;
		this.audioGenerator = new AudioGeneratorBasedOnMathSinAngle(durationInMsec, vol);
	}

	public AudioPlayCoordinator(boolean blockingPlay) {
		this(PlayMode.findFor(blockingPlay));
	}

	public void play(final Collection<Frequency> frequencyList) {
		playMode.play(this, frequencyList);
	}

	public void playAsynchronously(Collection<Frequency> frequencyList) {
		audioGenerator.setCoordinator(this);
		int availablePermits = permitToPlay.availablePermits();
		System.out.println("\n availablePermits=" + availablePermits);
		if (availablePermits <= 0) {
			System.out.println("Play is ongoing!! Issuing stop");
			stopCurrentPlay.set(true);
			audioGenerator.stop();
		}
		System.out.println("Playing new list in non-blocking mode...");
		executor.execute(playTask(frequencyList));
	}

	public void playSynchronously(Collection<Frequency> frequencyList) {
		audioGenerator.setCoordinator(this);
		playTask(frequencyList).run();
	}

	void acquireRightToPlay() throws InterruptedException {
		if(playMode == PlayMode.Synchronous){
			return;
		}
		permitToPlay.acquire();
		stopCurrentPlay.set(false);
	}

	boolean continuePlaying() {
		if(playMode == PlayMode.Synchronous){
			return true;
		}
		return !stopCurrentPlay.get();
	}

	void releaseRightToPlay() {
		if(playMode == PlayMode.Synchronous){
			return;
		}
		System.out.println("releasing right to play");
		permitToPlay.release();
	}

	AudioPlayCoordinator setDurationAndVolume(int durationInMsec, double vol) {
		return new AudioPlayCoordinator(this.playMode, durationInMsec, vol);
	}

	private final Runnable playTask(final Collection<Frequency> frequencyList) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				audioGenerator.playFrequencies(frequencyList);
			}
		};
		return task;
	}

	enum PlayMode {
		Synchronous {
			@Override
			public void play(DualModePlayer dualModePlayer, Collection<Frequency> frequencyList) {
				dualModePlayer.playSynchronously(frequencyList);
			}
		},
		Asynchornous {
			@Override
			public void play(DualModePlayer dualModePlayer, Collection<Frequency> frequencyList) {
				dualModePlayer.playAsynchronously(frequencyList);
			}
		};
		abstract public void play(DualModePlayer dualModePlayer, final Collection<Frequency> frequencyList);

		static PlayMode findFor(boolean blockingPlay) {
			return blockingPlay ? Synchronous : Asynchornous;
		}
	}
}