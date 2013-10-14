package com.aqua.music.model;

import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.DHA;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.DHA_;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.GA;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.GA_;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.HIGH_SA;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.MA;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.MA_;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.NI;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.NI_;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.PA;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.RE;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.RE_;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.SA;

import com.aqua.music.model.FundamentalFrequency;
import com.aqua.music.play.AudioFileListMaker;
import com.aqua.music.play.AudioLibrary;

public interface PredefinedFrequencySet
{
    public String name();

    public String type();

    public FundamentalFrequency[] ascendNotes();

    public FundamentalFrequency[] descendNotes();

    public void playAscendAndDescend();

    public void playAscendAndDescend( PatternApplier pattern );

    /**
     * 
     * This set uses same set of notes in ascend and descend
     * @author shruti.tiwari
     *
     */
    public enum SymmetricalSet implements PredefinedFrequencySet
    {
        THAAT_BHAIRAV(RE_, GA, MA, PA, DHA_, NI),
        THAAT_PURVI(RE_, GA, MA_, PA, DHA_, NI),
        THAAT_MARWA(RE_, GA, MA_, PA, DHA, NI),
        THAAT_KALYAN(RE, GA, MA_, PA, DHA, NI),
        THAAT_BILAWAL(RE, GA, MA, PA, DHA, NI),
        THAAT_KHAMAJ(RE, GA, MA, PA, DHA, NI_),
        THAAT_KAFI(RE, GA_, MA, PA, DHA, NI_),
        THAAT_ASAVARI(RE, GA_, MA, PA, DHA_, NI_),
        THAAT_BHAIRAVI(RE_, GA_, MA, PA, DHA_, NI_),
        THAAT_TODI(RE_, GA_, MA_, PA, DHA_, NI),
        RAAG2_BAIRAGI(RE_, MA, PA, NI_),
        RAAG2_GUJARI_TODI(RE_, GA_, MA_, DHA_, NI);

        private final FundamentalFrequency[] ascendNotes;
        private final FundamentalFrequency[] descendNotes;

        private SymmetricalSet( FundamentalFrequency... ascendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = Util.reverse( ascendNotes );
        }

        public void playAscendAndDescend() {
            AudioLibrary.audioPlayer().play( SymmetricalPlayableItem.forSet( this ) );
        }

        public void playAscendAndDescend( PatternApplier pattern ) {
            AudioLibrary.audioPlayer().play( SymmetricalPlayableItem.forSet( this ).andPattern( pattern ) );
        }

        public String type() {
            return "THAAT";
        }

        public FundamentalFrequency[] ascendNotes() {
            return ascendNotes;
        }

        public FundamentalFrequency[] descendNotes() {
            return descendNotes;
        }
    }

    /**
     * This set uses different set of notes in ascend and descend
     * @author shruti.tiwari
     *
     */
    public enum AssymmericalSet implements PredefinedFrequencySet
    {
        RAAG2_SHUDH_SARANG(sequence( RE, MA_, PA, NI ), sequence( NI, DHA, PA, MA_, PA, MA, RE )),
        RAAG2_YAMAN(sequence( RE, GA, MA_, DHA, NI ), sequence( NI, DHA, PA, MA_, GA, RE )),
        RAAG2_PURYA_KALYAN(sequence( RE_, GA, MA_, PA, MA_, DHA, NI ), sequence( NI, DHA, PA, DHA, MA_, PA, GA, MA_, RE_, GA, RE_ )),
        RAAG2_MULTANI(sequence( GA_, MA_, PA, NI ), sequence( NI, DHA_, PA, MA_, GA_, RE_ ));

        private final FundamentalFrequency[] ascendNotes;
        private final FundamentalFrequency[] descendNotes;

        private AssymmericalSet( FundamentalFrequency... ascendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = Util.reverse( ascendNotes );
        }

        private AssymmericalSet( FundamentalFrequency[] ascendNotes, FundamentalFrequency[] descendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = descendNotes;
        }

        private static FundamentalFrequency[] sequence( FundamentalFrequency... notes ) {
            return notes;
        }

        public void playAscendAndDescend() {
            Util.play( SA, ascendNotes, HIGH_SA );
            Util.play( HIGH_SA, descendNotes, SA );
        }

        public String type() {
            return "RAAG";
        }

        @Override
        public FundamentalFrequency[] ascendNotes() {
            return ascendNotes;
        }

        @Override
        public FundamentalFrequency[] descendNotes() {
            return descendNotes;
        }

        @Override
        public void playAscendAndDescend( PatternApplier pattern ) {
            // TODO Auto-generated method stub

        }
    }

    static abstract class Util
    {
        static void play( FundamentalFrequency start, FundamentalFrequency[] middleNotes, FundamentalFrequency end ) {
            AudioLibrary.audioPlayer().playList(
                    new AudioFileListMaker.MiddleNoteWithStartEndListMaker( start, end, middleNotes ).collectedAudioFiles() );
        }

        static FundamentalFrequency[] reverse( FundamentalFrequency... ascendNotes ) {
            int count = ascendNotes.length;
            FundamentalFrequency[] dscendNotes = new FundamentalFrequency[count];
            for( int i = 0; i < count; i++ ) {
                dscendNotes[i] = ascendNotes[count - i - 1];
            }
            return dscendNotes;
        }

        static FundamentalFrequency[] sequence( FundamentalFrequency... notes ) {
            return notes;
        }
    }
}