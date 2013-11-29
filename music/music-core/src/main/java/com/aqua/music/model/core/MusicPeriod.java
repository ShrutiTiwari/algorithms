package com.aqua.music.model.core;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface MusicPeriod {
	//final int ONE_SEC = 700;
	final int ONE_SEC = 800;
	
	int durationInMilliSec();
	
	final MusicPeriod SINGLE_BEAT = new MusicPeriod() {
		@Override
		public int durationInMilliSec() {
			return ONE_SEC;
		}
	};
	
	final MusicPeriod HALF_BEAT = new MusicPeriod() {
		@Override
		public int durationInMilliSec() {
			return ONE_SEC/2;
		}
	};
	
	
	
	class CustomizedDuration implements MusicPeriod{
		private final int durationInMilliSec;
		
		CustomizedDuration(final int numOfSingleBeats){
			this.durationInMilliSec =  numOfSingleBeats*SINGLE_BEAT.durationInMilliSec();
		}
		
		public CustomizedDuration(final double numOfSingleBeats){
			this.durationInMilliSec = (int) (numOfSingleBeats*((double)SINGLE_BEAT.durationInMilliSec()));
		}
		
		@Override
		public int durationInMilliSec() {
			return durationInMilliSec;
		}
		
	}
}
