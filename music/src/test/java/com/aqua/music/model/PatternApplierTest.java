package com.aqua.music.model;

import java.util.List;

import org.junit.Test;

import com.aqua.music.model.CommonAscDescPattern;
import com.aqua.music.model.PredefinedFrequency;
import com.aqua.music.model.PredefinedFrequencySet.Thaat;
import com.aqua.music.play.AudioFileListMaker;
import com.aqua.music.play.AudioLibrary;

public class PatternApplierTest {

	private static String[] input = new String[] { "Sa", "Re", "Ga", "Ma",
			"Pa", "Dha", "Ni", "Hsa" };
	
	private static int[] pattern = new int[] { 1, 4, 3 };

	//@Test
	public void testSequenceGeneration() {
		new CommonAscDescPattern<String>(pattern).generateAscendAndDescendSequences(input);
	}
	
	@Test
	public void testSequenceGenerationForThaat() {
		PredefinedFrequency[] middleNotes = Thaat.KAFI.ascendNotes();
		PredefinedFrequency[] input= new PredefinedFrequency[middleNotes.length+2];
		input[0]=PredefinedFrequency.FundamentalNote.SA;
		input[input.length-1]=PredefinedFrequency.FundamentalNote.HIGH_SA;
		int i=1;
		for(PredefinedFrequency each:middleNotes){
			input[i++]=each;
		}
		CommonAscDescPattern<PredefinedFrequency> permutationGenerator = new CommonAscDescPattern<PredefinedFrequency>(pattern);
        permutationGenerator.generateAscendAndDescendSequences(input);
        List<PredefinedFrequency> ascendSequence = permutationGenerator.ascendSequence();
        permutationGenerator.printPattern( ascendSequence );
        play( ascendSequence );
        List<PredefinedFrequency> descendSequence = permutationGenerator.descendSequence();
        permutationGenerator.printPattern( descendSequence );
        play( descendSequence );
	}

    private void play( List<PredefinedFrequency> notesSequence ) {
        AudioLibrary.audioPlayer().playList( new AudioFileListMaker.SimpleListMaker(notesSequence).collectedAudioFiles() );
    }
}
