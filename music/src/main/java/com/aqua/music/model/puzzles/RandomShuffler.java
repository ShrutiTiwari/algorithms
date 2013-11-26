package com.aqua.music.model.puzzles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomShuffler<T> {
	/**
	 * Used for re-initialising.
	 */
	private final List<T> initialList;

	/**
	 * Used for keeping track of selected items
	 */
	private final AtomicInteger lastResultIndex = new AtomicInteger();
	private List<T> mutableList;

	public RandomShuffler(Collection<T> inputCollection) {
		if (inputCollection == null || inputCollection.isEmpty() || inputCollection.size() < 2) {
			throw new RuntimeException("invalid input ... list is null or has less than 2 items");
		}
		this.initialList = Collections.unmodifiableList(new ArrayList<T>(inputCollection));
		this.mutableList = new ArrayList<T>(inputCollection);
	}

	public synchronized T nextRandom() {
		return (mutableList.size() == 1) ? chooseOnlyAvailableItemAndReintialize() : randomlyChooseOne();
	}

	private T chooseOnlyAvailableItemAndReintialize() {
		T result = mutableList.get(0);
		this.mutableList = new ArrayList<T>(initialList);
		return result;
	}

	private T randomlyChooseOne() {
		int oldValue = lastResultIndex.get();
		int newVal = -1;
		do {
			newVal = new Random().nextInt(mutableList.size());
		} while (!lastResultIndex.compareAndSet(oldValue, newVal));
		return mutableList.remove(lastResultIndex.get());
	}
}
