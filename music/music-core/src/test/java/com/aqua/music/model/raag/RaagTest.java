package com.aqua.music.model.raag;

import org.junit.Test;

import com.aqua.music.api.AudioPlayerSettings;

public class RaagTest {
	@Test
	public void testPlayingBhimpalasiRaag() {
		/*for (int i = 0; i < 2; i++) {
			AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(new RaagBhimpalasi().aarohiAvrohi());
		}*/
		for (int i = 0; i < 2; i++) {
			AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(new RaagBhimpalasi().aalap());
		}
	}

}
