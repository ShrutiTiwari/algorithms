package com.aqua.music.play;

import org.junit.Test;

import com.aqua.music.play.PermutationGenerator.AllThaat1;

public class PermutationGeneratorTest {

	private static String[] input = new String[] { "Sa", "Re", "Ga", "Ma",
			"Pa", "Dha", "Ni", "Hsa" };
	
	private static int[] pattern = new int[] { 1, 2, 3 };

	@Test
	public void testSequenceGeneration() {
		new PermutationGenerator<String>(pattern, input).playAscendAndDescend();;
	}
	
	@Test
	public void testSequenceGenerationForThaat() {
		Playable[] middleNotes = AllThaat1.KAFI.ascendNotes();
		Playable[] input= new Playable[middleNotes.length+2];
		input[0]=Playable.BaseNotes.SA;
		input[input.length-1]=Playable.BaseNotes.HIGH_SA;
		int i=1;
		for(Playable each:middleNotes){
			input[i++]=each;
		}
		new PermutationGenerator<Playable>(pattern, input).playAscendAndDescend();;
	}
}
