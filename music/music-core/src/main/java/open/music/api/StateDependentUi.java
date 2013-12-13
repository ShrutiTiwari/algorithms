/**
 * 
 */
package open.music.api;

import java.awt.TextArea;

import javax.swing.JButton;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface StateDependentUi {

	public JButton pauseButton();

	public void updateConsole(String displayText);

	/**
	 * @param displayText
	 */
	public void appendToConsole(String displayText);

	/**
	 * @param playableName
	 */
	public void updatePlayable(String playableName);
}