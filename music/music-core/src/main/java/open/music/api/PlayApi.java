package open.music.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayRightsManager;
import com.aqua.music.bo.audio.manager.AudioTask;
import com.aqua.music.bo.audio.manager.PlayMode;
import com.aqua.music.bo.audio.player.AudioPlayer;
import com.aqua.music.bo.audio.player.BasicNotePlayerWithMathSin;
import com.aqua.music.bo.audio.player.BasicNotePlayerWithMidiChannel;
import com.aqua.music.bo.audio.player.AudioPlayer.Factory;
import com.aqua.music.bo.audio.player.BasicNotePlayer;
import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.SymmetricalSet;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class PlayApi {
	private String defaultInstrument;
	private final Logger logger = LoggerFactory.getLogger(PlayApi.class);
	private final Collection<Playable> playablePlainThaats;

	private final Collection<Playable> playableSongs;
	private StateDependentUi stateDependentUi;

	PlayApi() {
		this.playableSongs = PlaybleType.SONG.playables();
		this.playablePlainThaats = PlaybleType.PLAIN_THAAT.playables();
		this.defaultInstrument = "Flute";
	}

	public String defaultInstrument() {
		return defaultInstrument;
	}

	public String[] getAllInstruments() {
		return AudioPlayer.Factory.DYNAMIC_AUDIO.fetchInstance().allInstruments();
	}

	public Collection<Playable> getAllPatternedThaat(FrequencySet frequencySet, PermuatationsGenerator permuatationsGenerator) {
		List<int[]> allPermutations = permuatationsGenerator.generatePermutations(frequencySet.ascendNotes());

		Collection<Playable> result = new ArrayList<Playable>();
		for (int[] eachPermutation : allPermutations) {
			CyclicFrequencySet playbleItem = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(frequencySet,
					eachPermutation);
			result.add(playbleItem);
		}
		return result;
	}
	
	public Collection<Playable> getCustomizedThaat(PracticeCustomization customization) {
		Collection<Playable> result = new ArrayList<Playable>();
		ClassicalNote[] startEndPoints= customization.getStartEndPoint();
		logger.info("generating pattern for startClassicalNote[" + startEndPoints[0] + "],  endClassicalNote[" + startEndPoints[1] + "]");
		for(SymmetricalSet each:SymmetricalSet.values()){
			CyclicFrequencySet playbleItem = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(each, startEndPoints);
			result.add(playbleItem);
		}
		return result;
	}

	public Collection<Playable> getAllPlainThaat() {
		return playablePlainThaats;
	}

	public Collection<Playable> getAllSongs() {
		return playableSongs;
	}

	public void initialize(StateDependentUi stateDependentUi, InitializationConfigProvider initializationConfigProvider) {
		this.stateDependentUi = stateDependentUi;
		InstrumentRole.MAIN.setTo(defaultInstrument);
		stateDependentUi.updateInstrument(defaultInstrument);
		AudioLifeCycleManager.instance.addStateObserver(stateDependentUi);
		new Initializer(initializationConfigProvider.getConfig()).initialize();
	}

	/**
	 * NOTE: Its a non blocking call
	 * 
	 * @param frequencyList
	 */
	public void play(Playable playableitem) {
		stateDependentUi.setPauseToDisplay();
		AudioPlayerFacade.ASYNCHRONOUS_PLAYER.play(playableitem.frequencies(), 1);
	}
	
	public void play(Playable playableitem,PracticeCustomization practiceCustomization) {
		stateDependentUi.setPauseToDisplay();
		AudioPlayerFacade.ASYNCHRONOUS_PLAYER.play(playableitem.frequencies(), 1);
	}
	

	public void playAllItemsWithInteractiveDisplayInTextArea(final Playable[] playableItems, int repeatCount) {
		AudioTask<Playable> audioTask = audioTaskWith(playableItems, repeatCount);
		stateDependentUi.setPauseToDisplay();
		PlayMode.Asynchronous.playTask(audioTask);
	}

	/**
	 * NOTE: Its a non blocking call
	 * 
	 * @param frequencyList
	 */
	public void playInLoop(Playable playableitem) {
		String playableName = playableitem.name();
		stateDependentUi.updatePlayable(playableName);
		String displayText = "\n\n Playing::" + playableName + "===>" + "\n" + playableitem.asText();
		logger.info(displayText);
		stateDependentUi.updateConsole(displayText);

		stateDependentUi.setPauseToDisplay();
		AudioPlayerFacade.ASYNCHRONOUS_PLAYER.playInLoop(playableitem.frequencies());
	}

	private AudioTask<Playable> audioTaskWith(final Playable[] playableItems, final int repeatCount) {
		AudioTask<Playable> audioTask = new AudioTask<Playable>() {
			@Override
			public void beforeForLoop() {
				stateDependentUi.updateConsole("Playing all items, each [" + repeatCount + "] times :\n");
				logger.info("" + playableItems.length);
			}

			@Override
			public void forLoopBody(final Playable playableItem) {
				String playableName = playableItem.name();
				stateDependentUi.updatePlayable(playableName);
				String text = playableName + "===>\n" + playableItem.asText();
				String displayText = "Playing::" + text;
				logger.info(displayText);
				stateDependentUi.appendToConsole(displayText);
				AudioPlayerFacade.SYNCHRONOUS_PLAYER.play(playableItem.frequencies(), repeatCount);
			}

			@Override
			public Playable[] forLoopParameter() {
				return playableItems;
			}
		};
		return audioTask;
	}

	public enum AudioPlayerNextStatus {
		PAUSE,
		RESUME;
	}
	
	public interface BasicNotePlayerBuidler{
		BasicNotePlayer build();
		BasicNotePlayerBuidler DESKTOP_MATH_SIN= new  BasicNotePlayerBuidler() {
			@Override
			public BasicNotePlayer build() {
				return new BasicNotePlayerWithMathSin();
			}
		};
		
		BasicNotePlayerBuidler DESKTOP_MIDI= new  BasicNotePlayerBuidler() {
			@Override
			public BasicNotePlayer build() {
				return new BasicNotePlayerWithMidiChannel();
			}
		};

	}
	
	public interface InitializationConfig{
		AudioPlayer audioPlayer();
		BasicNotePlayer basicNotePlayer();
	}
	
	public static class InitializationConfigImpl implements InitializationConfig{
		private final Factory audioFactory;
		private final BasicNotePlayerBuidler basicNotePlayerBuilder;
		public InitializationConfigImpl (BasicNotePlayerBuidler basicNotePlayerBuilder, Factory audioFactory) {
			this.basicNotePlayerBuilder = basicNotePlayerBuilder;
			this.audioFactory = audioFactory;
		}	
		
		public AudioPlayer audioPlayer(){
			return audioFactory.fetchInstance();
		}
		public BasicNotePlayer basicNotePlayer(){
			return basicNotePlayerBuilder.build();
		}
	}
	
	public interface InitializationConfigProvider{
		InitializationConfig getConfig();
	}
	private class Initializer{
		private InitializationConfig initializationConfig;
		
		private Initializer(InitializationConfig initializationConfig){
			this.initializationConfig=initializationConfig;
		}
		
		private void initialize() {
			AudioPlayer audioPlayer = initializationConfig.audioPlayer();
			try {
				audioPlayer.setBasicNotePalyer(initializationConfig.basicNotePlayer());
			} catch (Exception e) {
				e.printStackTrace();
			}
			setup(audioPlayer);
		}

		private void setup(AudioPlayer currentAudioPlayer) {
			final AudioPlayRightsManager audioPlayRightsManager = (AudioPlayRightsManager) AudioLifeCycleManager.instance;
			audioPlayRightsManager.setCurrentPlayer(currentAudioPlayer);
			currentAudioPlayer.setAudioPlayRigthsManager(audioPlayRightsManager);
		}		
	}
}
