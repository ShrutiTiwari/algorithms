package com.aqua.music.action.listeners;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.bo.audio.manager.AudioTask;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;

public class PlayAllFrequencySetsActionListener implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(PlayAllFrequencySetsActionListener.class);
	private final TextArea textArea;
	private final CyclicFrequencySet[] frequencySequences;

	public PlayAllFrequencySetsActionListener(final TextArea textArea, final CyclicFrequencySet[] frequencySequences) {
		this.textArea = textArea;
		this.frequencySequences = frequencySequences;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		AudioTask<CyclicFrequencySet> audioTask = new AudioTask<CyclicFrequencySet>() {
			@Override
			public CyclicFrequencySet[] forLoopParameter() {
				return frequencySequences;
			}

			@Override
			public void forLoopBody(final CyclicFrequencySet frequencySequence) {
				String text = frequencySequence.name() + "===>\n" + frequencySequence.asText();
				String displayText = "\n\n Playing::" + text;
				logger.info(displayText);
				textArea.append(displayText);
				frequencySequence.play(AudioPlayConfig.SYNCHRONOUS_DYNAMIC_PLAYER);
			}

			@Override
			public void beforeForLoop() {
				textArea.setText("Playing all items:\n");
				logger.info(""+frequencySequences.length);
			}
		};
		AudioLifeCycleManager.instance.execute(audioTask);
	}
}
