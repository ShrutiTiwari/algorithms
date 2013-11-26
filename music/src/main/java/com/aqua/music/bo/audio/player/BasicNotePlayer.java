package com.aqua.music.bo.audio.player;

import com.aqua.music.model.core.DynamicFrequency;

interface BasicNotePlayer {
	void finish();
	void play(DynamicFrequency each, int duration);
	void start();
	void stop();
	void tidyup();
}