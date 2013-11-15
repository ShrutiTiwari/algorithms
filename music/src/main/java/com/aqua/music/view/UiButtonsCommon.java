package com.aqua.music.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

enum UiButtonsCommon implements UiButtons{
	QUIT("Quit", "Click this to quit!") {
		@Override
		JButton createInstanceWith() {
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
	private final String text;
	private final String tooltip;

	private UiButtonsCommon(String text, String tooltip) {
		this.text = text;
		this.tooltip = tooltip;
	}

	static JButton configurableNamedButton(UiButtons itemType, String replaceName) {
		JButton resultButton = new JButton(itemType.text().replace("$$", replaceName));
		resultButton.setToolTipText(itemType.tooltip().replace("$$", replaceName));
		return resultButton;
	}

	static JButton fixedNameButton(UiButtons itemType) {
		JButton resultButton = new JButton(itemType.text());
		resultButton.setToolTipText(itemType.tooltip());
		return resultButton;
	}
	
	public JButton createButton(int yCoordinate) {
		JButton buttonItem = createInstanceWith();
		buttonItem.setOpaque(true);
		buttonItem.setBounds(X_COORIDNATE, yCoordinate, BUTTON_WIDTH, BUTTON_HEIGHT);
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
	abstract JButton createInstanceWith();
}
