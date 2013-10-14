package com.aqua.music.items;

import java.io.File;
import java.util.Collection;

import com.aqua.music.model.FundamentalFrequency;
import com.aqua.music.model.PredefinedFrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioFileListMaker;
import com.aqua.music.play.AudioLibrary;

public interface PlayableItem
{
    Collection<File> getPlayList();

    public class SymmetricalPlayableItem implements PlayableItem
    {
        private Collection<File> allAudioFiles = null;
        private int duration = 1;
        private PatternApplicator patternApplier = PatternApplicator.NONE;
        private SymmetricalSet symmetricalSet;

        public static SymmetricalPlayableItem forSet( SymmetricalSet symmetricalSet ) {
            SymmetricalPlayableItem result = new SymmetricalPlayableItem();
            result.symmetricalSet = symmetricalSet;
            return result;
        }

        public SymmetricalPlayableItem andPattern( PatternApplicator pattern ) {
            this.patternApplier = pattern;
            intializePlayList();
            return this;
        }

        public Collection<File> getPlayList() {
            if( allAudioFiles == null ) {
                intializePlayList();
            }
            return allAudioFiles;
        }

        SymmetricalPlayableItem forDuration( int duration ) {
            this.duration = duration;
            return this;
        }

        private void intializePlayList() {
            AudioLibrary.initializeWithGivenSeconds( duration );
            if( patternApplier == PatternApplicator.NONE ) {
                plainAscendDescend();
            }
            patternedAscendDescend();
        }

        private void patternedAscendDescend() {
            FundamentalFrequency[] middleNotes = symmetricalSet.ascendNotes();
            FundamentalFrequency[] input = new FundamentalFrequency[middleNotes.length + 2];
            input[0] = FundamentalFrequency.ClassicalNote.SA;
            input[input.length - 1] = FundamentalFrequency.ClassicalNote.HIGH_SA;
            int i = 1;
            for( FundamentalFrequency each : middleNotes ) {
                input[i++] = each;
            }

            patternApplier.generateAscendAndDescendSequences( input );
            AudioFileListMaker audioFileListMaker = new AudioFileListMaker.SimpleListMaker( patternApplier.allNotes() );
            this.allAudioFiles = audioFileListMaker.allAudioFiles();
        }

        private void plainAscendDescend() {
            AudioFileListMaker audioFileListMaker = new AudioFileListMaker.ListMakerForSymmetricalSet( symmetricalSet );
            System.out.print( "\t[" + audioFileListMaker.prettyPrintText() + "]" );
            this.allAudioFiles = audioFileListMaker.allAudioFiles();
        }
    }
}
