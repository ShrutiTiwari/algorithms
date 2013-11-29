package com.aqua.music.model.raag.song;

import java.util.Collection;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.api.Playable;
import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 *
 */
public enum Song implements Playable {
	S_AHIR_BHAIRAV(new SongAhirBhairav()),
	S_BAIRAGI(new SongBairagi()),
	S_BHAIRAV(new SongBhairav()),
	S_BHIMPALASI(new SongBhimpalasi()),
	S_JAUNPURI(new SongJaunpuri()),
	S_MULTANI(new SongMultani()),
	S_SHUDH_SARANG(new SongShudhSarang()),
	S_YAMAN1(new SongYaman1()),
	S_PURYA_KALYAN(new SongPuryaKalyan()),
	S_KHAMAJ(new SongKhamaj()),
	S_GUJARI_TODI(new SongGujariTodi()),
	S_BHOPALI(new SongBhopali());

	private final AbstractSong song;

	private Song(AbstractSong song) {
		this.song = song;
	}

	public String asText() {
		return song.printSummary();
	}

	public Collection<DynamicFrequency> frequencies() {
		return song.frequencies();
	}

	public void playTaan(AudioPlayerSettings audioPlayConfig) {
		Collection<Taan> taans = song.taans();
		for (Taan each : taans) {
			Taan playtaan = each;
			System.out.println(playtaan.printText());
			for (int i = 0; i < 2; i++) {
				audioPlayConfig.play(playtaan.frequencies());
			}
		}
	}

	public String songNameAsText() {
		return this.name();
	}
}