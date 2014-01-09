package com.aqua.music.view;

import open.music.api.StateDependentUi;

import com.aqua.music.model.core.Frequency;

public class StaticImpl {
	static StateDependentUi stateUi() {
		StateDependentUi stateDependentUi = new StateDependentUi() {
			@Override
			public void appendToConsole(String arg0) {
			}

			@Override
			public void registerStartEndPointChangeListener(
					StartEndPointChangeListener arg0) {
			}

			@Override
			public void setPauseToDisplay() {
			}

			@Override
			public void setStartEndPoints(Frequency[] arg0) {
			}

			@Override
			public void updateConsole(String arg0) {
			}

			@Override
			public void updateInstrument(String arg0) {
			}

			@Override
			public void updatePlayable(String arg0) {
			}

			@Override
			public void updateTempo(int arg0) {
			}
		};
		return stateDependentUi;
	}

}
