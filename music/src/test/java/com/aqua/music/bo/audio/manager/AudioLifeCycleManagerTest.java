package com.aqua.music.bo.audio.manager;

import static com.aqua.music.model.Frequency.ClassicalNote.D;
import static com.aqua.music.model.Frequency.ClassicalNote.G;
import static com.aqua.music.model.Frequency.ClassicalNote.M;
import static com.aqua.music.model.Frequency.ClassicalNote.N;
import static com.aqua.music.model.Frequency.ClassicalNote.P;
import static com.aqua.music.model.Frequency.ClassicalNote.R;
import static com.aqua.music.model.Frequency.ClassicalNote.S;
import static com.aqua.music.model.Frequency.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import org.junit.Test;

import com.aqua.music.model.Frequency;

public class AudioLifeCycleManagerTest {

	@Test
	public void testFrequencyPlayer() throws LineUnavailableException {
		Frequency[] sample = new Frequency[] { S, R, G, M, P, D, N, S3 };
		// int durationInMilliSec = 2000;
		List<Frequency> asList = Arrays.asList(sample);
		AudioLifeCycleManager.instance.play(asList, AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
	}

	//@Test
	public void testVlcPlayer() {
		List<Frequency> frequencyList = new ArrayList<Frequency>();
		frequencyList.add(Frequency.ClassicalNote.D);
		AudioLifeCycleManager.instance.play(frequencyList, AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}

}