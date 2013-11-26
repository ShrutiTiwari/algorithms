package com.aqua.music.bo.audio.manager;

import com.aqua.music.bo.audio.player.AudioPlayer;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface AudioPlayRightsManager {
	void acquireRightToPlay() throws InterruptedException;

	boolean stopPlaying();

	void releaseRightToPlay();

	void setCurrentPlayer(AudioPlayer currentAudioPlayer);

	boolean pauseCurrentPlay();
	
	int tempoMultilier(int duration);
}
