package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import open.music.api.AudioPlayerFacade;
import open.music.api.PlayApi.AudioPlayerNextStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface UiButtons {
	String IMAGE_DECREASE_TEMPO = "arrow_down.png";
	String IMAGE_INCREASE_TEMPO = "arrow_up.png";
	String IMAGE_PAUSE = "button_pause.png";
	String IMAGE_PLAY = "button_play.png";
	ImageResourceCache imageResourceCache = new ImageResourceCache();

	UiButtons PAUSE = new ButtonBuilder(IMAGE_PLAY, "Pause", "Click this to pause!") {
		@Override
		void actionListenerWork() {
			final Icon playIcon = imageResourceCache.imageIcon(IMAGE_PLAY);
			final Icon pauseIcon = imageResourceCache.imageIcon(IMAGE_PAUSE);
			Icon newIcon = (AudioPlayerFacade.togglePauseAndResume() == AudioPlayerNextStatus.PAUSE) ? pauseIcon : playIcon;
			PAUSE.getButton().setIcon(newIcon);
		}
	};
	UiButtons DECREASE_TEMPO = new ButtonBuilder(IMAGE_DECREASE_TEMPO, "DecreaseTempo", "Click this to decrease tempo.") {
		@Override
		void actionListenerWork() {
			AudioPlayerFacade.decreaseTempo();
		}
	};

	UiButtons INCREASE_TEMPO = new ButtonBuilder(IMAGE_INCREASE_TEMPO, "IncreaseTempo", "Click this to increase tempo.") {
		@Override
		void actionListenerWork() {
			AudioPlayerFacade.increaseTempo();
		}
	};

	UiButtons QUIT = new ButtonBuilder("Quit", "Click this to quit!") {
		@Override
		void actionListenerWork() {
			AudioPlayerFacade.stop();
			System.exit(0);
		}
	};

	JButton getButton();

	String text();

	String tooltip();

	abstract class ButtonBuilder implements UiButtons {
		private final String imageName;
		private final JButton resultButton;
		private final String text;
		private final String tooltip;

		ButtonBuilder(String text, String tooltip) {
			this.text = text;
			this.tooltip = tooltip;
			this.imageName = null;
			this.resultButton = createButton();
		}

		ButtonBuilder(String imageName, String text, String tooltip) {
			this.imageName = imageName;
			this.text = text;
			this.tooltip = tooltip;
			this.resultButton = createButton();
		}

		@Override
		public JButton getButton() {
			return resultButton;
		}

		@Override
		public String text() {
			return text;
		}

		@Override
		public String tooltip() {
			return tooltip;
		}

		abstract void actionListenerWork();

		private JButton createButton() {
			JButton button = createImageButton();
			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					actionListenerWork();
				}
			};
			button.addActionListener(actionListener);
			return button;
		}

		private JButton createImageButton() {
			if (imageName != null) {
				try {
					JButton imageButton = new JButton(imageResourceCache.imageIcon(imageName));
					imageButton.setForeground(Color.BLUE);
					imageButton.setBorder(BorderFactory.createEmptyBorder());
					imageButton.setPreferredSize(new Dimension(30, 30));
					return imageButton;
				} catch (Exception e) {
				}
			}
			return new JButton(text);
		}
	}

	public class ImageResourceCache {
		private static final Logger logger = LoggerFactory.getLogger(ImageResourceCache.class);
		Map<String, ImageIcon> cachedResoureces = new ConcurrentHashMap<String, ImageIcon>();

		private static ImageIcon imageIconLookup(String imageName) {
			try {
				BufferedImage readImage = ImageIO.read(UiButtons.class.getResource(imageName));
				return new ImageIcon(readImage);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return null;
			}
		}

		public synchronized ImageIcon imageIcon(String imageName) {
			ImageIcon existing = cachedResoureces.get(imageName);
			if (existing != null) {
				return existing;
			}
			ImageIcon imageIconLookup = imageIconLookup(imageName);
			cachedResoureces.put(imageName, imageIconLookup);
			return imageIconLookup;
		}

	}

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
		PLAYER_FOR_ALL("PLAY_ALL", "Click this to play all!") {
			@Override
			JButton createInstanceWith(String name) {
				JButton button = fixedNameButton(this);
				return button;
			}
		},
		QUIZ_PLAY("Play $$", "Click this to play $$") {
			@Override
			JButton createInstanceWith(String buttonName) {
				JButton button = configurableNamedButton(this, buttonName);
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

		private static JButton fixedNameButton(UiButtons itemType) {
			JButton resultButton = new JButton(itemType.text());
			resultButton.setToolTipText(itemType.tooltip());
			return resultButton;
		}

		public JButton getButton() {
			return getButtonWithNameSuffix("");
		}

		public JButton getButtonWithNameSuffix(String buttonName) {
			JButton buttonItem = createInstanceWith(buttonName);
			buttonItem.setOpaque(true);
			buttonItem.setToolTipText(tooltip);
			return buttonItem;
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
