package com.aqua.music.bo.audio.player;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import open.music.api.InstrumentRole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 *
 */
public class BasicNotePlayerWithMathSin implements BasicNotePlayer  {
	private static final double DEFAULT_VOL = 0.8;
	private static final Logger logger = LoggerFactory.getLogger(BasicNotePlayerWithMathSin.class);
	private static final float SAMPLE_RATE = 8000f;
	private static final double vol = DEFAULT_VOL;
	// handle for terminating the blocked running thread
	private volatile SourceDataLine sdl;
	
	public BasicNotePlayerWithMathSin() {
	}

	@Override
	public void finish() {
		sdl.drain();		
	}
	
	@Override
	public void play(final DynamicFrequency each, final int duration) {
		final float frequencyInHz = each.frequencyInHz();
		throwExceptionForInsaneInput(frequencyInHz, vol);
		byte[] buf = constructBufferForFrequency(frequencyInHz, duration);
		sdl.write(buf, 0, buf.length);
	}

	@Override
	public void start(){
		AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
		try {
			this.sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
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

	@Override
	public void tidyup() {
		closeStream();		
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
	private void throwExceptionForInsaneInput(float hz, double vol) {
		if (hz <= 0)
			throw new IllegalArgumentException("Frequency <= 0 hz");
		if (vol > 1.0 || vol < 0.0)
			throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");
	}

	@Override
	public void notifyInstrumentChange(String instrument, InstrumentRole changingInstrument) {
	}

	@Override
	public String[] allInstruments() {
		return new String[]{};
	}
}
