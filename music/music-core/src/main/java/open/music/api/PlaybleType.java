package open.music.api;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.raag.song.Song;

/**
 * @author "Shruti Tiwari"
 *
 */
public enum PlaybleType {
	PLAIN_THAAT {
		@Override
		List<Playable> playables() {
			List<Playable> result = new ArrayList<Playable>();
			for (FrequencySet each : SymmetricalSet.values()) {
				CyclicFrequencySet symmetricalSet = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet((FrequencySet) each);
				result.add(symmetricalSet);
			}
			return result;
		}

	},
	SONG {
		@Override
		List<Playable> playables() {
			List<Playable> result = new ArrayList<Playable>();
			for (Song each : Song.values()) {
				result.add(each);
			}
			return result;
		}

	};
	abstract List<Playable> playables();
}
