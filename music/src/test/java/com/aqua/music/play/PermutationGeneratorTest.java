package com.aqua.music.play;

import java.util.List;

import org.junit.Test;

import com.aqua.music.play.SequencePlayer.AllThaat;

public class PermutationGeneratorTest {

	private static String[] input = new String[] { "Sa", "Re", "Ga", "Ma",
			"Pa", "Dha", "Ni", "Hsa" };
	
	private static int[] pattern = new int[] { 1, 2, 3 };

	//@Test
	public void testSequenceGeneration() {
		new PermutationGenerator<String>(pattern, input).generateAscendAndDescendSequences();;
	}
	
	@Test
	public void testSequenceGenerationForThaat() {
		Playable[] middleNotes = AllThaat.KAFI.ascendNotes();
		Playable[] input= new Playable[middleNotes.length+2];
		input[0]=Playable.BaseNotes.SA;
		input[input.length-1]=Playable.BaseNotes.HIGH_SA;
		int i=1;
		for(Playable each:middleNotes){
			input[i++]=each;
		}
		PermutationGenerator<Playable> permutationGenerator = new PermutationGenerator<Playable>(pattern, input);
        permutationGenerator.generateAscendAndDescendSequences();
        List<Playable> ascendSequence = permutationGenerator.ascendSequence();
        permutationGenerator.printPattern( ascendSequence );
        play( ascendSequence );
        List<Playable> descendSequence = permutationGenerator.descendSequence();
        permutationGenerator.printPattern( descendSequence );
        play( descendSequence );
	}

    private void play( List<Playable> notesSequence ) {
        AudioLibrary.audioPlayer().playList( new AudioFileAssembler.SimpleEnquer(notesSequence).collectedAudioFiles() );
    }
}
