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
            public AudioPlayCoordinator blockingPlayer() {
                return audioPlayerFactory.blockingFrequencyPlayer();
            }

            @Override
            public AudioPlayCoordinator nonBlockingPlayer() {
                return audioPlayerFactory.nonBlockingFrequencyPlayer();
            }
        },
        VLC_BASED {
            @Override
            public AudioPlayCoordinator blockingPlayer() {
                return audioPlayerFactory.blockingVlcPlayer();
            }

            @Override
            public AudioPlayCoordinator nonBlockingPlayer() {
                return audioPlayerFactory.nonBlockingVlcPlayer();
            }
        };

        
        private static final AudioPlayerFactory audioPlayerFactory= new AudioPlayerFactory(); 
        public abstract AudioPlayCoordinator blockingPlayer();

        public abstract AudioPlayCoordinator nonBlockingPlayer();
        
        
        private static class AudioPlayerFactory
        {
            AudioPlayCoordinator blockingFrequencyPlayer;
            AudioPlayCoordinator blockingVlcPlayer;
            AudioPlayCoordinator nonBlockingFrequencyPlayer;
            AudioPlayCoordinator nonBlockingVlcPlayer;
            
            
            AudioPlayCoordinator blockingFrequencyPlayer() {
                if(blockingFrequencyPlayer==null){
                    blockingFrequencyPlayer=new AudioPlayCoordinator(true, new AudioGeneratorBasedOnMathSinAngle());
                }
                return blockingFrequencyPlayer;
            }

            AudioPlayCoordinator blockingVlcPlayer() {
                if(blockingVlcPlayer==null){
                    AudioLibrary.initializeWithGivenSeconds( 1 );
                    blockingVlcPlayer=new AudioPlayCoordinator(true, new AudioPlayerBasedOnVLC( true ));
                }
                return blockingVlcPlayer;
            }

            AudioPlayCoordinator nonBlockingFrequencyPlayer() {
                if(nonBlockingFrequencyPlayer==null){
                    nonBlockingFrequencyPlayer=new AudioPlayCoordinator(false, new AudioGeneratorBasedOnMathSinAngle());
                }
                return nonBlockingFrequencyPlayer;
            }

            AudioPlayCoordinator nonBlockingVlcPlayer() {
                if(nonBlockingVlcPlayer==null){
                    AudioLibrary.initializeWithGivenSeconds( 1 );
                    nonBlockingVlcPlayer=new AudioPlayCoordinator(false, new AudioPlayerBasedOnVLC( false));
                }
                return nonBlockingVlcPlayer;
            }
        }
    }
}
