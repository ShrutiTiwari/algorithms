package com.aqua.music.view.action.listeners;

import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.api.Playable;
import com.aqua.music.bo.audio.player.AudioPlayer;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class PlaySingleItemActionListener implements ListSelectionListener {
	private static final Logger logger = LoggerFactory.getLogger(PlaySingleItemActionListener.class);
	private final TextArea consoleArea;
	private final Playable[] allPlayableItems;
	private final JButton pauseButton;
	private JList jlist;

	public PlaySingleItemActionListener(final JList jlist, final TextArea consoleArea, Playable[] singlePlayableItem,
			JButton pauseButton) {
		this.jlist = jlist;
		this.consoleArea = consoleArea;
		this.allPlayableItems = singlePlayableItem;
		this.pauseButton = pauseButton;
	}

	private void displayOnConsole(final String name) {
		String displayText = "\n\n Playing::" + name;
		logger.info(displayText);
		consoleArea.setText(displayText);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int selectedIndex = jlist.getSelectedIndex();
			if (selectedIndex != -1) {
				this.pauseButton.setText(AudioPlayer.NextStatus.PAUSE.toString());
				AudioPlayerSettings.ASYNCHRONOUS_DYNAMIC_PLAYER.playInLoop(allPlayableItems[selectedIndex].frequencies());
				displayOnConsole(allPlayableItems[selectedIndex].name() + "===>" + "\n" + allPlayableItems[selectedIndex].asText());
			}
		}
	}
}