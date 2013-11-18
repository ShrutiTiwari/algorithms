package com.aqua.music.view.action.listeners;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.AudioPlayConfig;
import com.aqua.music.api.Playable;

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
		AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER.playInLoop(singlePlayableItem.frequencies());
		displayOnConsole(singlePlayableItem.name() + "===>" + "\n" + singlePlayableItem.asText());
	}

	private void displayOnConsole(final String name) {
		String displayText = "\n\n Playing::" + name;
		logger.info(displayText);
		consoleArea.setText(displayText);
	}
}
