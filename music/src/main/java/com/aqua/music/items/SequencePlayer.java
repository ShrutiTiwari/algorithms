package com.aqua.music.items;

import com.aqua.music.model.Playable;
import com.aqua.music.play.AudioFileListMaker;
import com.aqua.music.play.AudioLibrary;
import com.aqua.music.model.Playable.BaseNotes;


import static com.aqua.music.model.Playable.BaseNotes.*;
public interface SequencePlayer
{
    public String name();

    public void playAscend();

    public void playAscendAndDescend();

    public void playDescend();

    public String type();

    public enum Thaat implements SequencePlayer
    {
        BHAIRAV(RE_, GA, MA, PA, DHA_, NI),
        PURVI(RE_, GA, MA_, PA, DHA_, NI),
        MARWA(RE_, GA, MA_, PA, DHA, NI),
        KALYAN(RE, GA, MA_, PA, DHA, NI),
        BILAWAL(RE, GA, MA, PA, DHA, NI),
        KHAMAJ(RE, GA, MA, PA, DHA, NI_),
        KAFI(RE, GA_, MA, PA, DHA, NI_),
        ASAVARI(RE, GA_, MA, PA, DHA_, NI_),
        BHAIRAVI(RE_, GA_, MA, PA, DHA_, NI_),
        TODI(RE_, GA_, MA_, PA, DHA_, NI);

        private final Playable[] ascendNotes;
        private final Playable[] descendNotes;
        private final AudioFileListMaker audioFilesEnqueuer;

        private Thaat( Playable... ascendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = Util.reverse( ascendNotes );
            audioFilesEnqueuer = new AudioFileListMaker.ThaatEnqueueListMaker( this );
        }

        public void playAscend() {
            AudioLibrary.audioPlayer().playList( new AudioFileListMaker.MiddleNoteWithStartEndListMaker( SA, HIGH_SA, ascendNotes ).collectedAudioFiles() );
        }

        public void playAscendAndDescend() {
            System.out.print( "\t[" + audioFilesEnqueuer.printableAudios() + "]" );
            AudioLibrary.audioPlayer().playList( audioFilesEnqueuer.collectedAudioFiles() );
        }

        public void playDescend() {
            AudioLibrary.audioPlayer().playList( new AudioFileListMaker.MiddleNoteWithStartEndListMaker( HIGH_SA, SA, descendNotes ).collectedAudioFiles() );
        }

        public String type() {
            return "THAAT";
        }

        public class Sequence
        {
            private final String delim = "-";

            private Playable[] ascSequ;
            private Playable[] descSequ;

            private Sequence( String expression ) {
                createPattern( expression );
            }

            private void createPattern( String expression ) {
                String[] splitExp = expression.split( delim );
                int numOfNotesInPattern = splitExp.length;
                System.out.println( numOfNotesInPattern );
                ascSequ = createSequence( SA, HIGH_SA, ascendNotes, numOfNotesInPattern );
                descSequ = createSequence( HIGH_SA, SA, descendNotes, numOfNotesInPattern );
            }

            private Playable[] createSequence( BaseNotes firstNote, BaseNotes lastNote, Playable[] seqNotes, int numOfNotesInPattern ) {
                int numOfDistinctNotes = ascendNotes.length + 2;
                int totalNotesInSeq = (numOfDistinctNotes - numOfNotesInPattern) * numOfNotesInPattern + 2;
                System.out.println( totalNotesInSeq );

                Playable[] resultSequence = new Playable[totalNotesInSeq];

                /*
                 * resultSequence[0]=firstNote; resultSequence[1]=seqNotes[0];
                 */

                int k = 0;
                for( int i = 0; i < numOfDistinctNotes - 1; i++ ) {
                    for( int j = 0; j < numOfNotesInPattern; j++ ) {
                        if( i == 0 ) {
                            if( j == 0 ) {
                                resultSequence[k++] = firstNote;
                            } else {
                                resultSequence[k++] = seqNotes[j - 1];
                            }
                        } else {
                            try {
                                resultSequence[k++] = seqNotes[i + j];
                            } catch( Exception e ) {

                                resultSequence[k - 1] = lastNote;
                            }
                        }
                        System.out.println( (k - 1) + "." + resultSequence[k - 1] );
                    }
                }
                return resultSequence;
            }
        }

        public Playable[] ascendNotes() {
            return ascendNotes;
        }

        public Playable[] descendNotes() {
            return descendNotes;
        }
    }

    public enum Pattern implements SequencePlayer
    {
        Khamaj(NI_),
        Bilawal(NI),
        Dha(DHA);
        private final Playable[] ascendNotes;
        private final Playable[] descendNotes;

        private Pattern( Playable... ascendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = Util.reverse( ascendNotes );
        }

        public void playAscend() {
            Util.play( PA, ascendNotes, HIGH_SA );
        }

        public void playAscendAndDescend() {
            playAscend();
            playDescend();
        }

        public void playDescend() {
            Util.play( HIGH_SA, descendNotes, PA );
        }

        public String type() {
            return "PATTERN";
        }
    }

    public enum SecondYearRaag implements SequencePlayer
    {
        GUJARI_TODI(RE_, GA_, MA_, DHA_, NI),
        BAIRAGI(RE_, MA, PA, NI_),
        SHUDH_SARANG(sequence( RE, MA_, PA, NI ), sequence( NI, DHA, PA, MA_, PA, MA, RE )),
        YAMAN(sequence( RE, GA, MA_, DHA, NI ), sequence( NI, DHA, PA, MA_, GA, RE )),
        PURYA_KALYAN(sequence( RE_, GA, MA_, PA, MA_, DHA, NI ), sequence( NI, DHA, PA, DHA, MA_, PA, GA, MA_, RE_, GA, RE_ )),
        MULTANI(sequence( GA_, MA_, PA, NI ), sequence( NI, DHA_, PA, MA_, GA_, RE_ ));

        private final Playable[] ascendNotes;
        private final Playable[] descendNotes;

        private SecondYearRaag( Playable... ascendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = Util.reverse( ascendNotes );
        }

        private SecondYearRaag( Playable[] ascendNotes, Playable[] descendNotes ) {
            this.ascendNotes = ascendNotes;
            this.descendNotes = descendNotes;
        }

        private static Playable[] sequence( Playable... notes ) {
            return notes;
        }

        public void playAscend() {
            Util.play( SA, ascendNotes, HIGH_SA );
        }

        public void playAscendAndDescend() {
            playAscend();
            playDescend();
        }

        public void playDescend() {
            Util.play( HIGH_SA, descendNotes, SA );
        }

        public String type() {
            return "RAAG";
        }
    }

    static abstract class Util
    {
        static void play( Playable start, Playable[] middleNotes, Playable end ) {
            PlayEnqueuedAudioFiles.play( start, middleNotes, end );
        }

        static Playable[] reverse( Playable... ascendNotes ) {
            int count = ascendNotes.length;
            Playable[] dscendNotes = new Playable[count];
            for( int i = 0; i < count; i++ ) {
                dscendNotes[i] = ascendNotes[count - i - 1];
            }
            return dscendNotes;
        }

        static Playable[] sequence( Playable... notes ) {
            return notes;
        }
    }
}