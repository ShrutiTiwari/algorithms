/**
 * 
 */
package com.aqua.music.view.components;

import javax.sound.midi.Instrument;

import com.aqua.music.example.easymidi.Playable;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class CurrentState {
	private final Playable currentPlayable;
	private final Instrument mainInstrument;
	private final int speed;

	public CurrentState(Playable currentPlayable, Instrument mainInstrument, int speed) {
		this.currentPlayable = currentPlayable;
		this.mainInstrument = mainInstrument;
		this.speed = speed;
	}
}
