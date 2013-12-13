package open.music.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sound.midi.Instrument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioTask;
import com.aqua.music.bo.audio.manager.PlayMode;
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

	private static StateDependentUi stateDependentUi;
	private static Instrument defaultInstrument= BasicNotePlayer.MIDI_BASED_PLAYER.allInstruments()[73];
	
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
		String playableName = playableitem.name();
		stateDependentUi.updatePlayable(playableName);
		String displayText = "\n\n Playing::" + playableName + "===>" + "\n" + playableitem.asText();
		logger.info(displayText);
		stateDependentUi.updateConsole(displayText);
	
		stateDependentUi.setPauseToDisplay();
		AudioPlayerSettings.ASYNCHRONOUS_DYNAMIC_PLAYER.playInLoop(playableitem.frequencies());
	}

	/**
	 * NOTE: Its a non blocking call
	 * @param frequencyList
	 */
	public static void play(Playable playableitem) {
		stateDependentUi.setPauseToDisplay();
		AudioPlayerSettings.ASYNCHRONOUS_DYNAMIC_PLAYER.play(playableitem.frequencies(), 1);
	}
	
	public static void playAllItemsWithInteractiveDisplayInTextArea(final Playable[] playableItems, int repeatCount) {
		AudioTask<Playable> audioTask = audioTaskWith(playableItems, repeatCount);
		stateDependentUi.setPauseToDisplay();
		PlayMode.Asynchronous.playTask(audioTask);
	}

	private static AudioTask<Playable> audioTaskWith(final Playable[] playableItems, final int repeatCount) {
		AudioTask<Playable> audioTask = new AudioTask<Playable>() {
			@Override
			public Playable[] forLoopParameter() {
				return playableItems;
			}

			@Override
			public void forLoopBody(final Playable playableItem) {
				String playableName = playableItem.name();
				stateDependentUi.updatePlayable(playableName);
				String text = playableName + "===>\n" + playableItem.asText();
				String displayText = "Playing::" + text;
				logger.info(displayText);
				stateDependentUi.appendToConsole(displayText);
				AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(playableItem.frequencies(), repeatCount);
			}

			@Override
			public void beforeForLoop() {
				stateDependentUi.updateConsole("Playing all items:\n");
				logger.info("" + playableItems.length);
			}
		};
		return audioTask;
	}
	

	public enum AudioPlayerNextStatus{
		PAUSE, RESUME;
	}
	
	public static void initializeStateDepenendentUi(StateDependentUi stateDependentUi){
		PlayApi.stateDependentUi=stateDependentUi;
		InstrumentRole.MAIN.setTo(defaultInstrument);
		stateDependentUi.updateInstrument(defaultInstrument);
		AudioLifeCycleManager.instance.addStateObserver(stateDependentUi);
	}
	
	public static Instrument defaultInstrument(){
		return defaultInstrument;
	}
}
