package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.aqua.music.play.Playable.BaseNotes;
import com.aqua.music.play.SequencePlayer.AllThaat;

public interface AudioFileEnquere
{
    boolean NO_COMMA = false;

    Collection<File> collectedAudioFiles();

    String printableAudios();

    static class FileEnquer
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

    public static class MultipleThaatEnqueuer implements AudioFileEnquere
    {
        final Collection<File> collectedAudioFiles = new ArrayList<File>();
        final StringBuffer printableAudios = new StringBuffer();

        public MultipleThaatEnqueuer( AllThaat[] thaats ) {
            for( AllThaat each : thaats ) {
                processThaat( each );
            }
        }

        public Collection<File> collectedAudioFiles() {
            return collectedAudioFiles;
        }

        public String printableAudios() {
            return printableAudios.toString();
        }

        private void processThaat( AllThaat thaat ) {
            FileEnquer enquer = ThaatEnqueuer.createEnquereWith( thaat );
            collectedAudioFiles.addAll( enquer.collectedAudioFiles );
            printableAudios.append( "\n" + enquer.printableAudios );
        }
    }

    public static class StartEndEnquer implements AudioFileEnquere
    {
        private FileEnquer enquer;

        public StartEndEnquer( Playable start, Playable end, Playable[] middleNotes ) {
            this.enquer = new FileEnquer();
            enquer.addFileIfFound( start );
            enquer.addFilesIfFound( middleNotes );
            enquer.addFileIfFound( end );
        }

        public Collection<File> collectedAudioFiles() {
            return enquer.collectedAudioFiles;
        }

        @Override
        public String printableAudios() {
            return enquer.printableAudios.toString();
        }
    }

    public static class ThaatEnqueuer implements AudioFileEnquere
    {
        private FileEnquer enquer;

        public ThaatEnqueuer( AllThaat thaat ) {
            this.enquer = createEnquereWith( thaat );
        }

        private static FileEnquer createEnquereWith( AllThaat thaat ) {
            // enqueue ascend sequence
            FileEnquer enquer = new FileEnquer();
            enquer.addFileIfFound( BaseNotes.SA, NO_COMMA );
            enquer.addFilesIfFound( thaat.ascendNotes() );
            enquer.addFileIfFound( BaseNotes.HIGH_SA );

            enquer.printAdd( " |||  " );

            // enqueue descend sequence
            enquer.addFileIfFound( BaseNotes.HIGH_SA, NO_COMMA );
            enquer.addFilesIfFound( thaat.descendNotes() );
            enquer.addFileIfFound( BaseNotes.SA );
            return enquer;
        }

        public Collection<File> collectedAudioFiles() {
            return enquer.collectedAudioFiles;
        }

        public String printableAudios() {
            return enquer.printableAudios.toString();
        }
    }
}
