/**
 * 
 */
package com.aqua.music.model.cyclicset;

import static com.aqua.music.model.core.ClassicalNote.D1;
import static com.aqua.music.model.core.ClassicalNote.D1_;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G_;
import static com.aqua.music.model.core.ClassicalNote.N1;
import static com.aqua.music.model.core.ClassicalNote.N1_;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.S;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.core.Frequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
public enum GradualCombination {
	SaRe(FrequencyGroup.fromMainNote(true, R, R_, S)),
	DaSa(FrequencyGroup.fromMainNote(false, D1, D1_, S)),
	SaReGa(FrequencyGroup.fromMainNote(true, G, G_, S, R, R_), FrequencyGroup.fromCombo(true, G, G_, SaRe)),
	NiSaRe(FrequencyGroup.fromMainNote(false, N1, N1_, S, R, R_), FrequencyGroup.fromCombo(false, N1, N1_, SaRe));
	//DhaNiSa(FrequencyGroup.fromCombo(false, D1, D1_, NiSa));

	private final List<FrequencyGroup> frequencyGroup = new ArrayList<GradualCombination.FrequencyGroup>();

	private GradualCombination(FrequencyGroup... frequencyGroups) {
		for (FrequencyGroup each : frequencyGroups) {
			this.frequencyGroup.add(each);
		}
	}

	List<FrequencyGroup> frequencyGroup() {
		return frequencyGroup;
	}

	static class FrequencyGroup {
		private final List<List<Frequency>> frequenciesList = new ArrayList<List<Frequency>>();

		private void add(List<Frequency>... items) {
			for (List<Frequency> each : items) {
				frequenciesList.add(each);
			}
		}

		String print() {
			StringBuffer buf = new StringBuffer();
			for (List<Frequency> each : frequenciesList) {
				buf.append(each + "\n");
			}
			return buf.toString();
		}

		private static FrequencyGroup fromCombo(boolean isOneNoteLast, Frequency oneOfNote1, Frequency oneOfNote2,
				GradualCombination parentCombo) {
			FrequencyGroup instance = new FrequencyGroup();
			for (FrequencyGroup each : parentCombo.frequencyGroup) {
				instance.frequenciesList.addAll(copyListAndAdd(isOneNoteLast, oneOfNote1, each));
				instance.frequenciesList.addAll(copyListAndAdd(isOneNoteLast, oneOfNote2, each));
			}
			return instance;
		}

		private static FrequencyGroup fromMainNote(boolean isOneNoteLast, Frequency oneOfNote1, Frequency oneOfNote2,
				Frequency... mainNotes) {
			FrequencyGroup instance = new FrequencyGroup();
			for (Frequency eachMainNote : mainNotes) {
				List<Frequency> freqs1 = new ArrayList<Frequency>();
				List<Frequency> freqs2 = new ArrayList<Frequency>();

				if (isOneNoteLast) {
					freqs1.add(eachMainNote);
					freqs2.add(eachMainNote);

					freqs1.add(oneOfNote1);
					freqs2.add(oneOfNote2);
				} else {
					freqs1.add(oneOfNote1);
					freqs2.add(oneOfNote2);

					freqs1.add(eachMainNote);
					freqs2.add(eachMainNote);
				}

				instance.add(freqs1);
				instance.add(freqs2);
			}
			return instance;
		}

		private static List<List<Frequency>> copyListAndAdd(boolean isOneNoteLast, Frequency oneOfNote1, FrequencyGroup each) {
			List<List<Frequency>> allFreqList = new ArrayList<List<Frequency>>();
			for (List<Frequency> each1 : each.frequenciesList) {
				List<Frequency> dest1 = null;
				if (isOneNoteLast) {
					dest1 = new ArrayList<Frequency>(each1);
					dest1.add(oneOfNote1);
				} else {
					dest1 = new ArrayList<Frequency>();
					dest1.add(oneOfNote1);
					dest1.addAll(each1);
				}
				allFreqList.add(dest1);
			}
			return allFreqList;
		}
	}
}
