package com.aqua.music.model.raag.song;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.aqua.music.model.core.DynamicFrequency;
import com.aqua.music.model.raag.MusicalPhrase;
import com.aqua.music.model.raag.Taal;

/**
 * @author "Shruti Tiwari"
 *
 */
abstract class AbstractSong {
	private final List<DynamicFrequency> frequencies = new ArrayList<DynamicFrequency>();
	private final StringBuffer printSummary = new StringBuffer();
	final int beatsPerDivision;
	
	private final Taal taal;

	private final MusicalPhrase antaraFirstLine;
	private final MusicalPhrase antaraSecondLine;
	private final MusicalPhrase sthayiFirstLine;
	private final MusicalPhrase sthayiSecondLine;
	
	private final MusicalPhrase connectorLine;

	protected static final MusicalPhrase EMPTY = new MusicalPhrase(4);

	private final Collection<Taan> taans = new ArrayList<Taan>();

	AbstractSong() {
		this(Taal.TEENTAL);
	}
	
	AbstractSong(Taal taal) {
		this.taal=taal;
		this.beatsPerDivision = taal.totalBeats()/taal.numOfInterval();
		this.sthayiFirstLine = sthayiFirstLine();
		this.sthayiSecondLine = sthayiSecondLine();
		this.antaraFirstLine = antaraFirstLine();
		this.antaraSecondLine = antaraSecondLine();
		this.connectorLine=connectorLine();
		MusicalPhrase repeatAntaraLine = antaraFirstLineVariation();
		MusicalPhrase repeatSthayiLine = sthayiFirstLineVariation();

		MusicalPhrase sthayiSecondLineVariation = sthayiSecondLineVariation();
		Collection antaraExtraLines = antaraExtraLines();
		if (repeatSthayiLine == sthayiFirstLine) {
			addLines(antaraExtraLines, sthayiFirstLine, repeatSthayiLine, sthayiSecondLine, sthayiSecondLine, sthayiSecondLineVariation,
					connectorLine, antaraFirstLine, repeatAntaraLine, antaraSecondLine);
		} else {
			addLines(antaraExtraLines, sthayiFirstLine, repeatSthayiLine, sthayiSecondLine, sthayiSecondLineVariation, connectorLine, 
					antaraFirstLine, repeatAntaraLine, antaraSecondLine);
		}

	}

	protected MusicalPhrase connectorLine() {
		return sthayiFirstLine();
	}

	protected Collection<MusicalPhrase> antaraExtraLines() {
		return Collections.EMPTY_LIST;
	}

	public List<DynamicFrequency> frequencies() {
		return frequencies;
	}

	protected MusicalPhrase antaraFirstLineVariation() {
		return antaraFirstLine();
	}

	protected MusicalPhrase sthayiFirstLineVariation() {
		return sthayiFirstLine();
	}

	protected abstract MusicalPhrase antaraFirstLine();

	protected abstract MusicalPhrase antaraSecondLine();

	protected abstract MusicalPhrase sthayiFirstLine();

	protected abstract MusicalPhrase sthayiSecondLine();

	protected MusicalPhrase sthayiSecondLineVariation() {
		return EMPTY;
	}

	void addLines(Collection<MusicalPhrase> extraAntaralines, MusicalPhrase... songLines) {
		for (MusicalPhrase each : songLines) {
			if (each == EMPTY) {
				continue;
			}
			add(each);
		}

		for (MusicalPhrase each1 : extraAntaralines) {
			add(each1);
		}
		add(sthayiFirstLine);
	}

	private void add(MusicalPhrase each) {
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
	
	static MusicalPhrase createNewMusicalPhrase() {
		return new MusicalPhrase(4);
	}
}
