package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class AudioFileAssemblerUtil
{
    Map<String, File> allAudios = AudioLibrary.allAudios();
    Collection<File> collectedAudioFiles = new ArrayList<File>();
    StringBuffer printableAudios = new StringBuffer();

    public void addFileIfFound( Playable singleNote ) {
        addFileIfFound( singleNote, true );
    }

    public void addFileIfFound( Playable singleNote, boolean appendComma ) {
        if( allAudios == null ) {
            AudioLibrary.initializeWithGivenSeconds( 1 );
            allAudios=AudioLibrary.allAudios();
        }
        
        String code = singleNote.code();
        File audioFile = allAudios.get( code );
        if( audioFile == null ) {
            System.out.println( "No audio found for [" + singleNote + "] in the list of files[" + allAudios.keySet() + "]" );
        } else {
            collectedAudioFiles.add( audioFile );
            printableAudios.append( (appendComma ? ", " : "") + code );
        }
    }

    public void printAdd( String string ) {
        printableAudios.append( " |||  " );
    }

    public void addFilesIfFound( Playable[] notes ) {
        for( Playable each : notes ) {
            addFileIfFound( each );
        }
    }
}