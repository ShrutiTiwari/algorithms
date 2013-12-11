package com.aqua.music.bo.audio.manager;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.midi.Instrument;

import open.music.api.PlayApi.AudioPlayerNextStatus;

import com.aqua.music.bo.audio.player.AudioPlayer;

/**
 * @author "Shruti Tiwari"
 * 
 */
class AudioLifeCycleManagerImpl implements AudioLifeCycleManager, AudioPlayRightsManager {
	private AudioPlayer currentAudioPlayer;

	private volatile int multipler = 0;
	private final AtomicBoolean pauseCurrentPlay;
	private final Lock permitToPlay;
	private final AtomicBoolean stopCurrentPlay;
	private volatile Instrument configuredInstrument;
	
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
	public AudioPlayerNextStatus togglePauseAndResume() {
		if (currentAudioPlayer != null) {
			if (!pauseCurrentPlay.compareAndSet(false, true)) {
				pauseCurrentPlay.compareAndSet(true, false);
			}
		}
		return pauseCurrentPlay() ? AudioPlayerNextStatus.RESUME : AudioPlayerNextStatus.PAUSE;
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
	public void setCurrentPlayer(AudioPlayer audioPlayer) {
		this.currentAudioPlayer = audioPlayer;
		if(configuredInstrument!=null){
			changeInstrumentTo(configuredInstrument);
		}
	}

	@Override
	public synchronized void stop() {
		if (currentAudioPlayer != null) {
			stopCurrentPlay.set(true);
			currentAudioPlayer.stop();
			pauseCurrentPlay.set(false);
		}
	}

	@Override
	public boolean isMarkedToStopPlaying() {
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
		this.configuredInstrument=instrument;
		if (currentAudioPlayer != null) {
			currentAudioPlayer.changeInstrumentTo(configuredInstrument);
		}
	}
}