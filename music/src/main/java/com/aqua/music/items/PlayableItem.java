package com.aqua.music.items;

import java.util.Collection;

import com.aqua.music.audio.player.AudioPlayer.AudioPlayerType;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public interface PlayableItem
{
    public PlayableItem andPattern( PatternApplicator patternApplicator );

    public void play();

    public Collection<Frequency> frequencyList();

    PlayableItemFactory factory = new PlayableItemFactory();

    public static class PlayableItemFactory
    {
        private AudioPlayerType audioPlayerType;
        private boolean blocking = true;

        public void andNonBlocking() {
            this.blocking = false;
        }

        public PlayableItemFactory configureAudioPlayerType( AudioPlayerType audioPlayerType ) {
            this.audioPlayerType = audioPlayerType;
            return this;
        }

        public PlayableItem forSet( FrequencySet frequencySet ) {
            if( frequencySet instanceof SymmetricalSet ) {
                return new SymmetricalPlayableItem( frequencySet, audioPlayerType, blocking );
            }
            return new AsymmetricalPlayableItem( frequencySet, audioPlayerType, blocking );
        }

    }
}
