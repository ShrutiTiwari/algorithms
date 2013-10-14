package com.aqua.music.model;

import static com.aqua.music.model.Frequency.ClassicalNote.DHA;
import static com.aqua.music.model.Frequency.ClassicalNote.DHA_;
import static com.aqua.music.model.Frequency.ClassicalNote.GA;
import static com.aqua.music.model.Frequency.ClassicalNote.GA_;
import static com.aqua.music.model.Frequency.ClassicalNote.MA;
import static com.aqua.music.model.Frequency.ClassicalNote.MA_;
import static com.aqua.music.model.Frequency.ClassicalNote.NI;
import static com.aqua.music.model.Frequency.ClassicalNote.NI_;
import static com.aqua.music.model.Frequency.ClassicalNote.PA;
import static com.aqua.music.model.Frequency.ClassicalNote.RE;
import static com.aqua.music.model.Frequency.ClassicalNote.RE_;

import com.aqua.music.items.PatternApplicator;
import com.aqua.music.items.PlayableItem.AsymmetricalPlayableItem;
import com.aqua.music.items.PlayableItem.SymmetricalPlayableItem;

public interface FrequencySet
{
    public String name();

    public String type();

    public Frequency[] ascendNotes();

    public Frequency[] descendNotes();

    public void playAscendAndDescend();

    public void playAscendAndDescend( PatternApplicator pattern );

    /**
     * 
     * This set uses same set of notes in ascend and descend
     * 
     * @author shruti.tiwari
     * 
     */
    public enum SymmetricalSet implements FrequencySet
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

        private final Frequency[] ascendNotes;
        private final Frequency[] descendNotes;

        private SymmetricalSet( Frequency... ascendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = Util.reverse( ascendNotes );
        }

        public void playAscendAndDescend() {
            SymmetricalPlayableItem.forSet( this ).play();
        }

        public void playAscendAndDescend( PatternApplicator pattern ) {
            SymmetricalPlayableItem.forSet( this ).andPattern( pattern ).play();
        }

        public String type() {
            return "THAAT";
        }

        public Frequency[] ascendNotes() {
            return ascendNotes;
        }

        public Frequency[] descendNotes() {
            return descendNotes;
        }

        public void nonblockingPlayAscendAndDescend() {
            SymmetricalPlayableItem.forSet( this ).nonblockingPlay();
        }
    }

    /**
     * This set uses different set of notes in ascend and descend
     * 
     * @author shruti.tiwari
     * 
     */
    public enum AssymmericalSet implements FrequencySet
    {
        RAAG2_SHUDH_SARANG(sequence( RE, MA_, PA, NI ), sequence( NI, DHA, PA, MA_, PA, MA, RE )),
        RAAG2_YAMAN(sequence( RE, GA, MA_, DHA, NI ), sequence( NI, DHA, PA, MA_, GA, RE )),
        RAAG2_PURYA_KALYAN(sequence( RE_, GA, MA_, PA, MA_, DHA, NI ), sequence( NI, DHA, PA, DHA, MA_, PA, GA, MA_, RE_, GA, RE_ )),
        RAAG2_MULTANI(sequence( GA_, MA_, PA, NI ), sequence( NI, DHA_, PA, MA_, GA_, RE_ ));

        private final Frequency[] ascendNotes;
        private final Frequency[] descendNotes;

        private AssymmericalSet( Frequency... ascendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = Util.reverse( ascendNotes );
        }

        private AssymmericalSet( Frequency[] ascendNotes, Frequency[] descendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = descendNotes;
        }

        private static Frequency[] sequence( Frequency... notes ) {
            return notes;
        }

        public void playAscendAndDescend() {
            AsymmetricalPlayableItem.forSet( this ).play();
        }

        public String type() {
            return "RAAG";
        }

        @Override
        public Frequency[] ascendNotes() {
            return ascendNotes;
        }

        @Override
        public Frequency[] descendNotes() {
            return descendNotes;
        }

        @Override
        public void playAscendAndDescend( PatternApplicator pattern ) {

        }
    }

    static abstract class Util
    {
        static Frequency[] reverse( Frequency... ascendNotes ) {
            int count = ascendNotes.length;
            Frequency[] dscendNotes = new Frequency[count];
            for( int i = 0; i < count; i++ ) {
                dscendNotes[i] = ascendNotes[count - i - 1];
            }
            return dscendNotes;
        }
    }
}