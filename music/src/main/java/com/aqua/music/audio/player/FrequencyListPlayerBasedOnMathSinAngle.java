package com.aqua.music.audio.player;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

class FrequencyListPlayerBasedOnMathSinAngle
{
    private final int DEFAULT_MSEC=1000; 
    private final double DEFAULT_VOL=0.8;
    
    
    private int msecs=DEFAULT_MSEC;
    private double vol=DEFAULT_VOL;
    private final float SAMPLE_RATE = 8000f;
    
    private volatile SourceDataLine sdl;
    
    //concurrency variable
    private volatile CountDownLatch playOngoing = null;
    private volatile boolean stopCurrentPlay = false;
    

    FrequencyListPlayerBasedOnMathSinAngle setDurationAndVolume( int durationInMsec, double vol ) {
        this.msecs = durationInMsec;
        this.vol = vol;
        return this;
    }

    void terminate() {
        if( playOngoing != null && playOngoing.getCount() == 1 ) {
            System.out.println( "Play is ongoing!! Issuing stop" );
            this.stopCurrentPlay = true;
            if( sdl != null ) {
                try {
                    sdl.stop();
                } catch( Exception e ) {

                } finally {

                }
            }
            while( playOngoing.getCount() != 0 ) {
                try {
                    playOngoing.await( 200, TimeUnit.MILLISECONDS );
                } catch( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    void startPlaying( float... frequenciesInHz ) {
        try {
            this.playOngoing = new CountDownLatch( 1 );
            this.stopCurrentPlay = false;
            
            AudioFormat af = new AudioFormat( SAMPLE_RATE, 8, 1, true, false );
            this.sdl = AudioSystem.getSourceDataLine( af );
            sdl.open( af );
            sdl.start();
            for( float eachFrequency : frequenciesInHz ) {
                if( !stopCurrentPlay ) {
                    throwExceptionForInsaneInput( eachFrequency, msecs, vol );
                    byte[] buf = constructBufferForFrequency( eachFrequency );
                    sdl.write( buf, 0, buf.length );
                } else {
                    System.out.println( "breaking now!" );
                    break;
                }
            }
            sdl.drain();
            sdl.close();
            
        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            playOngoing.countDown();
        }  
    }
    
    
    private void startPlaying( int msecs, double vol, float... hz ) {
        this.msecs=msecs;
        this.vol=vol;
        startPlaying( hz );
    }

    private byte[] constructBufferForFrequency( float frequencyInHz ) {
        byte[] frequencyBuffer = new byte[(int) SAMPLE_RATE * msecs / 1000];

        for( int i = 0; i < frequencyBuffer.length; i++ ) {
            double angle = i / (SAMPLE_RATE / frequencyInHz) * 2.0 * Math.PI;
            frequencyBuffer[i] = (byte) (Math.sin( angle ) * 127.0 * vol);
        }

        // shape the front and back 10ms of the wave form
        for( int i = 0; i < SAMPLE_RATE / 100.0 && i < frequencyBuffer.length / 2; i++ ) {
            frequencyBuffer[i] = (byte) (frequencyBuffer[i] * i / (SAMPLE_RATE / 100.0));
            frequencyBuffer[frequencyBuffer.length - 1 - i] = (byte) (frequencyBuffer[frequencyBuffer.length - 1 - i] * i / (SAMPLE_RATE / 100.0));
        }
        return frequencyBuffer;
    }
    
    private void throwExceptionForInsaneInput( float hz, int msecs, double vol ) {
        if( hz <= 0 )
            throw new IllegalArgumentException( "Frequency <= 0 hz" );
        if( msecs <= 0 )
            throw new IllegalArgumentException( "Duration <= 0 msecs" );
        if( vol > 1.0 || vol < 0.0 )
            throw new IllegalArgumentException( "Volume out of range 0.0 - 1.0" );
    }
}