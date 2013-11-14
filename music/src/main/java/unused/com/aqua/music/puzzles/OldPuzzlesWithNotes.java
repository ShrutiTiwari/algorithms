package unused.com.aqua.music.puzzles;

import static com.aqua.music.model.Frequency.ClassicalNote.D;
import static com.aqua.music.model.Frequency.ClassicalNote.D_;
import static com.aqua.music.model.Frequency.ClassicalNote.G;
import static com.aqua.music.model.Frequency.ClassicalNote.G_;
import static com.aqua.music.model.Frequency.ClassicalNote.M;
import static com.aqua.music.model.Frequency.ClassicalNote.M_;
import static com.aqua.music.model.Frequency.ClassicalNote.N;
import static com.aqua.music.model.Frequency.ClassicalNote.N_;
import static com.aqua.music.model.Frequency.ClassicalNote.R;
import static com.aqua.music.model.Frequency.ClassicalNote.R_;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.Frequency;

public class OldPuzzlesWithNotes {
	private static final Logger logger = LoggerFactory.getLogger(OldPuzzlesWithNotes.class);
	static HashSet<Frequency> saTopa = new HashSet<Frequency>();
	static HashSet<Frequency> paToHighSa = new HashSet<Frequency>();
	static int REPEAT_COUNT = 6;

	public static void main(String[] args) {
		initialize();
		// alternatePlay();
		sequentialPay();
	}

	private static void alternatePlay(HashSet<Frequency>... saTopa2) {
		Iterator<Frequency> iterator1 = saTopa.iterator();
		Iterator<Frequency> iterator2 = paToHighSa.iterator();
		List<Frequency> playNotes = new ArrayList<Frequency>();
		for (int index = 0; index < paToHighSa.size(); index++) {
			playNotes.add(iterator1.next());
			playNotes.add(iterator2.next());
		}
		play(playNotes);
		playNotes = new ArrayList<Frequency>();
		for (int index = paToHighSa.size(); index < saTopa.size(); index++) {
			playNotes.add(iterator1.next());
		}
		play(playNotes);
	}

	private static void initialize() {
		populateNotes(saTopa, R, R_, G, G_, M, M_);
		populateNotes(paToHighSa, D, D_, N, N_);
	}

	private static void play(HashSet<Frequency> saTopa2) {
		List<Frequency> playNotes = new ArrayList<Frequency>();
		for (int i = 0; i < REPEAT_COUNT; i++) {
			logger.info("\n");
			for (Frequency each : saTopa2) {
				playNotes.add(each);
			}
		}
		play(playNotes);
	}

	private static void populateNotes(HashSet<Frequency> agreegatator, Frequency... notes) {
		for (Frequency each : notes) {
			agreegatator.add(each);
		}
	}

	private static void sequentialPay() {
		logger.info("Sa to pa");
		play(saTopa);
		logger.info("\n pa to high sa");
		play(paToHighSa);
	}

	public static void play(List<Frequency> notes) {
		StringBuffer printPlaylist = new StringBuffer();
		for (Frequency each : notes) {
			printPlaylist.append(", " + each);
		}
		logger.info("playing [" + printPlaylist.toString() + "]");
	}
}
