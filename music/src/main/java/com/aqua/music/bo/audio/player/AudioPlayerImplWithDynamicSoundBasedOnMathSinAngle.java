package com.aqua.music.bo.audio.player;

import java.util.Collection;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import com.aqua.music.bo.audio.manager.AudioPlayRightsManager;
import com.aqua.music.model.core.DynamicFrequency;

class AudioPlayerImplWithDynamicSoundBasedOnMathSinAngle implements AudioPlayer {
	private static final double DEFAULT_VOL = 0.8;
	private static final float SAMPLE_RATE = 8000f;

	private volatile AudioPlayRightsManager audioPlayRightsManager;

	// handle for terminating the blocked running thread
	private volatile SourceDataLine sdl;
	private final double vol;

	AudioPlayerImplWithDynamicSoundBasedOnMathSinAngle() {
		this(DEFAULT_VOL);
	}

	AudioPlayerImplWithDynamicSoundBasedOnMathSinAngle(double vol) {
		this.vol = vol;
	}

	public void playFrequencies(final Collection<? extends DynamicFrequency> frequencyList) {
		try {
			audioPlayRightsManager.acquireRightToPlay();
			logger.info("acquired right to play");
			AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
			this.sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();
			generateSound(frequencyList);
			// sdl.drain();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream();
			logger.info("releasing right to play");
			audioPlayRightsManager.releaseRightToPlay();
		}
	}

	public void playFrequenciesInLoop(final Collection<? extends DynamicFrequency> frequencyList) {
		try {
			audioPlayRightsManager.acquireRightToPlay();
			logger.info("acquired right to play");
			AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
			this.sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();
			while (!audioPlayRightsManager.stopPlaying()) {
				generateSound(frequencyList);
			}
			// sdl.drain();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream();
			logger.info("releasing right to play");
			audioPlayRightsManager.releaseRightToPlay();
		}
	}

	public final Runnable playTask(final Collection<? extends DynamicFrequency> frequencyList) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				playFrequencies(frequencyList);
			}
		};
		return task;
	}

	@Override
	public Runnable playTaskInLoop(final Collection<? extends DynamicFrequency> frequencyList) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				playFrequenciesInLoop(frequencyList);
			}
		};
		return task;
	}

	public void setAudioPlayRigthsManager(AudioPlayRightsManager audioPlayRightsManager) {
		this.audioPlayRightsManager = audioPlayRightsManager;
	}

	public void stop() {
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

	private byte[] constructBufferForFrequency(float frequencyInHz, int duration) {
		byte[] frequencyBuffer = new byte[(int) SAMPLE_RATE * duration / 1000];

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

	private void generateSound(final Collection<? extends DynamicFrequency> frequencyList) {
		for (final DynamicFrequency each : frequencyList) {
			if (audioPlayRightsManager.stopPlaying()) {
				logger.info("oops, marked to stop..breaking now.");
				break;
			}else if(audioPlayRightsManager.pauseCurrentPlay()){
				while(audioPlayRightsManager.pauseCurrentPlay()&&!audioPlayRightsManager.stopPlaying()){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				logger.info("Pause cleared!");
			}
			final float frequencyInHz = each.frequencyInHz();
			final int duration = each.duration();
			throwExceptionForInsaneInput(frequencyInHz, duration, vol);
			byte[] buf = constructBufferForFrequency(frequencyInHz, duration);
			sdl.write(buf, 0, buf.length);
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
