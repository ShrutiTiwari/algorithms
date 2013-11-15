package com.aqua.music.model.puzzles;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;

public class PuzzleBuilderTest {
	private final String expectedResult = "[[THAAT_BHAIRAV, THAAT_PURVI, THAAT_MARWA], [THAAT_MARWA, THAAT_KALYAN, THAAT_BILAWAL], [THAAT_BILAWAL, THAAT_KHAMAJ, THAAT_KAFI], [THAAT_KAFI, THAAT_ASAVARI, THAAT_BHAIRAVI], [THAAT_BHAIRAVI, THAAT_TODI, RAAG2_BAIRAGI, RAAG2_GUJARI_TODI]]";

	@Test
	public void testPuzzleBuilderResultWithFrequencySet() {
		SymmetricalSet[] sampleInput = SymmetricalSet.values();
		int bucketSize = 3;
		int expectedNumberOfBuckets = numberOfBuckets(sampleInput.length, bucketSize);
		PuzzleBuilder<FrequencySet> puzzleBuilder = new PuzzleBuilder(sampleInput, bucketSize);
		print(puzzleBuilder);
		TestCase.assertEquals(expectedNumberOfBuckets, puzzleBuilder.allBuckets().size());

		int i = 0;
		for (List<FrequencySet> buckets : puzzleBuilder.allBuckets()) {
			int actualBucketSize = buckets.size();
			if (i < (bucketSize - 1)) {
				TestCase.assertEquals(bucketSize, actualBucketSize);
			} else {
				TestCase.assertTrue(bucketSize == actualBucketSize || actualBucketSize < (bucketSize * 2));
			}
			i++;
		}

	}

	private void print(PuzzleBuilder<FrequencySet> puzzle) {
		System.out.println(puzzle.allBuckets());
		for (List<FrequencySet> each : puzzle.allBuckets()) {
			System.out.println(each);
		}
	}

	private int numberOfBuckets(int n, int r) {
		if (n <= r) {
			return 1;
		}

		int pivotalItemIndex = r;
		int k = 1;
		while (pivotalItemIndex <= n) {
			pivotalItemIndex = r * (k + 1) - (k);
			k++;
		}
		return k - 1;
	}
}
