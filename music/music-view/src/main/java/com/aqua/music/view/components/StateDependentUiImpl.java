/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JPanel;

import open.music.api.StateDependentUi;

import com.aqua.music.view.components.CommonTopPanel.CurrentState;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class StateDependentUiImpl implements StateDependentUi  {
	private final TextArea consoleArea;
	private final JButton pauseButton;
	private final CommonTopPanel commonTopPanel;
	
	public StateDependentUiImpl() {
		this.commonTopPanel = new CommonTopPanel();
		this.pauseButton = commonTopPanel.pauseButton();
		this.consoleArea = createConsoleArea();
	}

	@Override
	public JButton pauseButton() {
		return pauseButton;
	}
	
	@Override
	public void updateConsole(String displayText) {
		consoleArea.setText(displayText);
	}

	@Override
	public void appendToConsole(String text) {
		consoleArea.append(text);
	}
	private TextArea createConsoleArea() {
		TextArea textArea = new TextArea("Played notes will be displayed here in indian scale....");
		textArea.setEditable(false);
		textArea.setVisible(true);
		return textArea;
	}
	
	public TextArea consoleArea(){
		return consoleArea; 
	}
	
	public JPanel topPanel() {
		return commonTopPanel.getPanel();
	}

	@Override
	public void updatePlayable(String playableName) {
		commonTopPanel.currentState().setCurrentPlayable(playableName);
	}
}
