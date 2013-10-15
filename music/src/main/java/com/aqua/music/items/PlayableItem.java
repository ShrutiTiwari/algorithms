package com.aqua.music.items;

import static com.aqua.music.model.Frequency.ClassicalNote.HIGH_SA;
import static com.aqua.music.model.Frequency.ClassicalNote.SA;

import java.io.File;
import java.util.Collection;

import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioLibrary;
import com.aqua.music.play.AudioPlayer;

public interface PlayableItem
{
	public PlayableItem andPattern( PatternApplicator patternApplicator );

	public void playWithoutBlocking();

	public void play();

	PlayableItemFactory factory = new PlayableItemFactory();

	static class PlayableItemFactory
	{
		public static PlayableItem forSet( FrequencySet frequencySet ) {
			if( frequencySet instanceof SymmetricalSet ) {
				return new SymmetricalPlayableItem( frequencySet );
			}
			return new AsymmetricalPlayableItem( frequencySet );
		}
	}

	public class SymmetricalPlayableItem implements PlayableItem
	{
		/**
		 * caution: this variable shouldn't be used until initialised, properly.
		 */
		private Collection<File> allAudioFiles = null;

		private int duration = 1;
		private PatternApplicator patternApplicator = PatternApplicator.NONE;
		private final FrequencySet frequencySet;

		private SymmetricalPlayableItem( FrequencySet frequencySet ) {
			this.frequencySet = frequencySet;
		}

		public SymmetricalPlayableItem andPattern( PatternApplicator patternApplicator ) {
			this.patternApplicator = patternApplicator;
			intializePlayList();
			return this;
		}

		public void playWithoutBlocking() {
			AudioPlayer.NON_BLOCKING_VLC_PLAYER.playList( this.playList() );
		}

		public void play() {
			System.out.println( patternApplicator.prettyPrintTextForAscDesc() );
			AudioPlayer.BLOCKING_VLC_PLAYER.playList( this.playList() );
		}

		SymmetricalPlayableItem forDuration( int duration ) {
			this.duration = duration;
			return this;
		}

		private void intializePlayList() {
			AudioLibrary.initializeWithGivenSeconds( duration );
			if( patternApplicator == PatternApplicator.NONE ) {
				plainAscendDescend();
			} else {
				patternedAscendDescend();
			}
		}

		private void patternedAscendDescend() {
			Frequency[] middleNotes = frequencySet.ascendNotes();
			Frequency[] input = new Frequency[middleNotes.length + 2];
			input[0] = Frequency.ClassicalNote.SA;
			input[input.length - 1] = Frequency.ClassicalNote.HIGH_SA;
			int i = 1;
			for( Frequency each : middleNotes ) {
				input[i++] = each;
			}

			patternApplicator.initializeWith( input );
			AudioFileListBuilder audioFileListMaker = new AudioFileListBuilder.SimpleListBuilder(
					patternApplicator.allNotes() );
			this.allAudioFiles = audioFileListMaker.allAudioFiles();
		}

		private void plainAscendDescend() {
			AudioFileListBuilder audioFileListBuilder = new AudioFileListBuilder.BuilderForSymmetricalSet( frequencySet );
			System.out.print( "\t[ Plain ascend-descend:: " + audioFileListBuilder.prettyPrintText() + "]" );
			this.allAudioFiles = audioFileListBuilder.allAudioFiles();
		}

		private Collection<File> playList() {
			if( allAudioFiles == null ) {
				intializePlayList();
			}
			return allAudioFiles;
		}
	}

	public class AsymmetricalPlayableItem implements PlayableItem
	{
		private final FrequencySet frequencySet;
		private Collection<File> allAudioFiles = null;

		private AsymmetricalPlayableItem( FrequencySet frequencySet ) {
			this.frequencySet = frequencySet;
		}

		@Override
		public void playWithoutBlocking() {
		}

		@Override
		public void play() {
			createAudioList( SA, frequencySet.ascendNotes(), HIGH_SA );
			createAudioList( HIGH_SA, frequencySet.descendNotes(), SA );
			AudioPlayer.BLOCKING_VLC_PLAYER.playList( allAudioFiles );
		}

		void createAudioList( Frequency start, Frequency[] middleNotes, Frequency end ) {
			AudioFileListBuilder.WithMiddleNotesAndStartEndNotes audioFileListBuilder = new AudioFileListBuilder.WithMiddleNotesAndStartEndNotes(
					middleNotes, start, end );
			allAudioFiles.addAll( audioFileListBuilder.allAudioFiles() );
			audioFileListBuilder.allAudioFiles();
		}

		@Override
		public PlayableItem andPattern( PatternApplicator patternApplicator ) {
			return this;
		}
	}
}
