package open.music.api;

import java.util.Collection;

import open.music.api.PlayApi.AudioPlayerNextStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.PlayMode;
import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
public enum AudioPlayerFacade {
	ASYNCHRONOUS_PLAYER(PlayMode.Asynchronous),
	SYNCHRONOUS_PLAYER(PlayMode.Synchronous);

	private static final Logger logger = LoggerFactory.getLogger(AudioPlayerFacade.class);
	private final PlayMode playMode;

	private AudioPlayerFacade(PlayMode playMode) {
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
		playMode.play(frequencyList, 1);
	}

	public void play(Collection<? extends DynamicFrequency> frequencyList, int repeatCount) {
		playMode.play(frequencyList, repeatCount);
	}

	public void playInLoop(Collection<? extends DynamicFrequency> frequencyList) {
		playMode.playInLoop(frequencyList);
	}
}
