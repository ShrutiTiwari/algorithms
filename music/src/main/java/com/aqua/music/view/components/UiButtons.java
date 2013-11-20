package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.aqua.music.bo.audio.manager.PlayMode;

interface UiButtons {
	int BUTTON_HEIGHT = 30;
	int LARGE_BUTTON_WIDTH = 500;
	int MINI_BUTTON_WIDTH = 130;
	int PLAY_BUTTON_WIDTH = 300;
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
		PAUSE("Pause", "Click this to pause!", MINI_BUTTON_WIDTH) {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = fixedNameButton(this);
				ActionListener actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						PlayMode.pause();
					}
				};

				button.addActionListener(actionListener);
				return button;
			}
		},
		PLAYER_FOR_ALL("PLAY_ALL", "Click this to play all!", (PLAY_BUTTON_WIDTH + 200)) {
			@Override
			JButton createInstanceWith(String name) {
				return fixedNameButton(this);
			}
		},
		QUIT("Quit", "Click this to quit!") {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = fixedNameButton(this);
				ActionListener actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						PlayMode.stop();
						System.exit(0);
					}
				};

				button.addActionListener(actionListener);
				return button;
			}
		},
		QUIZ_PLAY("Play $$", "Click this to play $$", MINI_BUTTON_WIDTH) {
			@Override
			JButton createInstanceWith(String buttonName) {
				return configurableNamedButton(this, buttonName);
			}
		},
		RESUME("Resume", "Click this to resume!", MINI_BUTTON_WIDTH) {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = fixedNameButton(this);
				ActionListener actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						PlayMode.resume();
					}
				};

				button.addActionListener(actionListener);
				return button;
			}
		},
		SINGLE_ITEM_PLAYER("Play $$", "Click this to play $$", PLAY_BUTTON_WIDTH) {
			@Override
			JButton createInstanceWith(String buttonName) {
				return configurableNamedButton(this, buttonName);
			}
		},
		STOP("Stop", "Click this to stop!", MINI_BUTTON_WIDTH) {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = fixedNameButton(this);
				ActionListener actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						PlayMode.stop();
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
			this(text, tooltip, LARGE_BUTTON_WIDTH);
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
