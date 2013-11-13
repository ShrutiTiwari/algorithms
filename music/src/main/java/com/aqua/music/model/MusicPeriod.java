package com.aqua.music.model;

public interface MusicPeriod {
	final int ONE_SEC = 700;
	
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
			this.durationInMilliSec = numOfSingleBeats*SINGLE_BEAT.durationInMilliSec();
		}
		
		@Override
		public int durationInMilliSec() {
			return durationInMilliSec;
		}
		
	}
}
