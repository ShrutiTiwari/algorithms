package com.aqua.music.audio.player;

import java.io.File;
import java.util.Collection;

import com.aqua.music.items.PlayableItem;
import com.aqua.music.model.Frequency;

public interface AudioPlayer
{
    public void play( PlayableItem playableItem );

    void play( Collection<Frequency> collectedFrequencies );

    void playList( Collection<File> audioFiles );

    public enum AudioPlayerType
    {
        FREQUENCY_BASED {
            @Override
            public AudioPlayer blockingPlayer() {
                return audioPlayerFactory.blockingDynamicPlayer();
            }

            @Override
            public AudioPlayer nonBlockingPlayer() {
                return audioPlayerFactory.nonBlockingDynamicPlayer();
            }
        },
        VLC_BASED {
            @Override
            public AudioPlayer blockingPlayer() {
                return audioPlayerFactory.blockingVlcPlayer();
            }

            @Override
            public AudioPlayer nonBlockingPlayer() {
                return audioPlayerFactory.nonBlockingVlcPlayer();
            }
        };

        
        private static final AudioPlayerFactory audioPlayerFactory= new AudioPlayerFactory(); 
        public abstract AudioPlayer blockingPlayer();

        public abstract AudioPlayer nonBlockingPlayer();
        
        
        private static class AudioPlayerFactory
        {
            AudioPlayer blockingDynamicPlayer;
            AudioPlayer blockingVlcPlayer;
            AudioPlayer nonBlockingDynamicPlayer;
            AudioPlayer nonBlockingVlcPlayer;
            
            
            AudioPlayer blockingDynamicPlayer() {
                if(blockingDynamicPlayer==null){
                    blockingDynamicPlayer=new AudioPlayerBasedOnFrequencyList();
                }
                return blockingDynamicPlayer;
            }

            AudioPlayer blockingVlcPlayer() {
                if(blockingVlcPlayer==null){
                    AudioLibrary.initializeWithGivenSeconds( 1 );
                    blockingVlcPlayer=new AudioPlayerBasedOnVLC( true );
                }
                return blockingVlcPlayer;
            }

            AudioPlayer nonBlockingDynamicPlayer() {
                if(nonBlockingDynamicPlayer==null){
                    nonBlockingDynamicPlayer=new AudioPlayerBasedOnFrequencyList(false);
                }
                return nonBlockingDynamicPlayer;
            }

            AudioPlayer nonBlockingVlcPlayer() {
                if(nonBlockingVlcPlayer==null){
                    AudioLibrary.initializeWithGivenSeconds( 1 );
                    nonBlockingVlcPlayer=new AudioPlayerBasedOnVLC(false);
                }
                return nonBlockingVlcPlayer;
            }
        }
    }
}
