package com.aqua.music.controller.songs;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.DynamicFrequency;

abstract class AbstractSong {
	private final List<DynamicFrequency> frequencies = new ArrayList<DynamicFrequency>();
	private final StringBuffer printSummary = new StringBuffer();
	final int beatDivison;

	private final SongLine antaraFirstLine;
	private final SongLine antaraSecondLine;
	private final SongLine sthayiFirstLine;
	private final SongLine sthayiSecondLine;

	AbstractSong(int beatDivison) {
		this.beatDivison = beatDivison;
		this.sthayiFirstLine = sthayiFirstLine();
		this.sthayiSecondLine = sthayiSecondLine();
		this.antaraFirstLine = antaraFirstLine();
		this.antaraSecondLine = antaraSecondLine();
		addLines(sthayiFirstLine, sthayiFirstLine, sthayiSecondLine, sthayiSecondLine, sthayiFirstLine, antaraFirstLine, antaraFirstLine,
				antaraSecondLine, sthayiFirstLine);

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
			frequencies.addAll(each.frequencies());
			printSummary.append("\n" + each.printLine());
		}
	}

	String printSummary() {
		return printSummary.toString();
	}
}
