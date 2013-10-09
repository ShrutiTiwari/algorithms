package com.aqua.numbers.sorting;

public class MergeSortArray {

	public static void main(String[] args) {
		int[] numberContainer = new int[] { 2, 3 };
		int[] result = new MergeSortArray().sort(numberContainer, 0,
				numberContainer.length - 1);
		for (int each : result) {
			System.out.println(each);
		}
	}

	int[] sort(int[] numberContainer) {
		return sort(numberContainer, 0, numberContainer.length - 1);
	}

	int[] sort(int[] numberContainer, int start, int end) {
		int count = Math.abs(end - start) + 1;
		if (baseCondition(count)) {
			return baseCase(numberContainer, start, end, count);
		} else {
			int midWay = count / 2;
			int[] sort1 = sort(numberContainer, start, start + midWay);
			int[] sort2 = sort(numberContainer, start + midWay + 1, end);
			return merge(sort1, sort2);
		}
	}

	private int[] baseCase(int[] numberContainer, int start, int end, int count) {
		int num1 = numberContainer[start];
		int num2 = numberContainer[end];
		return (count == 1) ? new int[] { num1 } : (num1 >= num2) ? new int[] {
				num2, num1 } : new int[] { num1, num2 };
	}

	private boolean baseCondition(int count) {
		return count == 1 || count == 2;
	}

	/**
	 * @param sortedInput1
	 * @param sortedInput2
	 * 
	 *            Check the two arrays item and item and merge them together
	 * @return
	 */
	private int[] merge(int[] sortedInput1, int[] sortedInput2) {
		int[] result = new int[sortedInput1.length + sortedInput2.length];
		int currentIndexOfInput1 = 0;
		int currentIndexOfInput2 = 0;
		for (int i = 0; i < result.length; i++) {
			if (mergedInput(sortedInput1, currentIndexOfInput1)) {
				result[i] = sortedInput2[currentIndexOfInput2++];
			} else if (mergedInput(sortedInput2, currentIndexOfInput2)) {
				result[i] = sortedInput1[currentIndexOfInput1++];
			} else {
				result[i] = sortedInput1[currentIndexOfInput1] <= sortedInput2[currentIndexOfInput2] ? sortedInput1[currentIndexOfInput1++]
						: sortedInput2[currentIndexOfInput2++];
			}
		}
		return result;
	}

	private boolean mergedInput(int[] inputArray, int currentIndex) {
		return currentIndex >= inputArray.length;
	}
}
