/**
 * 
 */
package com.aqua.music.bo.audio.manager;

import java.util.ArrayList;
import java.util.List;

import open.music.api.DesktopConfig;
import open.music.api.SingletonFactory;
import open.music.api.StateDependentUi;

import com.aqua.music.model.core.Frequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class CommonCode {
	private static final StateDependentUi stateDependentUi = new MyStateDependentUi();

	public static void initialize() {
		SingletonFactory.PLAY_API.initialize(stateDependentUi, DesktopConfig.DYNAMIC);
	}

	public static void staticInitialize() {
		SingletonFactory.PLAY_API.initialize(stateDependentUi, DesktopConfig.STATIC);
	}

	static class MyStateDependentUi implements StateDependentUi {
		private final List<StartEndPointChangeListener> listeners = new ArrayList<StartEndPointChangeListener>();

		@Override
		public void appendToConsole(String displayText) {
		}

		public void setStartEndPoints(Frequency[] startEndPoints) {
			for (StartEndPointChangeListener each : listeners) {
				each.changedEndPoints(startEndPoints);
			}
		}

		@Override
		public void registerStartEndPointChangeListener(StartEndPointChangeListener listener) {
			this.listeners.add(listener);
		}

		@Override
		public void setPauseToDisplay() {
		}

		@Override
		public void updateConsole(String displayText) {
		}

		@Override
		public void updateInstrument(String instrument) {
		}

		@Override
		public void updatePlayable(String playableName) {
		}

		@Override
		public void updateTempo(int multipler) {
		}
	}
}
