package open.music.api;

import java.util.Collection;

import open.music.api.PlayApi.AudioPlayerNextStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.PlayMode;
import com.aqua.music.bo.audio.player.AudioPlayer;
import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
public enum AudioPlayerSettings {
	ASYNCHRONOUS_DYNAMIC_PLAYER(AudioPlayer.Factory.DYNAMIC_AUDIO, PlayMode.Asynchronous),
	ASYNCHRONOUS_STATIC_PLAYER(AudioPlayer.Factory.STATIC_AUDIO, PlayMode.Asynchronous),
	SYNCHRONOUS_DYNAMIC_PLAYER(AudioPlayer.Factory.DYNAMIC_AUDIO, PlayMode.Synchronous),
	SYNCHRONOUS_STATIC_PLAYER(AudioPlayer.Factory.STATIC_AUDIO, PlayMode.Synchronous);

	private final AudioPlayer.Factory audioPlayerFactory;
	private static final Logger logger = LoggerFactory.getLogger(AudioPlayerSettings.class);
	private final PlayMode playMode;

	private AudioPlayerSettings(AudioPlayer.Factory audioPlayer, PlayMode playMode) {
		this.audioPlayerFactory = audioPlayer;
		this.playMode = playMode;
	}

	public static void decreaseTempo() {
		try {
			AudioLifeCycleManager.instance.decreaseTempo();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void increaseTempo() {
		try {
			AudioLifeCycleManager.instance.increaseTempo();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static AudioPlayerNextStatus togglePauseAndResume() {
		try {
			return AudioLifeCycleManager.instance.togglePauseAndResume();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static void stop() {
		try {
			AudioLifeCycleManager.instance.stop();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void play(Collection<? extends DynamicFrequency> frequencyList) {
		playMode.play(audioPlayerFactory.fetchInstance(), frequencyList, 1);
	}

	public void play(Collection<? extends DynamicFrequency> frequencyList, int repeatCount) {
		playMode.play(audioPlayerFactory.fetchInstance(), frequencyList, repeatCount);
	}

	public void playInLoop(Collection<? extends DynamicFrequency> frequencyList) {
		playMode.playInLoop(audioPlayerFactory.fetchInstance(), frequencyList);
	}
}
