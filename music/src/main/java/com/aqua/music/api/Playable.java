package com.aqua.music.api;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;

public interface Playable {
	public String play(AudioPlayConfig audioPlayConfig);
	public String name();
	public String asText();
}