package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.aqua.music.api.AudioPlayerSettings;

interface UiButtons {
	String text();

	String tooltip();

	enum MusicButtons implements UiButtons {
		CHOICE_OPTIONS("$$", "This thaat is $$") {
			@Override
			JButton createInstanceWith(String buttonName) {
				JButton choiceButton = configurableNamedButton(this, buttonName);
				choiceButton.setBackground(Color.LIGHT_GRAY);
				choiceButton.setVisible(false);
				return choiceButton;
			}
		},
		PAUSE("Pause", "Click this to pause!") {
			@Override
			JButton createInstanceWith(String name) {
				final JButton button = fixedNameButton(this);
				ActionListener actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						button.setText(AudioPlayerSettings.togglePauseAndResume());
					}
				};

				button.addActionListener(actionListener);
				return button;
			}
		},
		PLAYER_FOR_ALL("PLAY_ALL", "Click this to play all!") {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = fixedNameButton(this);
				button.setForeground(Color.BLUE);
				return button;
			}
		},
		QUIT("Quit", "Click this to quit!") {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = fixedNameButton(this);
				ActionListener actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						AudioPlayerSettings.stop();
						System.exit(0);
					}
				};

				button.addActionListener(actionListener);
				button.setForeground(Color.BLUE);
				return button;
			}
		},
		QUIZ_PLAY("Play $$", "Click this to play $$") {
			@Override
			JButton createInstanceWith(String buttonName) {
				JButton button = configurableNamedButton(this, buttonName);
				return button;
			}
		},
		SINGLE_ITEM_PLAYER("Play $$", "Click this to play $$") {
			@Override
			JButton createInstanceWith(String buttonName) {
				JButton button = configurableNamedButton(this, buttonName);
				return button;
			}
		},
		INCREASE_TEMPO("IncreaseTempo", "Click this to stop!") {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = fixedNameButton(this);
				ActionListener actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						AudioPlayerSettings.increaseTempo();
					}
				};
				button.addActionListener(actionListener);
				return button;
			}
		},
		DECREASE_TEMPO("DecreaseTempo", "Click this to stop!") {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = fixedNameButton(this);
				ActionListener actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						AudioPlayerSettings.decreaseTempo();
					}
				};
				button.addActionListener(actionListener);
				return button;
			}
		};
		private final String text;
		private final String tooltip;

		private MusicButtons(String text, String tooltip) {
			this.text = text;
			this.tooltip = tooltip;
		}

		private static JButton configurableNamedButton(UiButtons itemType, String replaceName) {
			JButton resultButton = new JButton(itemType.text().replace("$$", replaceName));
			resultButton.setToolTipText(itemType.tooltip().replace("$$", replaceName));
			return resultButton;
		}

		private static JButton fixedNameButton(UiButtons itemType) {
			JButton resultButton = new JButton(itemType.text());
			resultButton.setToolTipText(itemType.tooltip());
			return resultButton;
		}

		public JButton createDynamicNamedButton(String buttonName) {
			JButton buttonItem = createInstanceWith(buttonName);
			buttonItem.setOpaque(true);
			return buttonItem;
		}

		public JButton createStaticNamedButton() {
			return createDynamicNamedButton("");
		}

		@Override
		public String text() {
			return text;
		}

		@Override
		public String tooltip() {
			return tooltip;
		}

		abstract JButton createInstanceWith(String name);
	}
}
