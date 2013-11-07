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
                return audioPlayerFactory.blockingFrequencyPlayer();
            }

            @Override
            public AudioPlayer nonBlockingPlayer() {
                return audioPlayerFactory.nonBlockingFrequencyPlayer();
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
            AudioPlayer blockingFrequencyPlayer;
            AudioPlayer blockingVlcPlayer;
            AudioPlayer nonBlockingFrequencyPlayer;
            AudioPlayer nonBlockingVlcPlayer;
            
            
            AudioPlayer blockingFrequencyPlayer() {
                if(blockingFrequencyPlayer==null){
                    blockingFrequencyPlayer=new AudioPlayerBasedOnFrequencyList();
                }
                return blockingFrequencyPlayer;
            }

            AudioPlayer blockingVlcPlayer() {
                if(blockingVlcPlayer==null){
                    AudioLibrary.initializeWithGivenSeconds( 1 );
                    blockingVlcPlayer=new AudioPlayerBasedOnVLC( true );
                }
                return blockingVlcPlayer;
            }

            AudioPlayer nonBlockingFrequencyPlayer() {
                if(nonBlockingFrequencyPlayer==null){
                    nonBlockingFrequencyPlayer=new AudioPlayerBasedOnFrequencyList(false);
                }
                return nonBlockingFrequencyPlayer;
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
