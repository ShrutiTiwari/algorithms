/**
 * 
 */
package open.music.api;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;

public enum InstrumentRole {
	MAIN,
	RHYTHM;

	public void setTo(String instrument) {
		AudioLifeCycleManager.instance.changeInstrumentTo(SingletonFactory.PLAY_API.findInstrument(instrument), this);
	}
}