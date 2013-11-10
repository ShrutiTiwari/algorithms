package com.aqua.music.ui.swing;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.aqua.music.audio.player.AudioPlayCoordinator;
import com.aqua.music.audio.player.DualModePlayer;
import com.aqua.music.items.PlayableItem;
import com.aqua.music.items.SymmetricalPatternApplicator;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

enum UiPanelButtons {
	FREQUENCY_SET_PATTERNED_PLAYER("Play $$", "Click this to play $$", 200) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final Object[] arg) {
			final FrequencySet freqSet = convertToFrequencySet(arg);
			final int[] pattern = (int[]) arg[1];

			JButton button = configurableNamedButton(this, freqSet.name() + "  ==>  " + displayText(pattern));

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SymmetricalPatternApplicator<Frequency> freqPattern = new SymmetricalPatternApplicator<Frequency>(pattern);
					PlayableItem pattern = PlayableItem.nonBlockingFrequencyPlayerConfig.forSet(freqSet).andPattern(freqPattern);
					String text = pattern.play();
					System.out.println(":::::::::::"+text);
					setText(textArea, freqSet.name() + "===>" +"\n"+ text);
				}
			};

			button.addActionListener(actionListener);
			return button;
		}
	},
	FREQUENCY_SET_PLAYER("Play $$", "Click this to play $$", 200) {
		@Override
		JButton createInstanceWith(final TextArea textArea, Object[] arg) {
			final FrequencySet freqSet = convertToFrequencySet(arg);

			JButton button = configurableNamedButton(this, freqSet.name());

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					PlayableItem pattern = PlayableItem.nonBlockingFrequencyPlayerConfig.forSet(freqSet);
					String text = pattern.play();
					setText(textArea, freqSet.name() + "===>" +"\n"+ text);
				}
			};

			button.addActionListener(actionListener);

			return button;
		}
	},
	PLAY_ALL("PLAY_ALL", "Click this to play all!", 400) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final Object[] arg) {
			JButton button = fixedNameButton(this);

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					DualModePlayer.executor.execute(new Runnable() {
						@Override
						public void run() {
							textArea.setText("Playing all items:\n");

							for (Object each : arg) {
								JButton eachButton = (JButton) each;
								eachButton.doClick();
								//textArea.repaint();
								AudioPlayCoordinator.awaitStop();
							}
						}
					});
				}
			};

			button.addActionListener(actionListener);
			return button;
		}
	},
	QUIT("Quit", "Click this to quit!", 400) {
		@Override
		JButton createInstanceWith(TextArea Object, Object[] newParam) {
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

	private static final int BUTTON_HEIGHT = 30;
	static final int X_COORIDNATE = 30;
	private final int displayWidth;
	private final String text;
	private final String tooltip;

	private UiPanelButtons(String text, String tooltip, int buttonWidth) {
		this.text = text;
		this.tooltip = tooltip;
		this.displayWidth = buttonWidth;
	}

	static final int HEIGHT() {
		return BUTTON_HEIGHT;
	}

	private static JButton configurableNamedButton(UiPanelButtons itemType, String replaceName) {
		JButton resultButton = new JButton(itemType.text.replace("$$", replaceName));
		resultButton.setToolTipText(itemType.tooltip.replace("$$", replaceName));
		return resultButton;
	}

	private static String displayText(int[] result) {
		String displayName = "" + result[0];
		int i = 0;
		for (int each : result) {
			if (i++ != 0) {
				displayName += ("-" + each);
			}
		}
		return displayName;
	}

	private static JButton fixedNameButton(UiPanelButtons itemType) {
		JButton resultButton = new JButton(itemType.text);
		resultButton.setToolTipText(itemType.tooltip);
		return resultButton;
	}

	public JButton createButton(TextArea textArea, int yCoordinate, Object[] arg) {
		JButton buttonItem = createInstanceWith(textArea, arg);
		buttonItem.setOpaque(true);
		buttonItem.setBounds(X_COORIDNATE, yCoordinate, displayWidth, BUTTON_HEIGHT);
		return buttonItem;
	}

	abstract JButton createInstanceWith(TextArea textArea, Object[] arg);

	static FrequencySet convertToFrequencySet(Object[] arg) {
		return convert(FrequencySet.class, arg[0]);
	}

	static <T> T convert(Class<T> t, Object arg) {
		return (T) arg;
	}
	private static void setText(final TextArea textArea, final String name) {
		String displayText = "\n\n Playing::" + name ;		
		System.out.println(displayText);
		if (AudioPlayCoordinator.playInProgress()) {
			textArea.append(displayText);
		} else {
			textArea.setText(displayText);
		}
	}
}