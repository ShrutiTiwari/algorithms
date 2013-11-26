package com.aqua.music.view.action.listeners;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.api.Playable;
import com.aqua.music.bo.audio.manager.AudioTask;

/**
 * @author "Shruti Tiwari"
 *
 */
public class PlayAllItemsActionListener implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(PlayAllItemsActionListener.class);
	private final TextArea textArea;
	private final Playable[] playableItems;

	public PlayAllItemsActionListener(final TextArea textArea, final Playable[] playableItems) {
		this.textArea = textArea;
		this.playableItems = playableItems;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		AudioTask<Playable> audioTask = new AudioTask<Playable>() {
			@Override
			public Playable[] forLoopParameter() {
				return playableItems;
			}

			@Override
			public void forLoopBody(final Playable playableItem) {
				String text = playableItem.name() + "===>\n" + playableItem.asText();
				String displayText = "Playing::" + text;
				logger.info(displayText);
				textArea.append(displayText);
				AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(playableItem.frequencies());
			}

			@Override
			public void beforeForLoop() {
				textArea.setText("Playing all items:\n");
				logger.info(""+playableItems.length);
			}
		};
		AudioPlayerSettings.playAsynchronously(audioTask);
		
	}
}
