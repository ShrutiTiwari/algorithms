package com.aqua.music.items;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.aqua.music.model.Frequency;
import com.aqua.music.model.Frequency.ClassicalNote;
import com.aqua.music.model.FrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioLibrary;

public interface AudioFileListBuilder {
	boolean NO_COMMA = false;
	boolean WITH_COMMA = true;

	Collection<File> allAudioFiles();

	String prettyPrintText();

	public static class BuilderForSymmetricalSet implements AudioFileListBuilder {
		private AudioFilesList audioFilesList;

		public BuilderForSymmetricalSet(SymmetricalSet symmetricalSet) {
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

	public static class WithMiddleNotesAndStartEndNotes implements AudioFileListBuilder {
		private AudioFilesList audioFilesList;

		public WithMiddleNotesAndStartEndNotes(Frequency[] middleNotes, Frequency start, Frequency end) {
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

	public static class BuilderForMultipleSymmetricalSets implements AudioFileListBuilder {
		final Collection<File> collectedAudioFiles = new ArrayList<File>();
		final StringBuffer printableAudios = new StringBuffer();

		public BuilderForMultipleSymmetricalSets(SymmetricalSet[] multipleSets) {
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
			AudioFilesList listMaker = BuilderForSymmetricalSet.createWith(set);
			collectedAudioFiles.addAll(listMaker.allAudioFiles);
			printableAudios.append("\n" + listMaker.prettyPrintText);
		}
	}

	public static class SimpleListBuilder implements AudioFileListBuilder {
		private AudioFilesList audioFilesList;

		public SimpleListBuilder(List<Frequency> allNotes) {
			this.audioFilesList = new AudioFilesList();
			for (Frequency each : allNotes) {
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
	
    public static class AudioFilesList
    {
        Collection<File> allAudioFiles = new ArrayList<File>();
        StringBuffer prettyPrintText = new StringBuffer();
        Map<String, File> audioLib=AudioLibrary.library();

        public void addIfFileFound( Frequency singleNote ) {
            addIfFileFound( singleNote, true );
        }

        public void addIfFileFound( Frequency singleNote, boolean appendComma ) {
            String code = singleNote.code();
            File audioFile = audioLib.get( code );
            if( audioFile == null ) {
                System.out.println( "No audio found for [" + singleNote + "] in the list of files[" +audioLib.keySet() + "]" );
            } else {
                allAudioFiles.add( audioFile );
                prettyPrintText.append( (appendComma ? ", " : "") + code );
            }
        }

        public void addIfFileFound( Frequency[] notes ) {
            for( Frequency each : notes ) {
                addIfFileFound( each );
            }
        }

        public void addText( String value ) {
            prettyPrintText.append( value );
        }
    }
}
