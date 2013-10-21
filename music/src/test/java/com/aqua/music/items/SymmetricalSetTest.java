package com.aqua.music.items;

import org.junit.Test;

import com.aqua.music.audio.player.AudioLibrary;
import com.aqua.music.audio.player.AudioPlayer.AudioPlayerType;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public class SymmetricalSetTest
{
    public SymmetricalSetTest() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
    }

    //@Test
    public void testKafi() {
        PlayableItem.factory.configureAudioPlayerType( AudioPlayerType.VLC_BASED ).forSet( SymmetricalSet.THAAT_KAFI ).play();
    }

     @Test
    public void testKafiWithPattern() {
        PlayableItem.factory.configureAudioPlayerType( AudioPlayerType.FREQUENCY_BASED ).forSet( SymmetricalSet.THAAT_KAFI ).play();
        SymmetricalPatternApplicator<Frequency> patternApplicator = new SymmetricalPatternApplicator<Frequency>( new int[] { 1, 4,
                3 } );
        PlayableItem.factory.forSet( SymmetricalSet.THAAT_KAFI ).andPattern( patternApplicator ).play();
    }
}
