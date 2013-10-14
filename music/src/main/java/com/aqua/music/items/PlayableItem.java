package com.aqua.music.items;

import static com.aqua.music.model.Frequency.ClassicalNote.HIGH_SA;
import static com.aqua.music.model.Frequency.ClassicalNote.SA;

import java.io.File;
import java.util.Collection;

import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.AssymmericalSet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioLibrary;

public interface PlayableItem
{
    public void nonblockingPlay();

    public void play();

    public class SymmetricalPlayableItem implements PlayableItem
    {
        /**
         * caution: this variable shouldn't be used until initialised, properly.
         */
        private Collection<File> allAudioFiles = null;
        
        
        private int duration = 1;
        private PatternApplicator patternApplicator = PatternApplicator.NONE;
        private final SymmetricalSet symmetricalSet;

        public SymmetricalPlayableItem( SymmetricalSet symmetricalSet ) {
            this.symmetricalSet = symmetricalSet;
        }

        public static SymmetricalPlayableItem forSet( SymmetricalSet symmetricalSet ) {
            return new SymmetricalPlayableItem( symmetricalSet );
        }

        public SymmetricalPlayableItem andPattern( PatternApplicator patternApplicator ) {
            this.patternApplicator = patternApplicator;
            intializePlayList();
            return this;
        }

        public void nonblockingPlay() {
            AudioLibrary.audioPlayer().nonblockingPlay( this.playList() );
        }

        public void play() {
            System.out.println( patternApplicator.prettyPrintTextForAscDesc() );
            AudioLibrary.audioPlayer().playList( this.playList() );
        }

        SymmetricalPlayableItem forDuration( int duration ) {
            this.duration = duration;
            return this;
        }

        private void intializePlayList() {
            AudioLibrary.initializeWithGivenSeconds( duration );
            if( patternApplicator == PatternApplicator.NONE ) {
                plainAscendDescend();
            } else {
                patternedAscendDescend();
            }
        }

        private void patternedAscendDescend() {
            Frequency[] middleNotes = symmetricalSet.ascendNotes();
            Frequency[] input = new Frequency[middleNotes.length + 2];
            input[0] = Frequency.ClassicalNote.SA;
            input[input.length - 1] = Frequency.ClassicalNote.HIGH_SA;
            int i = 1;
            for( Frequency each : middleNotes ) {
                input[i++] = each;
            }

            patternApplicator.generateAscendAndDescendSequences( input );
            AudioFileListBuilder audioFileListMaker = new AudioFileListBuilder.SimpleListBuilder( patternApplicator.allNotes() );
            this.allAudioFiles = audioFileListMaker.allAudioFiles();
        }

        private void plainAscendDescend() {
            AudioFileListBuilder audioFileListBuilder = new AudioFileListBuilder.BuilderForSymmetricalSet( symmetricalSet );
            System.out.print( "\t[ Plain ascend-descend:: " + audioFileListBuilder.prettyPrintText() + "]" );
            this.allAudioFiles = audioFileListBuilder.allAudioFiles();
        }

        private Collection<File> playList() {
            if( allAudioFiles == null ) {
                intializePlayList();
            }
            return allAudioFiles;
        }
    }

    public class AsymmetricalPlayableItem implements PlayableItem
    {
        private final FrequencySet frequencySet;
        private Collection<File> allAudioFiles = null;

        private AsymmetricalPlayableItem( FrequencySet frequencySet ) {
            this.frequencySet = frequencySet;
        }

        @Override
        public void nonblockingPlay() {}

        @Override
        public void play() {
            createAudioList( SA, frequencySet.ascendNotes(), HIGH_SA );
            createAudioList( HIGH_SA, frequencySet.descendNotes(), SA );
            AudioLibrary.audioPlayer().playList( allAudioFiles );
        }

        void createAudioList( Frequency start, Frequency[] middleNotes, Frequency end ) {
            AudioFileListBuilder.WithMiddleNotesAndStartEndNotes audioFileListBuilder = new AudioFileListBuilder.WithMiddleNotesAndStartEndNotes(
                    middleNotes, start, end );
            allAudioFiles.addAll( audioFileListBuilder.allAudioFiles() );
            audioFileListBuilder.allAudioFiles();
        }

        public static AsymmetricalPlayableItem forSet( AssymmericalSet frequencySet ) {
            return new AsymmetricalPlayableItem( frequencySet );
        }
    }
}
