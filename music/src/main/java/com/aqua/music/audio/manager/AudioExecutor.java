package com.aqua.music.audio.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public interface AudioExecutor {
	public final ExecutorService executor = Executors.newCachedThreadPool(new AudioThreadFactory());
	class AudioThreadFactory implements ThreadFactory {
		private final AtomicInteger counter = new AtomicInteger();
		private final String factoryName;

		public AudioThreadFactory() {
			this.factoryName = "AudioFactory";
		}

		@Override
		public Thread newThread(Runnable arg0) {
			Thread thread = new Thread(arg0, factoryName + "_" + counter.getAndIncrement());
			thread.setDaemon(true);
			return thread;
		}
	}
}
