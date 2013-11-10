package com.aqua.music.items;

import com.aqua.music.audio.player.AudioPlayerType;
import com.aqua.music.items.PlayableItem.AudioPlayerConfiguration;

/**
 * 
 * Seperated the filesystem based audio player as it doesnt work in the applet
 * and causes other workflows to also not work in its presence.
 * 
 * @author "Shruti Tiwari"
 * 
 */
public interface FilesystemPlayableItem extends PlayableItem {
	AudioPlayerConfiguration blocking = new AudioPlayerConfiguration();
	AudioPlayerConfiguration nonBlockingVlcPlayer = new AudioPlayerConfiguration(false, AudioPlayerType.VLC_BASED);
}
