package com.aqua.music.bo.audio.manager;

import com.aqua.music.bo.audio.player.AudioPlayer;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface AudioPlayRightsManager {
	void acquireRightToPlay() throws InterruptedException;

	boolean isMarkedToStopPlaying();

	void releaseRightToPlay();

	void setCurrentPlayer(AudioPlayer currentAudioPlayer);

	boolean pauseCurrentPlay();
	
	int tempoMultiplier(int duration);
}
