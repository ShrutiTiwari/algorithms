package com.aqua.music.model.raag;

import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.raag.song.Song;

public enum Raag {
	RAAG_AHIR_BHAIRAV(Time.MORNING, SymmetricalSet.THAAT_BHAIRAV, RaagArohiAvrohi.AHIR_BHAIRAV, Song.S_AHIR_BHAIRAV),
	RAAG_BAIRAGI(Time.MORNING, SymmetricalSet.THAAT_BHAIRAV, RaagArohiAvrohi.BAIRAGI, Song.S_BAIRAGI),
	RAAG_BHAIRAV(Time.MORNING, SymmetricalSet.THAAT_BHAIRAV, RaagArohiAvrohi.BHAIRAV, Song.S_BHAIRAV),
	RAAG_BHIMPALASI(Time.AFTERNOON, SymmetricalSet.THAAT_KAFI, RaagArohiAvrohi.BHIMPALASI, Song.S_BHIMPALASI),
	RAAG_BHOPALI(Time.EVENING, SymmetricalSet.THAAT_KALYAN, RaagArohiAvrohi.BHOPALI, Song.SONG_BHOPALI),
	RAAG_GUJARI_TODI(Time.MORNING, SymmetricalSet.THAAT_TODI, RaagArohiAvrohi.GUJARI_TODI, Song.SONG_GUJARI_TODI),
	RAAG_JAUNPURI(Time.AFTERNOON, SymmetricalSet.THAAT_ASAVARI, RaagArohiAvrohi.JAUNPURI, Song.S_JAUNPURI),
	RAAG_KHAMAJ(Time.EVENING, SymmetricalSet.THAAT_KHAMAJ, RaagArohiAvrohi.KHAMAJ, Song.SONG_KHAMAJ),
	RAAG_MULTANI(Time.AFTERNOON, SymmetricalSet.THAAT_TODI, RaagArohiAvrohi.MULTANI, Song.S_MULTANI),
	RAAG_PURYA_KALYAN(Time.EVENING, SymmetricalSet.THAAT_MARWA, RaagArohiAvrohi.PURYA_KALYAN, Song.SONG_PURYA_KALYAN),
	RAAG_SHUDH_SARANG(Time.AFTERNOON, SymmetricalSet.THAAT_KALYAN, RaagArohiAvrohi.SHUDH_SARANG, Song.S_SHUDH_SARANG),
	RAAG_YAMAN(Time.EVENING, SymmetricalSet.THAAT_KALYAN, RaagArohiAvrohi.YAMAN, Song.S_YAMAN1);

	private final Time time;
	private final SymmetricalSet thaat;
	private final RaagArohiAvrohi raagArohiAvrohi;
	private final Song song;

	private Raag(Time time, SymmetricalSet thaat, RaagArohiAvrohi raagArohiAvrohi, Song song) {
		this.time = time;
		this.thaat = thaat;
		this.raagArohiAvrohi = raagArohiAvrohi;
		this.song = song;
	}

	interface RaagProp {
	}

	enum Time implements RaagProp {
		AFTERNOON,
		EVENING,
		MORNING;
	}
}
