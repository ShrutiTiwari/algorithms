package com.aqua.music.model.raag.song;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.aqua.music.model.core.DynamicFrequency;

abstract class AbstractSong {
	private final List<DynamicFrequency> frequencies = new ArrayList<DynamicFrequency>();
	private final StringBuffer printSummary = new StringBuffer();
	final int beatDivison;

	private final SongLine antaraFirstLine;
	private final SongLine antaraSecondLine;
	private final SongLine sthayiFirstLine;
	private final SongLine sthayiSecondLine;

	protected static final SongLine EMPTY = new SongLine(4);

	private final Collection<Taan> taans = new ArrayList<Taan>();

	AbstractSong(int beatDivison) {
		this.beatDivison = beatDivison;
		this.sthayiFirstLine = sthayiFirstLine();
		this.sthayiSecondLine = sthayiSecondLine();
		this.antaraFirstLine = antaraFirstLine();
		this.antaraSecondLine = antaraSecondLine();
		SongLine repeatAntaraLine = antaraFirstLineVariation();
		SongLine repeatSthayiLine = sthayiFirstLineVariation();

		SongLine sthayiSecondLineVariation = sthayiSecondLineVariation();
		Collection antaraExtraLines = antaraExtraLines();
		if (repeatSthayiLine == sthayiFirstLine) {
			addLines(antaraExtraLines, sthayiFirstLine, repeatSthayiLine, sthayiSecondLine, sthayiSecondLine, sthayiSecondLineVariation,
					sthayiFirstLine, antaraFirstLine, repeatAntaraLine, antaraSecondLine);
		} else {
			addLines(antaraExtraLines, sthayiFirstLine, repeatSthayiLine, sthayiSecondLine, sthayiSecondLineVariation, sthayiFirstLine,
					antaraFirstLine, repeatAntaraLine, antaraSecondLine);
		}

	}

	protected Collection<SongLine> antaraExtraLines() {
		return Collections.EMPTY_LIST;
	}

	public List<DynamicFrequency> frequencies() {
		return frequencies;
	}

	protected SongLine antaraFirstLineVariation() {
		return antaraFirstLine();
	}

	protected SongLine sthayiFirstLineVariation() {
		return sthayiFirstLine();
	}

	protected abstract SongLine antaraFirstLine();

	protected abstract SongLine antaraSecondLine();

	protected abstract SongLine sthayiFirstLine();

	protected abstract SongLine sthayiSecondLine();

	protected SongLine sthayiSecondLineVariation() {
		return EMPTY;
	}

	void addLines(Collection<SongLine> extraAntaralines, SongLine... songLines) {
		for (SongLine each : songLines) {
			if (each == EMPTY) {
				continue;
			}
			add(each);
		}

		for (SongLine each1 : extraAntaralines) {
			add(each1);
		}
		add(sthayiFirstLine);
	}

	private void add(SongLine each) {
		for (int i = 0; i < each.repeatCount(); i++) {
			frequencies.addAll(each.frequencies());
			printSummary.append("\n" + each.printLine());
		}
	}

	String printSummary() {
		return printSummary.toString();
	}

	protected void addAll(Collection<Taan> myTaans) {
		taans.addAll(myTaans);
	}

	Collection<Taan> taans() {
		return taans;
	}
}
