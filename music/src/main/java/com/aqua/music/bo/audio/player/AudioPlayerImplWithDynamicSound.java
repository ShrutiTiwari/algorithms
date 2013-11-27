package com.aqua.music.bo.audio.player;

import java.util.Collection;

import javax.sound.midi.Instrument;

import com.aqua.music.bo.audio.manager.AudioPlayRightsManager;
import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
class AudioPlayerImplWithDynamicSound implements AudioPlayer {
	private volatile AudioPlayRightsManager audioPlayRightsManager;
	private final BasicNotePlayer basicNotePlayer = BasicNotePlayer.MIDI_BASED_PLAYER;

	public void playFrequencies(final Collection<? extends DynamicFrequency> frequencyList) {
		try {
			audioPlayRightsManager.acquireRightToPlay();
			logger.debug("acquired right to play");
			basicNotePlayer.start();
			generateSound(frequencyList);
			basicNotePlayer.finish();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			basicNotePlayer.tidyup();
			logger.debug("releasing right to play");
			audioPlayRightsManager.releaseRightToPlay();
		}
	}

	public void playFrequenciesInLoop(final Collection<? extends DynamicFrequency> frequencyList) {
		try {
			audioPlayRightsManager.acquireRightToPlay();
			logger.debug("acquired right to play");
			basicNotePlayer.start();
			while (!audioPlayRightsManager.isMarkedToStopPlaying()) {
				generateSound(frequencyList);
			}
			basicNotePlayer.finish();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			basicNotePlayer.tidyup();
			logger.debug("releasing right to play");
			audioPlayRightsManager.releaseRightToPlay();
		}
	}

	public final Runnable playTask(final Collection<? extends DynamicFrequency> frequencyList) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				playFrequencies(frequencyList);
			}
		};
		return task;
	}

	@Override
	public Runnable playTaskInLoop(final Collection<? extends DynamicFrequency> frequencyList) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				playFrequenciesInLoop(frequencyList);
			}
		};
		return task;
	}

	public void setAudioPlayRigthsManager(AudioPlayRightsManager audioPlayRightsManager) {
		this.audioPlayRightsManager = audioPlayRightsManager;
	}

	public void stop() {
		basicNotePlayer.stop();
	}

	private void generateSound(final Collection<? extends DynamicFrequency> frequencyList) {
		for (final DynamicFrequency each : frequencyList) {
			if (audioPlayRightsManager.isMarkedToStopPlaying()) {
				logger.debug("oops, marked to stop..breaking now.");
				break;
			} else if (audioPlayRightsManager.pauseCurrentPlay()) {
				while (audioPlayRightsManager.pauseCurrentPlay() && !audioPlayRightsManager.isMarkedToStopPlaying()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				logger.debug("Pause cleared Or marked to stop playing!");
			}
			if (audioPlayRightsManager.isMarkedToStopPlaying()) {
				return;
			} else {
				final int customizedDuration = audioPlayRightsManager.tempoMultilier(each.duration());
				throwExceptionForInsaneInput(customizedDuration);
				basicNotePlayer.play(each, customizedDuration);
			}
		}
	}

	private void throwExceptionForInsaneInput(int msecs) {
		if (msecs <= 0)
			throw new IllegalArgumentException("Duration <= 0 msecs");
	}

	@Override
	public void changeInstrumentTo(Instrument instrument) {
		basicNotePlayer.notifyInstrumentChange(instrument);
	}
}
