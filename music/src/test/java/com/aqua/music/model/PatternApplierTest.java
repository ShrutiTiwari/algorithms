package com.aqua.music.model;

import java.util.List;

import org.junit.Test;

import com.aqua.music.model.SymmetricalPattern;
import com.aqua.music.model.FundamentalFrequency;
import com.aqua.music.model.PredefinedFrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioFileListMaker;
import com.aqua.music.play.AudioLibrary;

public class PatternApplierTest {

	private static String[] input = new String[] { "Sa", "Re", "Ga", "Ma",
			"Pa", "Dha", "Ni", "Hsa" };
	
	private static int[] pattern = new int[] { 1, 4, 3 };

	//@Test
	public void testSequenceGeneration() {
		new SymmetricalPattern<String>(pattern).generateAscendAndDescendSequences(input);
	}
	
	@Test
	public void testSequenceGenerationForThaat() {
		FundamentalFrequency[] middleNotes = SymmetricalSet.THAAT_KAFI.ascendNotes();
		FundamentalFrequency[] input= new FundamentalFrequency[middleNotes.length+2];
		input[0]=FundamentalFrequency.ClassicalNote.SA;
		input[input.length-1]=FundamentalFrequency.ClassicalNote.HIGH_SA;
		int i=1;
		for(FundamentalFrequency each:middleNotes){
			input[i++]=each;
		}
		SymmetricalPattern<FundamentalFrequency> permutationGenerator = new SymmetricalPattern<FundamentalFrequency>(pattern);
        permutationGenerator.generateAscendAndDescendSequences(input);
        List<FundamentalFrequency> ascendSequence = permutationGenerator.ascendSequence();
        permutationGenerator.printPattern( ascendSequence );
        play( ascendSequence );
        List<FundamentalFrequency> descendSequence = permutationGenerator.descendSequence();
        permutationGenerator.printPattern( descendSequence );
        play( descendSequence );
	}

    private void play( List<FundamentalFrequency> notesSequence ) {
        AudioLibrary.audioPlayer().playList( new AudioFileListMaker.SimpleListMaker(notesSequence).collectedAudioFiles() );
    }
}
