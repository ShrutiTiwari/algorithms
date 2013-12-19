package com.aqua.music.bo.audio.manager;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import open.music.api.AudioPlayerFacade;
import open.music.api.DeviceType;

import org.junit.Test;

import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;

public class AudioLifeCycleManagerTest {
	//@Test
	public void testFrequencyPlayer() throws LineUnavailableException {
		DeviceType.DESKTOP_DYNAMIC.initializeAudioFactory();
		Frequency[] sample = new Frequency[] { S, R, G, M, P, D, N, S3 };
		// int durationInMilliSec = 2000;
		List<Frequency> asList = Arrays.asList(sample);
		AudioPlayerFacade.SYNCHRONOUS_PLAYER.play(asList, 1);
	}

	@Test
	public void testVlcPlayer() {
		List<Frequency> frequencyList = new ArrayList<Frequency>();
		frequencyList.add(ClassicalNote.D);

		DeviceType.DESKTOP_STATIC.initializeAudioFactory();
		AudioPlayerFacade.SYNCHRONOUS_PLAYER.play(frequencyList, 1);
	}
}