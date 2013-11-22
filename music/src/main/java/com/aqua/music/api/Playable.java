package com.aqua.music.api;

import java.util.Collection;

import com.aqua.music.model.core.DynamicFrequency;

public interface Playable {
	public String name();
	public String asText();
	public Collection<? extends DynamicFrequency> frequencies();
}
