package com.aqua.music.model.raag;

import open.music.api.AudioPlayerFacade;

import org.junit.Test;

import com.aqua.music.bo.audio.manager.CommonCode;

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
		CommonCode.initialize();
	}

}
