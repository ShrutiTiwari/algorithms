package com.aqua.music.audio.player;


public enum StandardAudioLifeCycleManagers {
	FREQUENCY_BASED(AudioGeneratorBasedOnMathSinAngle.class),
	VLC_BASED(AudioPlayerBasedOnVLC.class);

	private final Class<? extends AudioPlayer> audioPlayerClass;

	private AudioLifeCycleManager blockingPlayer;

	private boolean initialized=false;

	private AudioLifeCycleManager nonBlockingPlayer;

	private StandardAudioLifeCycleManagers(Class<? extends AudioPlayer> audioPlayerClass) {
		this.audioPlayerClass = audioPlayerClass;
	}

	public final AudioLifeCycleManager player(boolean blocking) {
		try {
			if (!initialized) {
				lazyInitialize();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blocking ? blockingPlayer : nonBlockingPlayer;
	}

	private synchronized void lazyInitialize() throws InstantiationException, IllegalAccessException {
		if (!initialized) {
			blockingPlayer = new AudioLifeCycleManager(true, audioPlayerClass.newInstance());
			nonBlockingPlayer = new AudioLifeCycleManager(false, audioPlayerClass.newInstance());
			initialized=true;
		}
	}

}
