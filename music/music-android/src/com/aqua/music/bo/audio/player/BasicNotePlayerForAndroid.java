package com.aqua.music.bo.audio.player;

import open.music.api.InstrumentRole;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class BasicNotePlayerForAndroid implements BasicNotePlayer {
	private static final double DEFAULT_VOL = 0.8;
	private static final float SAMPLE_RATE = 8000f;
	private static final double vol = DEFAULT_VOL;
	// handle for terminating the blocked running thread
	private volatile AudioTrack audioTrack;

	public BasicNotePlayerForAndroid() {
	}

	@Override
	public void finish() {
		closeStream();
		Log.i("ICM", "finished player");
	}

	@Override
	public void play(final DynamicFrequency each, final int duration) {
		final float frequencyInHz = each.frequencyInHz();
		throwExceptionForInsaneInput(frequencyInHz, vol);
		playSound(frequencyInHz, numOfSamples(duration));
	}

	int numOfSamples(final int duration) {
		return duration * ((int) SAMPLE_RATE);
	}

	void playSound(float frequencyInHz, int numSamples) {
		byte[] buf = constructBufferForFrequency(frequencyInHz, numSamples);
		audioTrack.write(buf, 0, buf.length);
		Log.i("ICM", "about to play[" + frequencyInHz + "]");
		audioTrack.play();
		Log.i("ICM", "playd[" + frequencyInHz + "]");
	}

	@Override
	public void stop() {
		if (audioTrack != null) {
			try {
				audioTrack.pause();
				audioTrack.flush();
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
			audioTrack.release();
		} catch (Exception e) {
		}
	}

	byte[] constructBufferForFrequency(float frequencyInHz, int numSamples) {

		double sample[] = new double[numSamples];
		byte generatedSnd[] = new byte[2 * numSamples];
		// fill out the array
		for (int i = 0; i < numSamples; ++i) {
			sample[i] = Math.sin(2 * Math.PI * i
					/ (SAMPLE_RATE / frequencyInHz));
		}

		// convert to 16 bit pcm sound array
		// assumes the sample buffer is normalised.
		int idx = 0;

		for (final double dVal : sample) {
			// scale to maximum amplitude
			final short val = (short) ((dVal * 32767));
			// in 16 bit wav PCM, first byte is the low order byte
			generatedSnd[idx++] = (byte) (val & 0x00ff);
			generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

		}
		return generatedSnd;
	}

	private void throwExceptionForInsaneInput(float hz, double vol) {
		if (hz <= 0)
			throw new IllegalArgumentException("Frequency <= 0 hz");
		if (vol > 1.0 || vol < 0.0)
			throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");
	}

	@Override
	public void notifyInstrumentChange(String instrument,
			InstrumentRole changingInstrument) {
	}

	@Override
	public String[] allInstruments() {
		return new String[] {};
	}

	@Override
	public void start(int overallDuration) {
		this.audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
				(int) SAMPLE_RATE, AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, numOfSamples(overallDuration),
				AudioTrack.MODE_STATIC);
		Log.i("ICM", "started player");
	}
}
