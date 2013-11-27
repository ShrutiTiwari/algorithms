package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.aqua.music.api.AudioPlayerSettings;

interface UiButtons {
	int BUTTON_HEIGHT = 30;
	int DEFAULT_BUTTON_WIDTH = 120;
	int MINI_BUTTON_WIDTH = 150;
	int PLAY_BUTTON_WIDTH = 200;
	int X_COORIDNATE = 30;

	String text();

	String tooltip();

	enum MusicButtons implements UiButtons {
		CHOICE_OPTIONS("$$", "This thaat is $$", MINI_BUTTON_WIDTH) {
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
		SINGLE_ITEM_PLAYER("Play $$", "Click this to play $$", PLAY_BUTTON_WIDTH) {
			@Override
			JButton createInstanceWith(String buttonName) {
				JButton button = configurableNamedButton(this, buttonName);
				return button;
			}
		},
		INCREASE_TEMPO("IncreaseTempo", "Click this to stop!", MINI_BUTTON_WIDTH) {
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
		DECREASE_TEMPO("DecreaseTempo", "Click this to stop!", MINI_BUTTON_WIDTH) {
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

		private final int buttonWidth;
		private final String text;
		private final String tooltip;

		private MusicButtons(String text, String tooltip) {
			this(text, tooltip, DEFAULT_BUTTON_WIDTH);
		}

		private MusicButtons(String text, String tooltip, int buttonWidth) {
			this.text = text;
			this.tooltip = tooltip;
			this.buttonWidth = buttonWidth;
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

		public JButton createDynamicNamedButton(String buttonName, int yCoordinate) {
			return createDynamicNamedButton(buttonName, X_COORIDNATE, yCoordinate);
		}

		public JButton createDynamicNamedButton(String buttonName, int xCoordinate, int yCoordinate) {
			JButton buttonItem = createInstanceWith(buttonName);
			buttonItem.setOpaque(true);
			buttonItem.setBounds(xCoordinate, yCoordinate, buttonWidth, BUTTON_HEIGHT);
			return buttonItem;
		}

		public JButton createStaticNamedButton(int yCoordinate) {
			return createDynamicNamedButton("", X_COORIDNATE, yCoordinate);
		}

		public JButton createStaticNamedButton(int xCoordinate, int yCoordinate) {
			return createDynamicNamedButton("", xCoordinate, yCoordinate);
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
