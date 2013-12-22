/**
 * 
 */
package open.music.api;

import com.aqua.music.model.core.BaseNote.Octave;
import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;

/**
 * @author "Shruti Tiwari"
 */
public class PracticeCustomization {
	private final ClassicalNote[] completeLowerOctave = new ClassicalNote[] { ClassicalNote.S1, ClassicalNote.S };
	private final ClassicalNote[] completeMiddleOctave = new ClassicalNote[] { ClassicalNote.S, ClassicalNote.S3 };
	private final ClassicalNote[] completeUpperOctave = new ClassicalNote[] { ClassicalNote.S3, ClassicalNote.S4 };

	private final ClassicalNote[] lowerLowerOctave = new ClassicalNote[] { ClassicalNote.S1, ClassicalNote.P1 };
	private final ClassicalNote[] lowerMiddleOctave = new ClassicalNote[] { ClassicalNote.S, ClassicalNote.P };
	private final ClassicalNote[] lowerUpperOctave = new ClassicalNote[] { ClassicalNote.S3, ClassicalNote.P3 };
	
	private final ClassicalNote[] upperLowerOctave = new ClassicalNote[] { ClassicalNote.P1, ClassicalNote.S };
	private final ClassicalNote[] upperMiddleOctave = new ClassicalNote[] { ClassicalNote.P, ClassicalNote.S3 };
	private final ClassicalNote[] upperUpperOctave = new ClassicalNote[] { ClassicalNote.P3, ClassicalNote.S4 };
	
	private final NoteFragments noteFragments;
	private final Octave octave;
	
	public PracticeCustomization(NoteFragments noteFragments, Octave octave) {
		this.noteFragments = noteFragments;
		this.octave = octave;
	}
	@Override
	public boolean equals(Object obj) {
		PracticeCustomization cust = (PracticeCustomization) obj;
		return (this.noteFragments == cust.noteFragments) && (this.octave == cust.octave);
	}

	/**
	 * @param freqSet
	 * @return
	 */
	public Frequency[] getStartEndPoint() {
		if (this.octave==Octave.MAIN_OCTAVE&&this.noteFragments==NoteFragments.ALL_NOTE) {
			return completeMiddleOctave;
		}

		switch (noteFragments) {
		case ALL_NOTE:
			switch (octave) {
			case LOWER_OCTAVE:
				return completeLowerOctave;
			case UPPER_OCTAVE:
				return completeUpperOctave;
			default:
				return completeMiddleOctave;
			}
		case UPPER_HALF:
			switch (octave) {
			case LOWER_OCTAVE:
				return upperLowerOctave;
			case UPPER_OCTAVE:
				return upperUpperOctave;
			default:
				return upperMiddleOctave;
			}
		case LOWER_HALF:
			switch (octave) {
			case LOWER_OCTAVE:
				return lowerLowerOctave;
			case UPPER_OCTAVE:
				return lowerUpperOctave;
			default:
				return lowerMiddleOctave;
			}
		}
		return completeMiddleOctave;
	}

	public enum Pattern {
		ASCEND_ONLY,
		BOTH_ASCEND_DESCEND,
		DESCEND_ONLY;
	}
}
