package com.aqua.music.items;

import com.aqua.music.audio.manager.AudioLifeCycleManager;
import com.aqua.music.audio.manager.AudioLifeCycleManagers;
import com.aqua.music.audio.manager.DualModeManager.PlayMode;
/**
 * 
 * Separated the filesystem based audio player as it doesn't work in the applet
 * and causes other workflows to also not work in its presence.
 * 
 * @author "Shruti Tiwari"
 * 
 */
public interface FilesystemPlayableItem extends PlayableItem {
	AudioLifeCycleManager blocking = AudioLifeCycleManagers.VLC_BASED.player(PlayMode.Synchronous);
	AudioLifeCycleManager nonBlockingVlcPlayer = AudioLifeCycleManagers.VLC_BASED.player(PlayMode.Asynchornous);
}
