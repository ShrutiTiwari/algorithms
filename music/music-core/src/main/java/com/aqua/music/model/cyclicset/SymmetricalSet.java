package com.aqua.music.model.cyclicset;

import static com.aqua.music.model.core.BaseNote.D;
import static com.aqua.music.model.core.BaseNote.D_;
import static com.aqua.music.model.core.BaseNote.G;
import static com.aqua.music.model.core.BaseNote.G_;
import static com.aqua.music.model.core.BaseNote.M;
import static com.aqua.music.model.core.BaseNote.M_;
import static com.aqua.music.model.core.BaseNote.N;
import static com.aqua.music.model.core.BaseNote.N_;
import static com.aqua.music.model.core.BaseNote.P;
import static com.aqua.music.model.core.BaseNote.R;
import static com.aqua.music.model.core.BaseNote.R_;
import static com.aqua.music.model.core.BaseNote.S;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.core.BaseNote;
import com.aqua.music.model.core.BaseNote.Octave;
import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;

/**
 * 
 * This set uses same set of notes in ascend and descend
 * 
 * @author shruti.tiwari
 * 
 */
public enum SymmetricalSet implements FrequencySet {
	THAAT_ASAVARI(S, R, G_, M, P, D_, N_),
	THAAT_BHAIRAV(S, R_, G, M, P, D_, N),
	THAAT_BHAIRAVI(S, R_, G_, M, P, D_, N_),
	THAAT_BILAWAL(S, R, G, M, P, D, N),
	THAAT_KAFI(S, R, G_, M, P, D, N_),
	THAAT_KALYAN(S, R, G, M_, P, D, N),
	THAAT_KHAMAJ(S, R, G, M, P, D, N_),
	THAAT_MARWA(S, R_, G, M_, P, D, N),
	THAAT_PURVI(S, R_, G, M_, P, D_, N),
	THAAT_TODI(S, R_, G_, M_, P, D_, N);

	private final BaseNote[] baseAscendNotes;
	private final Frequency[] mainOctaveAscendNotes;
	private final Frequency[] mainOctaveDescendNotes;
	private final Logger logger = LoggerFactory.getLogger(SymmetricalSet.class);

	private SymmetricalSet(BaseNote... ascendNotes) {
		this.baseAscendNotes = ascendNotes;
		this.mainOctaveAscendNotes = new Frequency[baseAscendNotes.length + 1];
		int i = 0;
		for (BaseNote each : baseAscendNotes) {
			mainOctaveAscendNotes[i++] = each.getFrequencyObject(Octave.MAIN_OCTAVE);
		}
		mainOctaveAscendNotes[i] = baseAscendNotes[0].getFrequencyObject(Octave.UPPER_OCTAVE);
		this.mainOctaveDescendNotes = Util.reverse(mainOctaveAscendNotes);
	}

	public Frequency[] ascendNotes() {
		return mainOctaveAscendNotes;
	}

	public Frequency[] descendNotes() {
		return mainOctaveDescendNotes;
	}

	public Frequency[][] ascendDescendNotes(Frequency startClassicalNote, Frequency endClassicalNote) {
		List<Frequency> resultAscendNoteList = new ArrayList<Frequency>();
		Octave octave = startClassicalNote.octave();
		boolean startNoteFound = false;
		boolean endNoteFound = false;

		ClassicalNote startMainNote= (ClassicalNote)startClassicalNote;	
		ClassicalNote startAlternateNote=findAlternativeNote(startMainNote);
		
		ClassicalNote endMainNote= (ClassicalNote)endClassicalNote;
		ClassicalNote endAlternateNote=findAlternativeNote(endMainNote);
		
		do {
			for (BaseNote each : baseAscendNotes) {
				if (!(each == startClassicalNote.baseNote() || each == startAlternateNote.baseNote())) {
					if (!startNoteFound) {
						continue;
					}
				} else {
					startNoteFound = true;
				}
				Frequency currentNote = each.getFrequencyObject(octave);
				resultAscendNoteList.add(currentNote);
				float currentNoteFreq = currentNote.frequencyInHz();
				if (currentNoteFreq == endClassicalNote.frequencyInHz() || currentNoteFreq == endAlternateNote.frequencyInHz()) {
					endNoteFound=true;
					break;
				}
			}
			octave = octave.next();
		}while (!endNoteFound);
		Frequency[] resultAscendNotes = resultAscendNoteList.toArray(new Frequency[resultAscendNoteList.size()]);
		return new Frequency[][] { resultAscendNotes, Util.reverse(resultAscendNotes) };
	}

	private ClassicalNote findAlternativeNote(ClassicalNote endMainNote) {
		if((endMainNote.baseNote()==BaseNote.S)||(endMainNote.baseNote()==BaseNote.P)){
			return endMainNote;
		} 
		String endMainNoteName = endMainNote.name();
		String alternativeEndMainNoteName = endMainNote.name();
		if(endMainNoteName.contains("_")){
			alternativeEndMainNoteName=endMainNoteName.replace("_", "");
		}else{
			alternativeEndMainNoteName=endMainNoteName + "_";
		}
		return ClassicalNote.valueOf(alternativeEndMainNoteName);
	}

	public String type() {
		return "THAAT";
	}
}