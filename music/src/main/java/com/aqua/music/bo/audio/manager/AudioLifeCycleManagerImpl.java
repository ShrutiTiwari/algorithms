package com.aqua.music.bo.audio.manager;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.midi.Instrument;

import com.aqua.music.bo.audio.player.AudioPlayer;

class AudioLifeCycleManagerImpl implements AudioLifeCycleManager, AudioPlayRightsManager {
	private AudioPlayer currentAudioPlayer;

	private volatile int multipler = 0;
	private final AtomicBoolean pauseCurrentPlay;
	private final Lock permitToPlay;
	private final AtomicBoolean stopCurrentPlay;

	AudioLifeCycleManagerImpl() {
		this.permitToPlay = new ReentrantLock();
		this.stopCurrentPlay = new AtomicBoolean(false);
		this.pauseCurrentPlay = new AtomicBoolean(false);
	}

	AudioLifeCycleManagerImpl(int durationInMsec, double vol) {
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
		}
		stopCurrentPlay.set(false);
		pauseCurrentPlay.set(false);
	}

	@Override
	public void decreaseTempo() {
		if (this.multipler == -9) {
			logger.info("at min tempo! [" + multipler + "]");
			return;
		}
		this.multipler = multipler - 1;
		logger.info("Decreased tempo [" + multipler + "]");
	}

	@Override
	public void increaseTempo() {
		if (this.multipler == 9) {
			logger.info("at max tempo! [" + multipler + "]");
			return;
		}
		this.multipler = multipler + 1;
		logger.info("Increased tempo [" + multipler + "]");
	}

	@Override
	public void pause() {
		if (currentAudioPlayer != null) {
			pauseCurrentPlay.set(true);
		}
	}

	@Override
	public boolean pauseCurrentPlay() {
		return pauseCurrentPlay.get();
	}

	@Override
	public void releaseRightToPlay() {
		permitToPlay.unlock();
	}

	@Override
	public void resume() {
		if (currentAudioPlayer != null) {
			pauseCurrentPlay.compareAndSet(true, false);
		}
	}

	@Override
	public void setCurrentPlayer(AudioPlayer audioPlayer) {
		this.currentAudioPlayer = audioPlayer;
	}

	@Override
	public synchronized void stop() {
		if (currentAudioPlayer != null) {
			pauseCurrentPlay.set(false);
			stopCurrentPlay.set(true);
			currentAudioPlayer.stop();
		}
	}

	@Override
	public boolean stopPlaying() {
		return stopCurrentPlay.get();
	}

	@Override
	public int tempoMultilier(int duration) {
		final int customizedDuration = (multipler == 0) ? duration : newDuration(duration);
		return customizedDuration;
	}

	AudioLifeCycleManagerImpl setDurationAndVolume(int durationInMsec, double vol) {
		return new AudioLifeCycleManagerImpl(durationInMsec, vol);
	}

	private int newDuration(int duration) {
		return Math.round((float) (duration - (0.1 * multipler * duration)));
	}

	@Override
	public void changeInstrumentTo(Instrument instrument) {
		currentAudioPlayer.changeInstrumentTo(instrument);
	}
}