package com.aqua.music.model.raag;

import open.music.api.AudioPlayerFacade;
import open.music.api.DeviceType;

import org.junit.Test;

public class RaagTest {
	@Test
	public void testPlayingBhimpalasiRaag() {
		initialize();
		/*for (int i = 0; i < 2; i++) {
			AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(new RaagBhimpalasi().aarohiAvrohi());
		}*/
		for (int i = 0; i < 2; i++) {
			AudioPlayerFacade.SYNCHRONOUS_PLAYER.play(new RaagBhimpalasi().aalap(), 1);
		}
	}
	
	private void initialize() {
		DeviceType.DESKTOP_DYNAMIC.initializeAudioFactory();
	}

}
