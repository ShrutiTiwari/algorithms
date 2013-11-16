package com.aqua.music.action.listeners;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.bo.audio.manager.AudioTask;
import com.aqua.music.model.cyclicset.Playable;

public class PlayAllItemsActionListener implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(PlayAllItemsActionListener.class);
	private final TextArea textArea;
	private final Playable[] playableItems;

	public PlayAllItemsActionListener(final TextArea textArea, final Playable[] frequencySequences) {
		this.textArea = textArea;
		this.playableItems = frequencySequences;
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
				String displayText = "\n\n Playing::" + text;
				logger.info(displayText);
				textArea.append(displayText);
				playableItem.play(AudioPlayConfig.SYNCHRONOUS_DYNAMIC_PLAYER);
			}

			@Override
			public void beforeForLoop() {
				textArea.setText("Playing all items:\n");
				logger.info(""+playableItems.length);
			}
		};
		AudioLifeCycleManager.instance.execute(audioTask);
	}
}
