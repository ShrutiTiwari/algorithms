package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class AudioFileAssemblerUtil
{
    Map<String, File> allAudios = AudioLibrary.allAudios;
    Collection<File> collectedAudioFiles = new ArrayList<File>();
    StringBuffer printableAudios = new StringBuffer();

    public void addFileIfFound( Playable start ) {
        addFileIfFound( start, true );
    }

    public void printAdd( String string ) {
        printableAudios.append( " |||  " );
    }

    void addFileIfFound( Playable note, boolean appendComma ) {
        String code = note.code();
        File audioFile = allAudios.get( code );
        if( audioFile == null ) {
            System.out.println( "No audio found for [" + note + "] in the list of files[" + allAudios.keySet() + "]" );
        } else {
            collectedAudioFiles.add( audioFile );
            printableAudios.append( (appendComma ? ", " : "") + code );
        }
    }

    void addFilesIfFound( Playable[] notes ) {
        for( Playable each : notes ) {
            addFileIfFound( each );
        }
    }
}