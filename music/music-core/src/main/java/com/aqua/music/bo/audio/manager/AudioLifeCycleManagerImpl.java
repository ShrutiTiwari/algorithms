package com.aqua.music.bo.audio.manager;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import open.music.api.InstrumentRole;
import open.music.api.PlayApi.AudioPlayerNextStatus;
import open.music.api.StateDependentUi;

import com.aqua.music.bo.audio.player.AudioPlayer;

/**
 * @author "Shruti Tiwari"
 * 
 */
class AudioLifeCycleManagerImpl implements AudioLifeCycleManager, AudioPlayRightsManager {
	private AudioPlayer currentAudioPlayer;

	private volatile int tempoMultipler = 0;
	private final AtomicBoolean pauseCurrentPlay;
	private final Lock permitToPlay;
	private final AtomicBoolean stopCurrentPlay;
	private volatile String configuredInstrument;

	private StateDependentUi stateObserver;
	
	private final int MULTIPLER_SLOWEST_ALLOWED_TEMPO = -9;
	private final int MULTIPLER_FASTEST_ALLOWED_TEMPO = 9;
	public AudioPlayer currentAudioPlayer(){
		return currentAudioPlayer;
	} 
	
	AudioLifeCycleManagerImpl() {
		this.permitToPlay = new ReentrantLock();
		this.stopCurrentPlay = new AtomicBoolean(false);
		this.pauseCurrentPlay = new AtomicBoolean(false);
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
		if (this.tempoMultipler == MULTIPLER_SLOWEST_ALLOWED_TEMPO) {
			logger.info("at min tempo! [" + tempoMultipler + "]");
			return;
		}
		this.tempoMultipler = tempoMultipler - 1;
		logger.info("Decreased tempo [" + tempoMultipler + "]");
		stateObserver.updateTempo(tempoMultipler);
	}

	@Override
	public void increaseTempo() {
		if (this.tempoMultipler == MULTIPLER_FASTEST_ALLOWED_TEMPO) {
			logger.info("at max tempo! [" + tempoMultipler + "]");
			return;
		}
		this.tempoMultipler = tempoMultipler + 1;
		logger.info("Increased tempo [" + tempoMultipler + "]");
		stateObserver.updateTempo(tempoMultipler);
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
			changeInstrumentTo(configuredInstrument, InstrumentRole.MAIN);
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
	public int customizedTempoDuration(int duration) {
		final int customizedDuration = (tempoMultipler == 0) ? duration : newDuration(duration);
		return customizedDuration;
	}

	private int newDuration(int duration) {
		return Math.round((float) (duration - (0.1 * tempoMultipler * duration)));
	}

	@Override
	public void changeInstrumentTo(String instrument, InstrumentRole instrumentRole) {
		this.configuredInstrument=instrument;
		if (currentAudioPlayer != null) {
			currentAudioPlayer.changeInstrumentTo(configuredInstrument, instrumentRole);
		}
	}

	public void addStateObserver(StateDependentUi stateDependentUi) {
		this.stateObserver=stateDependentUi;
	}
}