package com.aqua.music.bo.audio.player;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioPlayRightsManager;
import com.aqua.music.model.Frequency;

public interface AudioPlayer {
	Logger logger = LoggerFactory.getLogger(AudioPlayer.class);
	void setAudioPlayRigthsManager(AudioPlayRightsManager audioPlayRightsManager);

	void stop();

	Runnable playTask(final Collection<Frequency> frequencyList);

	enum Factory {
		DYNAMIC_AUDIO(AudioPlayerImplWithDynamicSoundBasedOnMathSinAngle.class),
		STATIC_AUDIO(AudioPlayerImplWithStaticSoundBasedOnVLC.class);

		private final Class<? extends AudioPlayer> audioPlayerClass;
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

		private AudioPlayer audioPlayerInstance;

		private synchronized void lazyInitialize() throws InstantiationException, IllegalAccessException {
			if (!initialized) {
				this.audioPlayerInstance = audioPlayerClass.newInstance();
				initialized = true;
			}
		}

		public static AudioPlayer customizedDymanic(int durationInMsec, double vol) {
			return new AudioPlayerImplWithDynamicSoundBasedOnMathSinAngle(durationInMsec, vol);
		}
	}
}
