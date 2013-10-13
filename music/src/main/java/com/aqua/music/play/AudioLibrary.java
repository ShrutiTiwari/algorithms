package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aqua.music.model.PredefinedFrequency;
import com.aqua.music.play.AudioPlayer.VLCPlayer;

public class AudioLibrary
{
    private static Map<String, File> allAudios;
    private static AudioPlayer audioPlayer = new VLCPlayer();
    private static final String AUDIO_LIBRARY = "recognition-puzzles/";
    private static final String FOLDER_PREFIX = "note-recognition-";

    public static Map<String, File> allAudios() {
    	initializeAudioIfNotAlready();
        return allAudios;
    }

    public static AudioPlayer audioPlayer() {
    	initializeAudioIfNotAlready();
        return audioPlayer;
    }
    
    public static void initializeWithGivenSeconds( int seconds ) {
        allAudios = findAllNotesAudios( seconds );
        System.out.println( "initializaed with [" + allAudios + "]" );
    }

    public static void addFileIfFound( List<File> audioFiles, PredefinedFrequency note ) {
        File audioFile = allAudios.get( note.code() );
        if( audioFile == null ) {
            System.out.println( "No audio found for [" + note + "] in the list of files[" + allAudios.keySet() + "]" );
        } else {
            audioFiles.add( audioFile );

        }
    }

    private static String directoryName( int duration ) {
        return FOLDER_PREFIX + duration + "s";
    }
    
    private static Map<String, File> findAllNotesAudios( int duration ) {
        String dir = AUDIO_LIBRARY + directoryName( duration ) + "/";
        String path = Thread.currentThread().getContextClassLoader().getResource( dir ).getPath();
        File sourceDirectory = new File( path );
        System.out.println( sourceDirectory.getAbsolutePath() );
        Map<String, File> allNoteAudios = new LinkedHashMap<String, File>();
        for( File audioFile : sourceDirectory.listFiles() ) {
            String noteName = audioFile.getName().replace( ".m4a", "" );
            allNoteAudios.put( noteName, audioFile );
        }
        return allNoteAudios;
    }
    
	private static void initializeAudioIfNotAlready() {
		if (allAudios == null) {
			initializeWithGivenSeconds(1);
		}
	}
    public static class AudioFileAssembler {
    	Collection<File> collectedAudioFiles = new ArrayList<File>();
    	StringBuffer printableAudios = new StringBuffer();

    	Collection<File> collectedAscendFiles = new ArrayList<File>();
    	Collection<File> collectedDescendFiles = new ArrayList<File>();
    	
    	public void addIfFileFound(PredefinedFrequency singleNote) {
    		addIfFileFound(singleNote, true);
    	}
    	
    	public void addToAscendIfFileFound(PredefinedFrequency singleNote, boolean appendComma) {
    		addToGivenCollectionIfFileFound(collectedAscendFiles, singleNote, appendComma);	
    	}
    	
    	public void addToDescendIfFileFound(PredefinedFrequency singleNote, boolean appendComma) {
    		addToGivenCollectionIfFileFound(collectedDescendFiles, singleNote, appendComma);	
    	}
    	

    	public void addIfFileFound(PredefinedFrequency singleNote, boolean appendComma) {
    		initializeAudioIfNotAlready();
    		String code = singleNote.code();
    		File audioFile = allAudios.get(code);
    		if (audioFile == null) {
    			System.out.println("No audio found for [" + singleNote
    					+ "] in the list of files[" + allAudios.keySet() + "]");
    		} else {
    			collectedAudioFiles.add(audioFile);
    			printableAudios.append((appendComma ? ", " : "") + code);
    		}
    	}
    	
    	private void addToGivenCollectionIfFileFound(Collection<File> givenCollection, PredefinedFrequency singleNote, boolean appendComma ) {
    		initializeAudioIfNotAlready();
    		String code = singleNote.code();
    		File audioFile = allAudios.get(code);
    		if (audioFile == null) {
    			System.out.println("No audio found for [" + singleNote
    					+ "] in the list of files[" + allAudios.keySet() + "]");
    		} else {
    			givenCollection.add(audioFile);
    			printableAudios.append((appendComma ? ", " : "") + code);
    		}
    	}

    	public void addIfFilesFound(PredefinedFrequency[] notes) {
    		for (PredefinedFrequency each : notes) {
    			addIfFileFound(each);
    		}
    	}
    	
    	public void addToAscendIfFileFound(PredefinedFrequency[] notes) {
    		for (PredefinedFrequency each : notes) {
    			addToAscendIfFileFound(each, true);
    		}
    	}
    	
    	public void addToDescendIfFileFound(PredefinedFrequency[] notes) {
    		for (PredefinedFrequency each : notes) {
    			addToDescendIfFileFound(each, true);
    		}
    	}

    	public void printAdd(String value) {
    		printableAudios.append(value);
    	}
    }

}