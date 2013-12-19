package com.aqua.music.bo.audio.player;

import java.util.Collection;

import open.music.api.InstrumentRole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioPlayRightsManager;
import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface AudioPlayer {
	Logger logger = LoggerFactory.getLogger(AudioPlayer.class);

	Runnable playTask(final Collection<? extends DynamicFrequency> frequencyList, int repeatCount);

	Runnable playTaskInLoop(Collection<? extends DynamicFrequency> frequencyList);

	void setAudioPlayRigthsManager(AudioPlayRightsManager audioPlayRightsManager);

	void stop();
	
	String[] allInstruments();
	
	public void setBasicNotePalyer(BasicNotePlayer basicNotePlayer);

	enum Factory {
		DYNAMIC_AUDIO(AudioPlayerImplWithDynamicSound.class),
		STATIC_AUDIO(AudioPlayerImplWithStaticSoundBasedOnVLC.class);

		private final Class<? extends AudioPlayer> audioPlayerClass;
		private AudioPlayer audioPlayerInstance;

		private boolean initialized = false;

		private Factory(Class<? extends AudioPlayer> audioPlayerClass) {
			this.audioPlayerClass = audioPlayerClass;
		}

		public AudioPlayer fetchInstance() {
			try {
				if (!initialized) {
					lazyInitialize();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return audioPlayerInstance;
		}

		private synchronized void lazyInitialize() throws InstantiationException, IllegalAccessException {
			if (!initialized) {
				this.audioPlayerInstance = audioPlayerClass.newInstance();
				initialized = true;
			}
		}
	}

	void changeInstrumentTo(String instrument, InstrumentRole changingInstrument);
}
