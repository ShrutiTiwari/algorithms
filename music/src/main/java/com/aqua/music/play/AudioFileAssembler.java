package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aqua.music.play.Playable.BaseNotes;
import com.aqua.music.play.SequencePlayer.AllThaat;

public interface AudioFileAssembler
{
    boolean NO_COMMA = false;

    Collection<File> collectedAudioFiles();

    String printableAudios();

    public static class MultipleThaatEnqueuer implements AudioFileAssembler
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
            AudioFileAssemblerUtil enquer = ThaatEnqueuer.createEnquereWith( thaat );
            collectedAudioFiles.addAll( enquer.collectedAudioFiles );
            printableAudios.append( "\n" + enquer.printableAudios );
        }
    }

    public static class StartEndEnquer implements AudioFileAssembler
    {
        private AudioFileAssemblerUtil enquer;

        public StartEndEnquer( Playable start, Playable end, Playable[] middleNotes ) {
            this.enquer = new AudioFileAssemblerUtil();
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

    public static class SimpleEnquer implements AudioFileAssembler
    {
        private AudioFileAssemblerUtil enquer;

        public SimpleEnquer( List<Playable> allNotes ) {
            this.enquer = new AudioFileAssemblerUtil();
            for( Playable each : allNotes ) {
                enquer.addFileIfFound( each );
            }
        }

        public Collection<File> collectedAudioFiles() {
            return enquer.collectedAudioFiles;
        }

        @Override
        public String printableAudios() {
            return enquer.printableAudios.toString();
        }
    }

    public static class ThaatEnqueuer implements AudioFileAssembler
    {
        private AudioFileAssemblerUtil enquer;

        public ThaatEnqueuer( AllThaat thaat ) {
            this.enquer = createEnquereWith( thaat );
        }

        private static AudioFileAssemblerUtil createEnquereWith( AllThaat thaat ) {
            // enqueue ascend sequence
            AudioFileAssemblerUtil enquer = new AudioFileAssemblerUtil();
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
