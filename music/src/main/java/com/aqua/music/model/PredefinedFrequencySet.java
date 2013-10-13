package com.aqua.music.model;

import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.DHA;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.DHA_;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.GA;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.GA_;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.HIGH_SA;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.MA;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.MA_;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.NI;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.NI_;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.PA;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.RE;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.RE_;
import static com.aqua.music.model.PredefinedFrequency.FundamentalNote.SA;

import com.aqua.music.model.PredefinedFrequency;
import com.aqua.music.play.AudioFileListMaker;
import com.aqua.music.play.AudioLibrary;

public interface PredefinedFrequencySet {
	public String name();
	public String type();
	public PredefinedFrequency[] ascendNotes();
	public PredefinedFrequency[] descendNotes();
	public void playAscendAndDescend();
	public void playAscendAndDescend(PatternApplier pattern);

	public enum Thaat implements PredefinedFrequencySet {
		BHAIRAV(RE_, GA, MA, PA, DHA_, NI), PURVI(RE_, GA, MA_, PA, DHA_, NI), MARWA(RE_, GA, MA_, PA, DHA, NI), KALYAN(RE, GA, MA_, PA, DHA, NI), BILAWAL(RE,
				GA, MA, PA, DHA, NI), KHAMAJ(RE, GA, MA, PA, DHA, NI_), KAFI(RE, GA_, MA, PA, DHA, NI_), ASAVARI(RE, GA_, MA, PA, DHA_, NI_), BHAIRAVI(RE_,
				GA_, MA, PA, DHA_, NI_), TODI(RE_, GA_, MA_, PA, DHA_, NI);

		private final PredefinedFrequency[] ascendNotes;
		private final PredefinedFrequency[] descendNotes;

		private Thaat(PredefinedFrequency... ascendNotes) {
			this.ascendNotes = ascendNotes;
			this.descendNotes = Util.reverse(ascendNotes);
		}

		public void playAscendAndDescend() {
			AudioLibrary.audioPlayer().play(ThaatPlayableItem.forThaat(this));
		}
		
		public void playAscendAndDescend(PatternApplier pattern) {
			AudioLibrary.audioPlayer().play(ThaatPlayableItem.forThaat(this).andPattern(pattern));
		}

		public String type() {
			return "THAAT";
		}

		public PredefinedFrequency[] ascendNotes() {
			return ascendNotes;
		}

		public PredefinedFrequency[] descendNotes() {
			return descendNotes;
		}
	}

	public enum SecondYearRaag implements PredefinedFrequencySet {
		GUJARI_TODI(RE_, GA_, MA_, DHA_, NI), BAIRAGI(RE_, MA, PA, NI_), SHUDH_SARANG(sequence(RE, MA_, PA, NI), sequence(NI, DHA, PA, MA_, PA, MA, RE)), YAMAN(
				sequence(RE, GA, MA_, DHA, NI), sequence(NI, DHA, PA, MA_, GA, RE)), PURYA_KALYAN(sequence(RE_, GA, MA_, PA, MA_, DHA, NI), sequence(NI, DHA,
				PA, DHA, MA_, PA, GA, MA_, RE_, GA, RE_)), MULTANI(sequence(GA_, MA_, PA, NI), sequence(NI, DHA_, PA, MA_, GA_, RE_));

		private final PredefinedFrequency[] ascendNotes;
		private final PredefinedFrequency[] descendNotes;

		private SecondYearRaag(PredefinedFrequency... ascendNotes) {
			this.ascendNotes = ascendNotes;
			this.descendNotes = Util.reverse(ascendNotes);
		}

		private SecondYearRaag(PredefinedFrequency[] ascendNotes, PredefinedFrequency[] descendNotes) {
			this.ascendNotes = ascendNotes;
			this.descendNotes = descendNotes;
		}

		private static PredefinedFrequency[] sequence(PredefinedFrequency... notes) {
			return notes;
		}

		public void playAscendAndDescend() {
			Util.play(SA, ascendNotes, HIGH_SA);
			Util.play(HIGH_SA, descendNotes, SA);
		}

		public String type() {
			return "RAAG";
		}

		@Override
		public PredefinedFrequency[] ascendNotes() {
			return ascendNotes;
		}

		@Override
		public PredefinedFrequency[] descendNotes() {
			return descendNotes;
		}

		@Override
		public void playAscendAndDescend(PatternApplier pattern) {
			// TODO Auto-generated method stub
			
		}
	}

	static abstract class Util {
		static void play(PredefinedFrequency start, PredefinedFrequency[] middleNotes, PredefinedFrequency end) {
			AudioLibrary.audioPlayer().playList(new AudioFileListMaker.MiddleNoteWithStartEndListMaker(start, end, middleNotes).collectedAudioFiles());
		}

		static PredefinedFrequency[] reverse(PredefinedFrequency... ascendNotes) {
			int count = ascendNotes.length;
			PredefinedFrequency[] dscendNotes = new PredefinedFrequency[count];
			for (int i = 0; i < count; i++) {
				dscendNotes[i] = ascendNotes[count - i - 1];
			}
			return dscendNotes;
		}

		static PredefinedFrequency[] sequence(PredefinedFrequency... notes) {
			return notes;
		}
	}
}