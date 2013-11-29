package com.aqua.music.api;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sound.midi.Instrument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioTask;
import com.aqua.music.bo.audio.player.BasicNotePlayer;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class PlayApi {
	private static final Logger logger = LoggerFactory.getLogger(PlayApi.class);
	private static final Collection<Playable> playableSongs = PlaybleType.SONG.playables();
	private static final Instrument[] instruments = BasicNotePlayer.MIDI_BASED_PLAYER.allInstruments();
	private static final Collection<Playable> playablePlainThaats = PlaybleType.PLAIN_THAAT.playables();

	public static Collection<Playable> getAllSongs() {
		return playableSongs;
	}

	public static Collection<Playable> getAllPlainThaat() {
		return playablePlainThaats;
	}

	public static Collection<Playable> getAllPatternedThaat(FrequencySet frequencySet, PermuatationsGenerator permuatationsGenerator) {
		List<int[]> allPermutations = permuatationsGenerator.generatePermutations(frequencySet.ascendNotes());

		Collection<Playable> result = new ArrayList<Playable>();
		for (int[] eachPermutation : allPermutations) {
			CyclicFrequencySet playbleItem = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(frequencySet,
					eachPermutation);
			result.add(playbleItem);
		}
		return result;
	}

	public static Instrument[] getAllInstruments() {
		return instruments;
	}

	/**
	 * NOTE: Its a non blocking call
	 * @param frequencyList
	 */
	public static void playInLoop(Playable playableitem) {
		AudioPlayerSettings.ASYNCHRONOUS_DYNAMIC_PLAYER.playInLoop(playableitem.frequencies());
	}

	/**
	 * NOTE: Its a non blocking call
	 * @param frequencyList
	 */
	public static void play(Playable playableitem) {
		AudioPlayerSettings.ASYNCHRONOUS_DYNAMIC_PLAYER.play(playableitem.frequencies());
	}
	
	public static void playAllItemsWithInteractiveDisplayInTextArea(final Playable[] playableItems, final TextArea textArea) {
		AudioPlayerSettings.playAsynchronously(audioTaskWith(playableItems, textArea));
	}

	private static AudioTask<Playable> audioTaskWith(final Playable[] playableItems, final TextArea textArea) {
		AudioTask<Playable> audioTask = new AudioTask<Playable>() {
			@Override
			public Playable[] forLoopParameter() {
				return playableItems;
			}

			@Override
			public void forLoopBody(final Playable playableItem) {
				String text = playableItem.name() + "===>\n" + playableItem.asText();
				String displayText = "Playing::" + text;
				logger.info(displayText);
				textArea.append(displayText);
				AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(playableItem.frequencies());
			}

			@Override
			public void beforeForLoop() {
				textArea.setText("Playing all items:\n");
				logger.info("" + playableItems.length);
			}
		};
		return audioTask;
	}
}
