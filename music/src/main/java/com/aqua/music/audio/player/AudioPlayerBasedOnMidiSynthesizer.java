package com.aqua.music.audio.player;

import java.io.File;
import java.util.Collection;

import com.aqua.music.items.PlayableItem;
import com.aqua.music.model.Frequency;

public class AudioPlayerBasedOnMidiSynthesizer implements Runnable, AudioPlayer
{
    Collection<Frequency> frequencyList;
    private final boolean blockingPlay;
    private final FrequencyListPlayerBasedOnMathSinAngle frequencyListPlayer = new FrequencyListPlayerBasedOnMathSinAngle();

    AudioPlayerBasedOnMidiSynthesizer() {
        this( true );
    }

    AudioPlayerBasedOnMidiSynthesizer( boolean blockingPlay ) {
        this.blockingPlay = blockingPlay;

        System.out.println( "Configured to have blockingPlay[" + blockingPlay + "]" );
    }

    public void play( Collection<Frequency> frequencyList ) {
        this.frequencyList = frequencyList;
        if( blockingPlay ) {
            run();
        } else {
            asyncPlay();
        }
    }

    @Override
    public void play( PlayableItem playableItem ) {
        Collection<Frequency> frequencyList = playableItem.frequencyList();
        play( frequencyList );
    }

    @Override
    public void playList( Collection<File> audioFiles ) {

    }

    public void run() {
        float[] fhz = new float[frequencyList.size()];
        int i = 0;
        for( Frequency each : frequencyList ) {
            fhz[i++] = each.frequencyInHz();
        }
        frequencyListPlayer.startPlaying( fhz );
    }

    public AudioPlayerBasedOnMidiSynthesizer setDurationAndVolume( int durationInMsec, double vol ) {
        frequencyListPlayer.setDurationAndVolume( durationInMsec, vol );
        return this;
    }

    private void asyncPlay() {
        System.out.println( "\n Ordered to play async" );
        frequencyListPlayer.terminate();
        System.out.println( "Playing new list!!" );
        Thread asyncThread = new Thread( this, "PlayerThread" );
        asyncThread.setDaemon( true );
        asyncThread.start();
    }
}