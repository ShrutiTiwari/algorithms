package com.aqua.music.bo.audio.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface AudioLifeCycleManager {
	Logger logger = LoggerFactory.getLogger(AudioLifeCycleManager.class);
	final AudioLifeCycleManager instance = new AudioLifeCycleManagerImpl();

	<T> void execute(AudioTask<T> audioTask);
}
