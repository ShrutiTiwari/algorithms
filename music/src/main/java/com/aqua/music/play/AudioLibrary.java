package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aqua.music.model.FundamentalFrequency;
import com.aqua.music.play.AudioPlayer.VLCPlayer;

public class AudioLibrary
{
    private static Map<String, File> allAudios;
    private static final String AUDIO_LIBRARY = "recognition-puzzles/";
    private static AudioPlayer audioPlayer = new VLCPlayer();
    private static final String FOLDER_PREFIX = "note-recognition-";

    public static void addFileIfFound( List<File> audioFiles, FundamentalFrequency note ) {
        File audioFile = allAudios.get( note.code() );
        if( audioFile == null ) {
            System.out.println( "No audio found for [" + note + "] in the list of files[" + allAudios.keySet() + "]" );
        } else {
            audioFiles.add( audioFile );
        }
    }

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
    public static class AudioFilesList {
    	Collection<File> allAudioFiles = new ArrayList<File>();
    	StringBuffer prettyPrintText = new StringBuffer();

    	public void addIfFileFound(FundamentalFrequency singleNote) {
    		addIfFileFound(singleNote, true);
    	}
    	
    	public void addIfFileFound(FundamentalFrequency singleNote, boolean appendComma) {
    		initializeAudioIfNotAlready();
    		String code = singleNote.code();
    		File audioFile = allAudios.get(code);
    		if (audioFile == null) {
    			System.out.println("No audio found for [" + singleNote
    					+ "] in the list of files[" + allAudios.keySet() + "]");
    		} else {
    			allAudioFiles.add(audioFile);
    			prettyPrintText.append((appendComma ? ", " : "") + code);
    		}
    	}
    	
    	public void addIfFileFound(FundamentalFrequency[] notes) {
    		for (FundamentalFrequency each : notes) {
    			addIfFileFound(each);
    		}
    	}
    	
    	public void addText(String value) {
    		prettyPrintText.append(value);
    	}
    }

}