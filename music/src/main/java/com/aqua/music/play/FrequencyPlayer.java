package com.aqua.music.play;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.aqua.music.model.Frequency;

public class FrequencyPlayer implements Runnable {
	private final JavaSoundPlayer javaSoundPlayer = new JavaSoundPlayer();
	private final boolean blockingPlay;
	int msecs;
	double vol;
	Collection<Frequency> frequencyList;

	// Synchronises two threads
	private volatile boolean stopCurrentPlay = false;
	private volatile CountDownLatch playOngoing;

	FrequencyPlayer() {
		this(true);
	}

	FrequencyPlayer(boolean blockingPlay) {
		this.blockingPlay = blockingPlay;
		System.out.println("Configured to have blockingPlay[" + blockingPlay + "]");
	}

	public void play(Collection<Frequency> frequencyList) {
		this.frequencyList = frequencyList;
		if (blockingPlay) {
			run();
		} else {
			asyncPlay();
		}
	}

	public void run() {
		try {
			playOngoing = new CountDownLatch(1);
			stopCurrentPlay = false;
			float[] fhz = new float[frequencyList.size()];
			int i = 0;
			for (Frequency each : frequencyList) {
				fhz[i++] = each.frequencyInHz();
			}
			javaSoundPlayer.startPlaying(msecs, vol, fhz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			playOngoing.countDown();
		}
	}

	public FrequencyPlayer setDurationAndVolume(int durationInMsec, double vol) {
		this.msecs = durationInMsec;
		this.vol = vol;
		return this;
	}

	private void asyncPlay() {
		System.out.println("\n Ordered to play async");
		if (playOngoing != null && playOngoing.getCount() == 1) {
			System.out.println("Play is ongoing!! Issuing stop");
			stopCurrentPlay = true;

			javaSoundPlayer.stop();

			while (playOngoing.getCount() != 0) {
				try {
					playOngoing.await(200, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Playing new list!!");
		Thread asyncThread = new Thread(this, "PlayerThread");
		asyncThread.setDaemon(true);
		asyncThread.start();
	}

	private class JavaSoundPlayer {
		private final float SAMPLE_RATE = 8000f;
		private volatile SourceDataLine sdl;

		private byte[] constructBuffer(float hz, int msecs, double vol) {
			byte[] buf = new byte[(int) SAMPLE_RATE * msecs / 1000];

			for (int i = 0; i < buf.length; i++) {
				double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
				buf[i] = (byte) (Math.sin(angle) * 127.0 * vol);
			}

			// shape the front and back 10ms of the wave form
			for (int i = 0; i < SAMPLE_RATE / 100.0 && i < buf.length / 2; i++) {
				buf[i] = (byte) (buf[i] * i / (SAMPLE_RATE / 100.0));
				buf[buf.length - 1 - i] = (byte) (buf[buf.length - 1 - i] * i / (SAMPLE_RATE / 100.0));
			}
			return buf;
		}

		private void startPlaying(int msecs, double vol, float... hz) throws LineUnavailableException {
			AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
			this.sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();
			for (float each : hz) {
				if (!stopCurrentPlay) {
					throwExceptionForInsaneInput(each, msecs, vol);
					byte[] buf = constructBuffer(each, msecs, vol);
					sdl.write(buf, 0, buf.length);
				} else {
					System.out.println("breaking now!");
					break;
				}
			}
			sdl.drain();
			sdl.close();
		}

		private void stop() {
			if (sdl != null) {
				try {
					sdl.stop();
				} catch (Exception e) {

				} finally {

				}
			}
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