package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aqua.music.model.CommonAscDescPattern;
import com.aqua.music.model.PatternApplier;
import com.aqua.music.model.PredefinedFrequency;
import com.aqua.music.model.PredefinedFrequency.FundamentalNote;
import com.aqua.music.model.PredefinedFrequencySet.Thaat;
import com.aqua.music.play.AudioLibrary.AudioFileAssembler;

public interface AudioFileListMaker {
	boolean NO_COMMA = false;
	boolean WITH_COMMA = true;

	Collection<File> collectedAudioFiles();

	String printableAudios();

	public static class MultipleThaatListMaker implements AudioFileListMaker {
		final Collection<File> collectedAudioFiles = new ArrayList<File>();
		final StringBuffer printableAudios = new StringBuffer();

		public MultipleThaatListMaker(Thaat[] thaats) {
			for (Thaat each : thaats) {
				processThaat(each);
			}
		}

		public Collection<File> collectedAudioFiles() {
			return collectedAudioFiles;
		}

		public String printableAudios() {
			return printableAudios.toString();
		}

		private void processThaat(Thaat thaat) {
			AudioFileAssembler enquer = ThaatEnqueueListMaker.createEnquereWith(thaat);
			collectedAudioFiles.addAll(enquer.collectedAudioFiles);
			printableAudios.append("\n" + enquer.printableAudios);
		}
	}

	public static class MiddleNoteWithStartEndListMaker implements AudioFileListMaker {
		private AudioFileAssembler assembler;

		public MiddleNoteWithStartEndListMaker(PredefinedFrequency start, PredefinedFrequency end, PredefinedFrequency[] middleNotes) {
			this.assembler = new AudioFileAssembler();
			assembler.addIfFileFound(start);
			assembler.addIfFilesFound(middleNotes);
			assembler.addIfFileFound(end);
		}

		public Collection<File> collectedAudioFiles() {
			return assembler.collectedAudioFiles;
		}

		@Override
		public String printableAudios() {
			return assembler.printableAudios.toString();
		}
	}

	public static class SimpleListMaker implements AudioFileListMaker {
		private AudioFileAssembler assembler;

		public SimpleListMaker(List<PredefinedFrequency> allNotes) {
			this.assembler = new AudioFileAssembler();
			for (PredefinedFrequency each : allNotes) {
				assembler.addIfFileFound(each);
			}
		}

		public Collection<File> collectedAudioFiles() {
			return assembler.collectedAudioFiles;
		}

		@Override
		public String printableAudios() {
			return assembler.printableAudios.toString();
		}
	}

	public static class ThaatEnqueueListMaker implements AudioFileListMaker {
		private AudioFileAssembler assembler;

		public ThaatEnqueueListMaker(Thaat thaat) {
			this.assembler = createEnquereWith(thaat);
		}

		private static AudioFileAssembler createEnquereWith(Thaat thaat) {
			// enqueue ascend sequence
			AudioFileAssembler assembler = new AudioFileAssembler();
			assembler.addToAscendIfFileFound(FundamentalNote.SA, NO_COMMA);
			assembler.addToAscendIfFileFound(thaat.ascendNotes());
			assembler.addToAscendIfFileFound(FundamentalNote.HIGH_SA, WITH_COMMA);

			assembler.printAdd(" |||  ");

			// enqueue descend sequence
			assembler.addToDescendIfFileFound(FundamentalNote.HIGH_SA, NO_COMMA);
			assembler.addToDescendIfFileFound(thaat.descendNotes());
			assembler.addToDescendIfFileFound(FundamentalNote.SA, WITH_COMMA);
			return assembler;
		}

		public Collection<File> collectedAudioFiles() {
			return assembler.collectedAudioFiles;
		}

		public String printableAudios() {
			return assembler.printableAudios.toString();
		}
	}
}
