package com.aqua.music.controller.songs;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.DynamicFrequency;

abstract class AbstractSong {
	private List<DynamicFrequency> frequencies = new ArrayList<DynamicFrequency>();
	SongLine sthayiFirstLine;
	SongLine sthayiSecondLine;
	SongLine antaraFirstLine;
	SongLine antaraSecondLine;

	AbstractSong() {
		this.sthayiFirstLine = sthayiFirstLine();
		this.sthayiSecondLine = sthayiSecondLine();
		this.antaraFirstLine = antaraFirstLine();
		this.antaraSecondLine = antaraSecondLine();
		addLines(sthayiFirstLine, sthayiFirstLine, sthayiSecondLine, sthayiFirstLine, antaraFirstLine, antaraFirstLine, antaraSecondLine,
				sthayiFirstLine);

	}

	public List<DynamicFrequency> frequencies() {
		return frequencies;
	}

	protected abstract SongLine antaraFirstLine();

	protected abstract SongLine antaraSecondLine();

	protected abstract SongLine sthayiFirstLine();

	protected abstract SongLine sthayiSecondLine();

	void addLines(SongLine... songLines) {
		for (SongLine each : songLines) {
			frequencies.addAll(each.frequencies);
		}
	}
}
