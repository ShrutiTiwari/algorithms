package com.aqua.music.model.puzzles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class PuzzleBuilder<T> {
	private final Collection<List<T>> bucketedItems = new ArrayList<List<T>>();

	PuzzleBuilder(T[] input, int bucketSize) {
		List<T> segregatedItem = new ArrayList<T>();
		bucketedItems.add(segregatedItem);
		T lastItem = null;
		int groupProcessedItems = 0;
		int overallProcessedItems = 0;
		for (T each : input) {
			lastItem = each;
			segregatedItem.add(each);
			overallProcessedItems++;
			groupProcessedItems++;
			if (groupProcessedItems == bucketSize) {
				if (((input.length - overallProcessedItems) + 1) >= bucketSize) {
					segregatedItem = new ArrayList<T>();
					segregatedItem.add(lastItem);
					bucketedItems.add(segregatedItem);
					groupProcessedItems = 1;
				}
			}
		}
	}

	Collection<List<T>> allBuckets() {
		return Collections.unmodifiableCollection(bucketedItems);
	}
}
