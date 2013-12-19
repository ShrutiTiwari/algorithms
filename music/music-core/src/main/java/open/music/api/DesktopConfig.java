/**
 * 
 */
package open.music.api;

import open.music.api.PlayApi.BasicNotePlayerBuidler;
import open.music.api.PlayApi.InitializationConfig;
import open.music.api.PlayApi.InitializationConfigImpl;
import open.music.api.PlayApi.InitializationConfigProvider;

import com.aqua.music.bo.audio.player.AudioPlayer;

public enum DesktopConfig implements InitializationConfigProvider{
	DYNAMIC(new InitializationConfigImpl(BasicNotePlayerBuidler.DESKTOP_MIDI, AudioPlayer.Factory.DYNAMIC_AUDIO)),
	STATIC(new InitializationConfigImpl(BasicNotePlayerBuidler.DESKTOP_MIDI, AudioPlayer.Factory.STATIC_AUDIO));

	private InitializationConfig initializationConfig;
	
	private DesktopConfig(InitializationConfig initializationConfig) {
		this.initializationConfig = initializationConfig;
	}

	public InitializationConfig getConfig() {
		return initializationConfig;
	}
}