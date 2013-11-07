package com.aqua.music.audio.player;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.aqua.music.model.Frequency;

interface DualModePlayer {
	public final ExecutorService executor = Executors.newSingleThreadExecutor(new AudioThreadFactory());
	
	public void playSynchronously(Collection<Frequency> obj);

	public void playAsynchronously(Collection<Frequency> obj);

	class AudioThreadFactory implements ThreadFactory{
		private final String factoryName;
		private final AtomicInteger counter= new AtomicInteger();

		public AudioThreadFactory() {
			this.factoryName= "AudioFactory";
		}

		@Override
		public Thread newThread(Runnable arg0) {
			Thread thread = new Thread(arg0,factoryName+"_"+counter.getAndIncrement());
			thread.setDaemon(true);
			return thread;
		}
	}
}