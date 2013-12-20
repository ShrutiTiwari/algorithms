/**
 * 
 */
package open.music.api;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.core.BaseNote.Octave;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;

/**
 * @author "Shruti Tiwari"
 */
public class PracticeCustomization {
	private PracticeCustomization DEFAULT= new PracticeCustomization(NoteFragments.ALL_NOTE, Octave.MAIN_OCTAVE,Pattern.BOTH_ASCEND_DESCEND);
	private final NoteFragments noteFragments;
	private final Octave octave;
	private final Pattern pattern;

	public PracticeCustomization(NoteFragments noteFragments, Octave octave){
		this(noteFragments,octave, Pattern.BOTH_ASCEND_DESCEND);
	}
	
	public PracticeCustomization(NoteFragments noteFragments, Octave octave, Pattern pattern) {
		this.noteFragments = noteFragments;
		this.octave = octave;
		this.pattern = pattern;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		PracticeCustomization cust= (PracticeCustomization)obj;
		return (this.noteFragments==cust.noteFragments)&&(this.octave==cust.octave)&&(this.pattern==cust.pattern);
	}
	
	
	/**
	 * @param freqSet
	 * @return
	 */
	public FrequencySet applyOn(FrequencySet freqSet) {
		if(this.equals(DEFAULT)){
			return freqSet;
		}
		
		final List<Frequency> ascL= new ArrayList<Frequency>();
		final List<Frequency> descL= new ArrayList<Frequency>();
		
		
		FrequencySet result = new FrequencySet() {
			@Override
			public Frequency[] ascendNotes() {
				return ascL.toArray(new Frequency[ascL.size()]);
			}
			
			@Override
			public Frequency[] descendNotes() {
				return descL.toArray(new Frequency[descL.size()]);
			}
			
			@Override
			public String name() {
				return null;
			}
			
			@Override
			public String type() {
				return null;
			}
		};
		return null;
	}
	public NoteFragments noteFragments() {
		return noteFragments;
	}
	public Octave octave() {
		return octave;
	}

	public Pattern pattern() {
		return pattern;
	}

	PracticeCustomization getDefault(){
		return DEFAULT;
	}

	enum NoteFragments {
		ALL_NOTE,
		LOWER_HALF,
		UPPER_HALF;

		/**
		 * 
		 */
		public void trimAscend() {
			
		}
	}
	
	enum Pattern {
		ASCEND_ONLY,
		BOTH_ASCEND_DESCEND,
		DESCEND_ONLY;
	}
}
