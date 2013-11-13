package com.aqua.music.bo.audio.manager;

public interface AudioPlayRightsManager {
	void acquireRightToPlay() throws InterruptedException;

	boolean stopPlaying();

	void releaseRightToPlay();
}
