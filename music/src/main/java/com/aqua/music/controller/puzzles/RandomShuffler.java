package com.aqua.music.controller.puzzles;

import java.util.ArrayList;
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

	public RandomShuffler(List<T> inputList) {
		if (inputList == null || inputList.isEmpty() || inputList.size() < 2) {
			throw new RuntimeException("invalid input ... list is null or has less than 2 items");
		}
		this.initialList = Collections.unmodifiableList(new ArrayList<T>(inputList));
		this.mutableList = new ArrayList<T>(inputList);
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
