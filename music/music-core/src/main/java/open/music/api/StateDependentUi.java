/**
 * 
 */
package open.music.api;


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
}