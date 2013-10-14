package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aqua.music.model.FundamentalFrequency;
import com.aqua.music.model.FundamentalFrequency.ClassicalNote;
import com.aqua.music.model.PredefinedFrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioLibrary.AudioFilesList;

public interface AudioFileListMaker {
	boolean NO_COMMA = false;
	boolean WITH_COMMA = true;

	Collection<File> allAudioFiles();

	String prettyPrintText();

	public static class ListMakerForSymmetricalSet implements AudioFileListMaker {
		private AudioFilesList audioFilesList;

		public ListMakerForSymmetricalSet(SymmetricalSet symmetricalSet) {
			this.audioFilesList = createWith(symmetricalSet);
		}

		private static AudioFilesList createWith(SymmetricalSet symmetricalSet) {
			// enqueue ascend sequence
			AudioFilesList audioFilesList = new AudioFilesList();
			audioFilesList.addIfFileFound(ClassicalNote.SA, NO_COMMA);
			audioFilesList.addIfFileFound(symmetricalSet.ascendNotes());
			audioFilesList.addIfFileFound(ClassicalNote.HIGH_SA, WITH_COMMA);

			audioFilesList.addText(" |||  ");

			// enqueue descend sequence
			audioFilesList.addIfFileFound(ClassicalNote.HIGH_SA, NO_COMMA);
			audioFilesList.addIfFileFound(symmetricalSet.descendNotes());
			audioFilesList.addIfFileFound(ClassicalNote.SA, WITH_COMMA);
			return audioFilesList;
		}

		public Collection<File> allAudioFiles() {
			return audioFilesList.allAudioFiles;
		}

		public String prettyPrintText() {
			return audioFilesList.prettyPrintText.toString();
		}
	}

	public static class ListMakerWithMiddleNotesAndStartEndNotes implements AudioFileListMaker {
		private AudioFilesList audioFilesList;

		public ListMakerWithMiddleNotesAndStartEndNotes(FundamentalFrequency[] middleNotes, FundamentalFrequency start, FundamentalFrequency end) {
			this.audioFilesList = new AudioFilesList();
			audioFilesList.addIfFileFound(start);
			audioFilesList.addIfFileFound(middleNotes);
			audioFilesList.addIfFileFound(end);
		}

		public Collection<File> allAudioFiles() {
			return audioFilesList.allAudioFiles;
		}

		@Override
		public String prettyPrintText() {
			return audioFilesList.prettyPrintText.toString();
		}
	}

	public static class ListMakerForMultipleSymmetricalSets implements AudioFileListMaker {
		final Collection<File> collectedAudioFiles = new ArrayList<File>();
		final StringBuffer printableAudios = new StringBuffer();

		public ListMakerForMultipleSymmetricalSets(SymmetricalSet[] multipleSets) {
			for (SymmetricalSet each : multipleSets) {
				processEach(each);
			}
		}

		public Collection<File> allAudioFiles() {
			return collectedAudioFiles;
		}

		public String prettyPrintText() {
			return printableAudios.toString();
		}

		private void processEach(SymmetricalSet set) {
			AudioFilesList listMaker = ListMakerForSymmetricalSet.createWith(set);
			collectedAudioFiles.addAll(listMaker.allAudioFiles);
			printableAudios.append("\n" + listMaker.prettyPrintText);
		}
	}

	public static class SimpleListMaker implements AudioFileListMaker {
		private AudioFilesList audioFilesList;

		public SimpleListMaker(List<FundamentalFrequency> allNotes) {
			this.audioFilesList = new AudioFilesList();
			for (FundamentalFrequency each : allNotes) {
				audioFilesList.addIfFileFound(each);
			}
		}

		public Collection<File> allAudioFiles() {
			return audioFilesList.allAudioFiles;
		}

		@Override
		public String prettyPrintText() {
			return audioFilesList.prettyPrintText.toString();
		}
	}
}
