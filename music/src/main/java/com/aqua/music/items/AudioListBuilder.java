package com.aqua.music.items;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.aqua.music.model.Frequency;
import com.aqua.music.model.Frequency.ClassicalNote;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioLibrary;

public interface AudioListBuilder {
	boolean NO_COMMA = false;
	boolean WITH_COMMA = true;

	Collection<File> allAudioFiles();
	String prettyPrintText();
	public Collection<Frequency> finalFrequencySequence();

	public static class BuilderForSymmetricalSet implements AudioListBuilder {
		private AudioFilesList audioFilesList;
		private FrequencyList frequencyList;
		
		public BuilderForSymmetricalSet(FrequencySet frequencySet) {
			this.frequencyList=generate(frequencySet);
			this.audioFilesList = new AudioFilesList(frequencyList);
		}

		private static FrequencyList generate(FrequencySet frequencySet) {
			// enqueue ascend sequence
			FrequencyList frequencyList = new FrequencyList();
			frequencyList.add(ClassicalNote.SA, NO_COMMA);
			frequencyList.add(frequencySet.ascendNotes());
			frequencyList.add(ClassicalNote.HIGH_SA, WITH_COMMA);

			frequencyList.addText(" |||  ");

			// enqueue descend sequence
			frequencyList.add(ClassicalNote.HIGH_SA, NO_COMMA);
			frequencyList.add(frequencySet.descendNotes());
			frequencyList.add(ClassicalNote.SA, WITH_COMMA);
			return frequencyList;
		}
		
		public Collection<File> allAudioFiles() {
			return audioFilesList.allAudioFiles;
		}

		public String prettyPrintText() {
			return frequencyList.prettyPrintText.toString();
		}
		
		public Collection<Frequency> finalFrequencySequence() {
			return frequencyList.allNotes;
		}
	}

	public static class WithMiddleNotesAndStartEndNotes implements AudioListBuilder {
		private AudioFilesList audioFilesList;
		private FrequencyList frequencyList;

		public WithMiddleNotesAndStartEndNotes(Frequency[] middleNotes, Frequency start, Frequency end) {
			this.frequencyList=new FrequencyList();
			frequencyList.add(start);
			frequencyList.add(middleNotes);
			frequencyList.add(end);
			this.audioFilesList = new AudioFilesList(frequencyList);
		}

		public Collection<File> allAudioFiles() {
			return audioFilesList.allAudioFiles;
		}
		
		public Collection<Frequency> finalFrequencySequence(){
			return frequencyList.allNotes;
		}

		@Override
		public String prettyPrintText() {
			return frequencyList.prettyPrintText.toString();
		}
	}

	public static class BuilderForMultipleSymmetricalSets implements AudioListBuilder {
		final Collection<Frequency> collectedFrequencies = new ArrayList<Frequency>();
		final Collection<File> collectedAudioFile = new ArrayList<File>();
		final StringBuffer printableAudios = new StringBuffer();

		public BuilderForMultipleSymmetricalSets(SymmetricalSet[] multipleSets) {
			for (SymmetricalSet each : multipleSets) {
				processEach(each);
			}
		}

		public Collection<Frequency> finalFrequencySequence(){
			return collectedFrequencies;
		}
		
		public Collection<File> allAudioFiles() {
			return collectedAudioFile;
		}

		public String prettyPrintText() {
			return printableAudios.toString();
		}

		private void processEach(SymmetricalSet set) {
			FrequencyList listMaker = BuilderForSymmetricalSet.generate(set);
			collectedFrequencies.addAll(listMaker.allNotes);
			printableAudios.append("\n" + listMaker.prettyPrintText);
			Collection<File> allAudioFiles = new AudioFilesList(listMaker).allAudioFiles;
			this.collectedAudioFile.addAll(allAudioFiles);
		}
	}

	public static class SimpleListBuilder implements AudioListBuilder {
		private AudioFilesList audioFilesList;
		private FrequencyList frequencyList;

		public SimpleListBuilder(Collection<Frequency> allNotes) {
			this.frequencyList=new FrequencyList();
			for (Frequency each : allNotes) {
				frequencyList.add(each);
			}
			this.audioFilesList = new AudioFilesList(frequencyList);
			
		}

		
		public Collection<Frequency> finalFrequencySequence(){
			return frequencyList.allNotes;
		}
		
		public Collection<File> allAudioFiles() {
			return audioFilesList.allAudioFiles;
		}

		@Override
		public String prettyPrintText() {
			return frequencyList.prettyPrintText.toString();
		}
	}

    public static class FrequencyList
    {
    	Collection<Frequency> allNotes = new ArrayList<Frequency>();
    	
        StringBuffer prettyPrintText = new StringBuffer();

        public void add( Frequency singleNote ) {
            add( singleNote, true );
        }

        public void add( Frequency singleNote, boolean appendComma ) {
        	allNotes.add( singleNote );
                prettyPrintText.append( (appendComma ? ", " : "") + singleNote.fileCode() );
        }

        public void add( Frequency[] notes ) {
            for( Frequency each : notes ) {
                add( each );
            }
        }

        public void addText( String value ) {
            prettyPrintText.append( value );
        }
    }
	
    public static class AudioFilesList
    {
        Collection<File> allAudioFiles = new ArrayList<File>();
        StringBuffer prettyPrintText = new StringBuffer();
        Map<String, File> audioLib=AudioLibrary.library();

        
        public AudioFilesList(FrequencyList frequencyList) {
        	for (Frequency each:frequencyList.allNotes){
        		addIfFileFound( each );
        	}
		}
        
        public void addIfFileFound( Frequency singleNote ) {
            addIfFileFound( singleNote, true );
        }

        public void addIfFileFound( Frequency singleNote, boolean appendComma ) {
            String code = singleNote.fileCode();
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
