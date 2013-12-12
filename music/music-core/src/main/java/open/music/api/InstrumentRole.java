/**
 * 
 */
package open.music.api;

import javax.sound.midi.Instrument;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;

public enum InstrumentRole {
	MAIN,
	RHYTHM;

	public void setTo(Instrument instrument) {
		AudioLifeCycleManager.instance.changeInstrumentTo(instrument, this);
	}
}