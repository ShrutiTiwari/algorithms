package com.aqua.music.view;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.bo.audio.manager.AudioTask;
import com.aqua.music.controller.CyclicFrequencySet;

enum UiButtonsForFrequencySet {
	FREQUENCY_SET_PATTERNED_PLAYER("Play $$", "Click this to play $$", 300) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final CyclicFrequencySet[] frequencySequences) {
			final CyclicFrequencySet frequencySequence = frequencySequences[0];
			final String buttonTitle = frequencySequence.freqSetNamePermuationAsText();
			JButton button = configurableNamedButton(this, buttonTitle);

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String text = frequencySequence.play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
					setText(textArea, buttonTitle + "===>" + "\n" + text);
				}
			};

			button.addActionListener(actionListener);
			return button;
		}
	},
	FREQUENCY_SET_PLAYER("Play $$", "Click this to play $$", 200) {
		@Override
		JButton createInstanceWith(final TextArea textArea, CyclicFrequencySet[] frequencySequences) {
			final CyclicFrequencySet frequencySequence = frequencySequences[0];
			final String buttonTitle = frequencySequence.freqSetNamePermuationAsText();
			JButton button = configurableNamedButton(this, buttonTitle);

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String text = frequencySequence.play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
					setText(textArea, buttonTitle + "===>" + "\n" + text);
				}
			};

			button.addActionListener(actionListener);

			return button;
		}
	},
	PLAY_ALL("PLAY_ALL", "Click this to play all!", 400) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final CyclicFrequencySet[] frequencySequences) {
			JButton button = fixedNameButton(this);

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					AudioTask<CyclicFrequencySet> audioTask = new AudioTask<CyclicFrequencySet>() {
						@Override
						public CyclicFrequencySet[] forLoopParameter() {
							return frequencySequences;
						}

						@Override
						public void forLoopBody(final CyclicFrequencySet frequencySequence) {
							String text = frequencySequence.freqSetNamePermuationAsText() + "===>\n" + frequencySequence.cycleFrequenciesAsText();
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
			};

			button.addActionListener(actionListener);
			return button;
		}
	},
	QUIT("Quit", "Click this to quit!", 400) {
		@Override
		JButton createInstanceWith(TextArea Object, CyclicFrequencySet[] newParam) {
			JButton button = fixedNameButton(this);

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			};

			button.addActionListener(actionListener);
			return button;
		}
	};
	private static final Logger logger = LoggerFactory.getLogger(UiButtonsForFrequencySet.class);
	static final int X_COORIDNATE = 30;
	private static final int BUTTON_HEIGHT = 30;
	private final int displayWidth;
	private final String text;
	private final String tooltip;

	private UiButtonsForFrequencySet(String text, String tooltip, int buttonWidth) {
		this.text = text;
		this.tooltip = tooltip;
		this.displayWidth = buttonWidth;
	}

	static final int HEIGHT() {
		return BUTTON_HEIGHT;
	}

	private static JButton configurableNamedButton(UiButtonsForFrequencySet itemType, String replaceName) {
		JButton resultButton = new JButton(itemType.text.replace("$$", replaceName));
		resultButton.setToolTipText(itemType.tooltip.replace("$$", replaceName));
		return resultButton;
	}

	private static JButton fixedNameButton(UiButtonsForFrequencySet itemType) {
		JButton resultButton = new JButton(itemType.text);
		resultButton.setToolTipText(itemType.tooltip);
		return resultButton;
	}

	private static void setText(final TextArea textArea, final String name) {
		String displayText = "\n\n Playing::" + name;
		logger.info(displayText);
		textArea.setText(displayText);
	}

	public JButton createButton(TextArea textArea, int yCoordinate, CyclicFrequencySet[] frequencySequences) {
		JButton buttonItem = createInstanceWith(textArea, frequencySequences);
		buttonItem.setOpaque(true);
		buttonItem.setBounds(X_COORIDNATE, yCoordinate, displayWidth, BUTTON_HEIGHT);
		return buttonItem;
	}

	abstract JButton createInstanceWith(TextArea textArea, CyclicFrequencySet[] frequencySequences);
}