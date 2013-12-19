/**
 * 
 */
package open.music.api;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayRightsManager;
import com.aqua.music.bo.audio.player.AudioPlayer;
import com.aqua.music.bo.audio.player.AudioPlayer.Factory;
import com.aqua.music.bo.audio.player.BasicNotePlayer;
import com.aqua.music.bo.audio.player.BasicNotePlayerWithMathSin;
import com.aqua.music.bo.audio.player.BasicNotePlayerWithMidiChannel;

public enum DeviceType {
	DESKTOP_DYNAMIC(BasicNotePlayerWithMidiChannel.class, AudioPlayer.Factory.DYNAMIC_AUDIO),
	DESKTOP_STATIC(BasicNotePlayerWithMidiChannel.class, AudioPlayer.Factory.STATIC_AUDIO),
	ANDROID(BasicNotePlayerWithMathSin.class, AudioPlayer.Factory.DYNAMIC_AUDIO);

	private final Class<? extends BasicNotePlayer> basicNotePlayerClass;
	private final Factory audioFactory;

	private DeviceType(Class<? extends BasicNotePlayer> basicNotePlayerClass, Factory audioFactory) {
		this.basicNotePlayerClass = basicNotePlayerClass;
		this.audioFactory = audioFactory;
	}

	public void initializeAudioFactory() {
		AudioPlayer audioPlayer = audioFactory.fetchInstance();
		try {
			audioPlayer.setBasicNotePalyer(basicNotePlayerClass.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setup(audioPlayer);
	}

	private static void setup(AudioPlayer currentAudioPlayer) {
		final AudioPlayRightsManager audioPlayRightsManager = (AudioPlayRightsManager) AudioLifeCycleManager.instance;
		audioPlayRightsManager.setCurrentPlayer(currentAudioPlayer);
		currentAudioPlayer.setAudioPlayRigthsManager(audioPlayRightsManager);
	}
}