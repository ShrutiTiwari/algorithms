package com.aqua.music.bo.audio.manager;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.aqua.music.bo.audio.player.AudioPlayer;
import com.aqua.music.model.core.DynamicFrequency;

class AudioLifeCycleManagerImpl implements AudioLifeCycleManager, AudioPlayRightsManager {
	private AudioPlayer currentAudioPlayer;

	private final Lock permitToPlay;
	private final AtomicBoolean stopCurrentPlay;

	AudioLifeCycleManagerImpl() {
		this.permitToPlay = new ReentrantLock();
		this.stopCurrentPlay = new AtomicBoolean(false);
	}

	AudioLifeCycleManagerImpl(int durationInMsec, double vol) {
		this();
	}

	@Override
	public synchronized void acquireRightToPlay() throws InterruptedException {
		boolean acquired = permitToPlay.tryLock();
		if (!acquired) {
			logger.info("Play is ongoing!! Issuing stop");
			stopCurrentPlay.set(true);
			currentAudioPlayer.stop();
			permitToPlay.lock();
			stopCurrentPlay.set(false);
		}
	}

	@Override
	public <T> void execute(final AudioTask<T> audioTask) {
		PlayMode.Asynchronous.playTask(audioTask);
	}

	@Override
	public void setCurrentPlayer(AudioPlayer audioPlayer) {
		this.currentAudioPlayer = audioPlayer;
	}

	@Override
	public void releaseRightToPlay() {
		permitToPlay.unlock();
	}

	@Override
	public boolean stopPlaying() {
		return stopCurrentPlay.get();
	}

	AudioLifeCycleManagerImpl setDurationAndVolume(int durationInMsec, double vol) {
		return new AudioLifeCycleManagerImpl(durationInMsec, vol);
	}
}