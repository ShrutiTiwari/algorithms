package com.aqua.music.audio.player;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import com.aqua.music.model.Frequency;

class FrequencyListPlayerBasedOnMathSinAngle {
	private final ExecutorService executor = Executors.newSingleThreadExecutor(new AudioThreadFactory());
	private final Semaphore permitToPlay= new Semaphore(1);
	private final AtomicBoolean stopCurrentPlay = new AtomicBoolean(false);
	private final AudioGenerator frequencyAudioGenerator; 
	
	FrequencyListPlayerBasedOnMathSinAngle() {
		this.frequencyAudioGenerator=new AudioGenerator();
	}

	FrequencyListPlayerBasedOnMathSinAngle(int durationInMsec, double vol) {
		this.frequencyAudioGenerator =new AudioGenerator( durationInMsec,vol);
	}

	FrequencyListPlayerBasedOnMathSinAngle setDurationAndVolume(int durationInMsec, double vol) {
		return new FrequencyListPlayerBasedOnMathSinAngle(durationInMsec, vol);
	}

	private void acquireRightToPlay() throws InterruptedException {
		permitToPlay.acquire();
		stopCurrentPlay.set(false);
	}
	
	private boolean continuePlaying() {
		return !stopCurrentPlay.get();
	}

	private void playAsynchronously(Collection<Frequency> frequencyList) {
		int availablePermits = permitToPlay.availablePermits();
		System.out.println("\n availablePermits="+availablePermits);
		if (availablePermits<=0) {
			System.out.println("Play is ongoing!! Issuing stop");
			stopCurrentPlay.set(true);
			frequencyAudioGenerator.stop();
		}
		System.out.println("Playing new list in non-blocking mode...");
		executor.execute(playTask(frequencyList));
	}

	private void playSynchronously(Collection<Frequency> frequencyList) {
		playTask(frequencyList).run();		
	}

	private final Runnable playTask(final Collection<Frequency> frequencyList) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				frequencyAudioGenerator.playFrequencies(frequencyList);
			}
		};
		return task;
	}
	private void releaseRightToPlay() {
		System.out.println("releasing right to play");
		permitToPlay.release();
	}

	static enum PlayMode {
		Synchronous {
			@Override
			public void play(Collection<Frequency> frequencyList, FrequencyListPlayerBasedOnMathSinAngle frequencyListPlayer) {
				frequencyListPlayer.playSynchronously(frequencyList);
			}
		},
		Asynchornous {
			@Override
			public void play(Collection<Frequency> frequencyList, FrequencyListPlayerBasedOnMathSinAngle frequencyListPlayer) {
				frequencyListPlayer.playAsynchronously(frequencyList);

			}
		};
		abstract public void play(final Collection<Frequency> frequencyList, FrequencyListPlayerBasedOnMathSinAngle frequencyListPlayer);
	}
	
	private class AudioGenerator{
		private static final int DEFAULT_MSEC = 1000;
		private static final double DEFAULT_VOL = 0.8;
		private static final float SAMPLE_RATE = 8000f;
		
		private final int msecs;
		private final double vol;
		
		// handle for terminating the blocked running thread
		private volatile SourceDataLine sdl;

		public AudioGenerator() {
			this(DEFAULT_MSEC, DEFAULT_VOL);
		}

		public AudioGenerator(int msecs, double vol) {
			this.msecs = msecs;
			this.vol = vol;
		}

		void playFrequencies(final Collection<Frequency> frequencyList) {
			final float[] frequenciesInHz = new float[frequencyList.size()];
			int i = 0;
			for (Frequency each : frequencyList) {
				frequenciesInHz[i++] = each.frequencyInHz();
			}
			
			try {
				acquireRightToPlay();
				AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
				this.sdl = AudioSystem.getSourceDataLine(af);
				sdl.open(af);
				sdl.start();
				for (float eachFrequency : frequenciesInHz) {
					if (continuePlaying()) {
						throwExceptionForInsaneInput(eachFrequency, msecs, vol);
						byte[] buf = constructBufferForFrequency(eachFrequency);
						sdl.write(buf, 0, buf.length);
					} else {
						System.out.println("oops, ordered to shutdown. terminating..");
						break;
					}
				}
				//sdl.drain();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeStream();
				releaseRightToPlay();
			}
		}

		void stop() {
			if (sdl != null) {
				try {
					sdl.stop();
				} catch (Exception e) {
				} finally {
					closeStream();
				}
			}			
		}
		
		private void closeStream() {
			try {
				sdl.close();
			} catch (Exception e) {
			}
		}
		
		private byte[] constructBufferForFrequency(float frequencyInHz) {
			byte[] frequencyBuffer = new byte[(int) SAMPLE_RATE * msecs / 1000];

			for (int i = 0; i < frequencyBuffer.length; i++) {
				double angle = i / (SAMPLE_RATE / frequencyInHz) * 2.0 * Math.PI;
				frequencyBuffer[i] = (byte) (Math.sin(angle) * 127.0 * vol);
			}

			// shape the front and back 10ms of the wave form
			for (int i = 0; i < SAMPLE_RATE / 100.0 && i < frequencyBuffer.length / 2; i++) {
				frequencyBuffer[i] = (byte) (frequencyBuffer[i] * i / (SAMPLE_RATE / 100.0));
				frequencyBuffer[frequencyBuffer.length - 1 - i] = (byte) (frequencyBuffer[frequencyBuffer.length - 1 - i] * i / (SAMPLE_RATE / 100.0));
			}
			return frequencyBuffer;
		}

		private void throwExceptionForInsaneInput(float hz, int msecs, double vol) {
			if (hz <= 0)
				throw new IllegalArgumentException("Frequency <= 0 hz");
			if (msecs <= 0)
				throw new IllegalArgumentException("Duration <= 0 msecs");
			if (vol > 1.0 || vol < 0.0)
				throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");
		}
	}
}