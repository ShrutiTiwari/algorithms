package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import open.music.api.AudioPlayerSettings;
import open.music.api.PlayApi.AudioPlayerNextStatus;

interface UiButtons {
	String text();

	String tooltip();

	String IMAGE_INCREASE_TEMPO = "arrow_up.png";
	String IMAGE_DECREASE_TEMPO = "arrow_down.png";
	String IMAGE_PAUSE = "button_pause.png";
	String IMAGE_PLAY = "button_play.png";
	ImageResourceCache imageResourceCache = new ImageResourceCache();

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
				final JButton button = createImageButton(IMAGE_PLAY, fixedNameButton(this));
				final Icon playIcon = imageResourceCache.imageIcon(IMAGE_PLAY);
				final Icon pauseIcon = imageResourceCache.imageIcon(IMAGE_PAUSE);
				ActionListener actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Icon newIcon = (AudioPlayerSettings.togglePauseAndResume() == AudioPlayerNextStatus.PAUSE) ? pauseIcon : playIcon;
						button.setIcon(newIcon);
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
		INCREASE_TEMPO("IncreaseTempo", "Click this to stop!") {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = createImageButton(IMAGE_INCREASE_TEMPO, fixedNameButton(this));
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
				JButton button = createImageButton(IMAGE_DECREASE_TEMPO, fixedNameButton(this));
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

		private static JButton createImageButton(String imageName, JButton namedButton) {
			try {
				JButton imageButton = new JButton(imageResourceCache.imageIcon(imageName));
				imageButton.setBorder(BorderFactory.createEmptyBorder());
				imageButton.setPreferredSize(new Dimension(30, 30));
				return imageButton;
			} catch (Exception e) {
				return namedButton;
			}
		}
	}

	public class ImageResourceCache {
		Map<String, ImageIcon> cachedResoureces = new ConcurrentHashMap<String, ImageIcon>();

		public synchronized ImageIcon imageIcon(String imageName) {
			ImageIcon existing = cachedResoureces.get(imageName);
			if (existing != null) {
				return existing;
			}
			ImageIcon imageIconLookup = imageIconLookup(imageName);
			cachedResoureces.put(imageName, imageIconLookup);
			return imageIconLookup;
		}

		private static ImageIcon imageIconLookup(String imageName) {
			String path = Thread.currentThread().getContextClassLoader().getResource(imageName).getPath();
			try {
				ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File(path)));
				return imageIcon;
			} catch (Exception e) {
				return null;
			}
		}

	}

}
