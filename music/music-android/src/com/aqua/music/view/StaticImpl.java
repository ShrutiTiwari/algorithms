package com.aqua.music.view;

import java.util.concurrent.atomic.AtomicBoolean;

import open.music.api.SingletonFactory;
import open.music.api.StateDependentUi;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.player.AndroidConfigBuilder;
import com.aqua.music.model.core.Frequency;

public class StaticImpl {
	private static BaseSoundPlayActivity activity = null;
	private final static AtomicBoolean initialized = new AtomicBoolean(false);
	private static int staticTempoMultipler=0;
	StaticImpl() {
		synchronized (initialized) {
			if (initialized.get()) {
				return;
			}
			SingletonFactory.PLAY_API.initialize(stateUi(),
					AndroidConfigBuilder.AndroidConfig.DYNAMIC);
			AudioLifeCycleManager.instance.increaseTempo();
			AudioLifeCycleManager.instance.increaseTempo();
			initialized.set(true);
		}
	}

	public void register(BaseSoundPlayActivity a) {
		activity = a;
		activity.updateTempo(staticTempoMultipler);
	}

	StateDependentUi stateUi() {
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
			public void updateTempo(int tempoMultipler) {
				staticTempoMultipler=tempoMultipler;
				if (activity != null) {
					activity.updateTempo(tempoMultipler);
				}
			}
		};
		return stateDependentUi;
	}

}
