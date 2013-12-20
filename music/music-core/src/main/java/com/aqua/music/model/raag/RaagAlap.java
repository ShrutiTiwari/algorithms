package com.aqua.music.model.raag;

import java.util.ArrayList;
import java.util.Collection;

import open.music.api.Playable;
import open.music.api.PracticeCustomization;

import com.aqua.music.model.core.DynamicFrequency;


/**
 * This set uses different set of notes in ascend and descend
 * 
 * @author shruti.tiwari
 * 
 */
public enum RaagAlap implements Playable{
	ALAP_BHIMPALASI(new Bhimplasi());
	;

	private final FrequencyCollector frequencyCollector;
	
	private RaagAlap(FrequencyCollector frequencyCollector) {
		this.frequencyCollector = frequencyCollector;
	}

	@Override
	public String asText() {
		return this.name();
	}

	@Override
	public Collection<? extends DynamicFrequency> frequencies() {
		return frequencyCollector.frequencies();
	}
	

	interface FrequencyCollector{
		public Collection<? extends DynamicFrequency> frequencies();	
	}
	
	public static class Bhimplasi implements FrequencyCollector{
		@Override
		public Collection<? extends DynamicFrequency> frequencies() {
			Collection<DynamicFrequency> resul= new ArrayList<DynamicFrequency>();
			
			
			
			return null;
		}

	}

	/* (non-Javadoc)
	 * @see open.music.api.Playable#frequencies(open.music.api.PracticeCustomization)
	 */
	@Override
	public Collection<? extends DynamicFrequency> frequencies(PracticeCustomization practiceCustomization) {
		// TODO Auto-generated method stub
		return null;
	}

	
}