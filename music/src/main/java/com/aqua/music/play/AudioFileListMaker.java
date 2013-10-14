package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aqua.music.model.FundamentalFrequency;
import com.aqua.music.model.FundamentalFrequency.ClassicalNote;
import com.aqua.music.model.PredefinedFrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioLibrary.AudioFileAssembler;

public interface AudioFileListMaker {
	boolean NO_COMMA = false;
	boolean WITH_COMMA = true;

	Collection<File> collectedAudioFiles();

	String printableAudios();

	public static class MultipleThaatListMaker implements AudioFileListMaker {
		final Collection<File> collectedAudioFiles = new ArrayList<File>();
		final StringBuffer printableAudios = new StringBuffer();

		public MultipleThaatListMaker(SymmetricalSet[] thaats) {
			for (SymmetricalSet each : thaats) {
				processThaat(each);
			}
		}

		public Collection<File> collectedAudioFiles() {
			return collectedAudioFiles;
		}

		public String printableAudios() {
			return printableAudios.toString();
		}

		private void processThaat(SymmetricalSet thaat) {
			AudioFileAssembler enquer = SymmetricalSetEnqueueListMaker.createEnquereWith(thaat);
			collectedAudioFiles.addAll(enquer.collectedAudioFiles);
			printableAudios.append("\n" + enquer.printableAudios);
		}
	}

	public static class MiddleNoteWithStartEndListMaker implements AudioFileListMaker {
		private AudioFileAssembler assembler;

		public MiddleNoteWithStartEndListMaker(FundamentalFrequency start, FundamentalFrequency end, FundamentalFrequency[] middleNotes) {
			this.assembler = new AudioFileAssembler();
			assembler.addIfFileFound(start);
			assembler.addIfFileFound(middleNotes);
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

		public SimpleListMaker(List<FundamentalFrequency> allNotes) {
			this.assembler = new AudioFileAssembler();
			for (FundamentalFrequency each : allNotes) {
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

	public static class SymmetricalSetEnqueueListMaker implements AudioFileListMaker {
		private AudioFileAssembler assembler;

		public SymmetricalSetEnqueueListMaker(SymmetricalSet symmetricalSet) {
			this.assembler = createEnquereWith(symmetricalSet);
		}

		private static AudioFileAssembler createEnquereWith(SymmetricalSet symmetricalSet) {
			// enqueue ascend sequence
			AudioFileAssembler assembler = new AudioFileAssembler();
			assembler.addIfFileFound(ClassicalNote.SA, NO_COMMA);
			assembler.addIfFileFound(symmetricalSet.ascendNotes());
			assembler.addIfFileFound(ClassicalNote.HIGH_SA, WITH_COMMA);

			assembler.printAdd(" |||  ");

			// enqueue descend sequence
			assembler.addIfFileFound(ClassicalNote.HIGH_SA, NO_COMMA);
			assembler.addIfFileFound(symmetricalSet.descendNotes());
			assembler.addIfFileFound(ClassicalNote.SA, WITH_COMMA);
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
