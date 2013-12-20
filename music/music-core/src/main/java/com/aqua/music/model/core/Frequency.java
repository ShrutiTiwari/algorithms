
package com.aqua.music.model.core;


/**
 * @author "Shruti Tiwari"
 *
 */
public interface Frequency extends DynamicFrequency {
	public String prettyPrint();

	public String western();
	
	public BaseNote baseNote();
}