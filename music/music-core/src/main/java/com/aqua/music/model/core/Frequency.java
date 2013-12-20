
package com.aqua.music.model.core;

import com.aqua.music.model.core.BaseNote.Octave;


/**
 * @author "Shruti Tiwari"
 *
 */
public interface Frequency extends DynamicFrequency {
	public String prettyPrint();

	public String western();
	
	public BaseNote baseNote();
	
	public Octave octave();
}