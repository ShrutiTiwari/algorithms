package com.aqua.music.model.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;

public class RandomShufflerTest {
	@Test
	public void testShuffleShouldReturnAllElementsInEachIterationWithFrequencySetInput() {
		FrequencySet[] freqSets = SymmetricalSet.values();
		List<FrequencySet> sampleInputList = Arrays.asList(freqSets);
		sortedPrint(sampleInputList);
		RandomShuffler<FrequencySet> randomShuffler = new RandomShuffler<FrequencySet>(sampleInputList);
		
		for (int k = 0; k < 3; k++) {
			List<FrequencySet> iterationResult=new ArrayList<FrequencySet>();
			for (int j = 0; j < sampleInputList.size(); j++) {
				iterationResult.add(randomShuffler.nextRandom());
			}
			//Collections.sort(iterationResult);
			System.out.println("Iteration" + k + " results[" + iterationResult + "]");
			TestCase.assertTrue(iterationResult.containsAll(sampleInputList));
		}
	}

	@Test
	public void testShuffleShouldReturnAllElementsInEachIterationWithIntegerInput() {
		List<Integer> sampleInputList = sampleIntegerInput();
		sortedPrint(sampleInputList);
		RandomShuffler<Integer> randomShuffler = new RandomShuffler<Integer>(sampleInputList);
		
		for (int k = 0; k < 3; k++) {
			List<Integer> iterationResult=new ArrayList<Integer>();
			for (int j = 0; j < sampleInputList.size(); j++) {
				iterationResult.add(randomShuffler.nextRandom());
			}
			//Collections.sort(iterationResult);
			System.out.println("Iteration" + k + " results[" + iterationResult + "]");
			TestCase.assertTrue(iterationResult.containsAll(sampleInputList));
		}
	}

	private List<Integer> sampleIntegerInput() {
		List<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			items.add(i);
		}
		return Collections.unmodifiableList(items);
	}

	private void sortedPrint(List sampleInputList1) {
		List list= new ArrayList(sampleInputList1);
		Collections.sort(list);
		System.out.println("Original list[" + list + "]");
	}
}
