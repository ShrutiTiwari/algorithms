package com.aqua.music.audio.player;
import static com.aqua.music.model.Frequency.ClassicalNote.DHA;
import static com.aqua.music.model.Frequency.ClassicalNote.GA;
import static com.aqua.music.model.Frequency.ClassicalNote.HIGH_SA;
import static com.aqua.music.model.Frequency.ClassicalNote.MA;
import static com.aqua.music.model.Frequency.ClassicalNote.NI;
import static com.aqua.music.model.Frequency.ClassicalNote.PA;
import static com.aqua.music.model.Frequency.ClassicalNote.RE;
import static com.aqua.music.model.Frequency.ClassicalNote.SA;

import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import com.aqua.music.audio.player.AudioPlayerBasedOnFrequencyList;
import com.aqua.music.model.Frequency;

public class FrequencyGeneratorTest {
	public static void main(String[] args) throws LineUnavailableException {
		Frequency[] sample = new Frequency[] { SA, RE, GA, MA, PA, DHA,
				NI, HIGH_SA };
		int durationInMilliSec = 2000;
		List<Frequency> asList = Arrays.asList(sample);
		new AudioPlayerBasedOnFrequencyList(new FrequencyListPlayerBasedOnMathSinAngle(durationInMilliSec, 0.8),false).play(asList);

	}
}