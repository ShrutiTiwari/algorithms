package com.aqua.music.action.listeners;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.Playable;

public class PlaySingleItemActionListener implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(PlaySingleItemActionListener.class);
	private final TextArea consoleArea;
	private final Playable singlePlayableItem;

	public PlaySingleItemActionListener(final TextArea consoleArea, Playable singlePlayableItem) {
		this.consoleArea = consoleArea;
		this.singlePlayableItem = singlePlayableItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = singlePlayableItem.play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
		displayOnConsole(singlePlayableItem.name() + "===>" + "\n" + singlePlayableItem.asText());
	}

	private void displayOnConsole(final String name) {
		String displayText = "\n\n Playing::" + name;
		logger.info(displayText);
		consoleArea.setText(displayText);
	}
}
