package com.aqua.music.view.action.listeners;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.api.Playable;
import com.aqua.music.bo.audio.player.AudioPlayer;

/**
 * @author "Shruti Tiwari"
 *
 */
public class PlaySingleItemActionListener implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(PlaySingleItemActionListener.class);
	private final TextArea consoleArea;
	private final Playable singlePlayableItem;
	private final JButton pauseButton;

	public PlaySingleItemActionListener(final TextArea consoleArea, Playable singlePlayableItem, JButton pauseButton) {
		this.consoleArea = consoleArea;
		this.singlePlayableItem = singlePlayableItem;
		this.pauseButton=pauseButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.pauseButton.setText(AudioPlayer.NextStatus.PAUSE.toString());
		AudioPlayerSettings.ASYNCHRONOUS_DYNAMIC_PLAYER.playInLoop(singlePlayableItem.frequencies());
		displayOnConsole(singlePlayableItem.name() + "===>" + "\n" + singlePlayableItem.asText());
	}

	private void displayOnConsole(final String name) {
		String displayText = "\n\n Playing::" + name;
		logger.info(displayText);
		consoleArea.setText(displayText);
	}
}