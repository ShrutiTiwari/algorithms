/**
 * 
 */
package open.music.api;

import com.aqua.music.model.core.Frequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
public interface StateDependentUi {

	/**
	 * @param displayText
	 */
	public void appendToConsole(String displayText);

	public void setPauseToDisplay();

	public void updateConsole(String displayText);

	public void updateInstrument(String instrument);

	/**
	 * @param playableName
	 */
	public void updatePlayable(String playableName);

	/**
	 * @param multipler
	 */
	public void updateTempo(int multipler);

	public void setStartEndPoints(Frequency[] startEndPoints);

	public void registerStartEndPointChangeListener(StartEndPointChangeListener listener);

	public interface StartEndPointChangeListener {
		public void changedEndPoints(Frequency[] startEndPoints);
	}
}