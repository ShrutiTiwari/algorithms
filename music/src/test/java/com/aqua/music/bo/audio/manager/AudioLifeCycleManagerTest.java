package com.aqua.music.bo.audio.manager;

import static com.aqua.music.model.Frequency.ClassicalNote.DHA;
import static com.aqua.music.model.Frequency.ClassicalNote.GA;
import static com.aqua.music.model.Frequency.ClassicalNote.MA;
import static com.aqua.music.model.Frequency.ClassicalNote.NI;
import static com.aqua.music.model.Frequency.ClassicalNote.PA;
import static com.aqua.music.model.Frequency.ClassicalNote.RE;
import static com.aqua.music.model.Frequency.ClassicalNote.SA;
import static com.aqua.music.model.Frequency.ClassicalNote.SA3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import org.junit.Test;

import com.aqua.music.model.Frequency;

public class AudioLifeCycleManagerTest {

	@Test
	public void testFrequencyPlayer() throws LineUnavailableException {
		Frequency[] sample = new Frequency[] { SA, RE, GA, MA, PA, DHA, NI, SA3 };
		// int durationInMilliSec = 2000;
		List<Frequency> asList = Arrays.asList(sample);
		AudioLifeCycleManager.instance.play(asList, AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
	}

	//@Test
	public void testVlcPlayer() {
		List<Frequency> frequencyList = new ArrayList<Frequency>();
		frequencyList.add(Frequency.ClassicalNote.DHA);
		AudioLifeCycleManager.instance.play(frequencyList, AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}

}