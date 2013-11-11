package com.aqua.music.items;

import com.aqua.music.audio.player.AudioLifeCycleManager;
import com.aqua.music.audio.player.StandardAudioLifeCycleManagers;

/**
 * 
 * Separated the filesystem based audio player as it doesn't work in the applet
 * and causes other workflows to also not work in its presence.
 * 
 * @author "Shruti Tiwari"
 * 
 */
public interface FilesystemPlayableItem extends PlayableItem {
	AudioLifeCycleManager blocking = StandardAudioLifeCycleManagers.VLC_BASED.player(true);
	AudioLifeCycleManager nonBlockingVlcPlayer = StandardAudioLifeCycleManagers.VLC_BASED.player(false);
}
