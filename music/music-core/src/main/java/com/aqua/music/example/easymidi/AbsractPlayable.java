/**
 * 
 */
package com.aqua.music.example.easymidi;

/**
 * @author "Shruti Tiwari"
 * 
 */
public abstract class AbsractPlayable implements Playable {
	int counter16 = 0;

	boolean on = false;

	private final int divisionInSixteen;

	AbsractPlayable(int divisionInSixteen) {
		this.divisionInSixteen = divisionInSixteen;
	}

	public void play() {
		playAction();
		counter16 = 0;
		on = true;
	}

	public void stop() {
		stopAction();
		counter16 = 0;
		on = false;
	}

	public void tick() {
		if (on) {
			counter16++;
			if (counter16 >= divisionInSixteen) {
				stop();
			}
		}
	}

	abstract void playAction();

	abstract void stopAction();
	
	public static class Drum extends AbsractPlayable {
		private final int instrument;
		private final int velocity;

		public Drum(int part, int instrument) {
			this(part, instrument, 127);
		}

		public Drum(int part, int instrument, int velocity) {
			super(16 / part);
			this.instrument = instrument;
			this.velocity = velocity;
		}

		@Override
		void playAction() {
			Tools.openDrum(instrument, velocity);
		}

		@Override
		void stopAction() {
			Tools.closeDrum(instrument);
		}
	}
	public static class Note extends AbsractPlayable {
		private int channel = 0;
		private final int instrument;
		private final int pitch;
		private final int velocity = 127;

		public Note(int part, int pitch, int instrument) {
			super(16 / part);
			this.instrument = instrument;
			this.pitch = pitch;
		}

		@Override
		void playAction() {
			channel = Tools.openNote(instrument, pitch, velocity);
		}

		@Override
		void stopAction() {
			Tools.closeNote(pitch, channel);
		}
	}

}
