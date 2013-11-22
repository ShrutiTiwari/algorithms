package com.aqua.music.model.raag.song;

public class SongBhopali extends AbstractSong{
	public SongBhopali(int beatDivison) {
		super(beatDivison);
	}

	@Override
	protected SongLine antaraFirstLine() {
		return EMPTY;
	}

	@Override
	protected SongLine antaraSecondLine() {
		return EMPTY;
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return EMPTY;
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return EMPTY;
	}
}
