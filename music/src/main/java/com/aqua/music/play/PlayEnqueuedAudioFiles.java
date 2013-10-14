package com.aqua.music.play;

import static com.aqua.music.play.AudioLibrary.addFileIfFound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.FundamentalFrequency;

public class PlayEnqueuedAudioFiles {
	public static void play(List<FundamentalFrequency> notes) {
		List<File> audioFiles = new ArrayList<File>();
		StringBuffer printPlaylist = new StringBuffer();
		for (FundamentalFrequency each : notes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		System.out.println("playing	[" + printPlaylist.toString() + "]");
		AudioLibrary.audioPlayer().playList( audioFiles);
	}

	public static void play(FundamentalFrequency start, FundamentalFrequency[] middleNotes, FundamentalFrequency end) {
		StringBuffer printPlaylist = new StringBuffer();
		List<File> audioFiles = new ArrayList<File>();
		addFileIfFound(audioFiles, start);
		printPlaylist.append(start);
		for (FundamentalFrequency each : middleNotes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		addFileIfFound(audioFiles, end);
		printPlaylist.append(", " + end);
		System.out.print("playing	[" + printPlaylist.toString() + "]");
		AudioLibrary.audioPlayer().playList( audioFiles);
	}

	public static void playArray(FundamentalFrequency[] ascNotes, FundamentalFrequency[] descNotes) {
		StringBuffer printPlaylist = new StringBuffer();
		List<File> audioFiles = new ArrayList<File>();
		for (FundamentalFrequency each : ascNotes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		for (FundamentalFrequency each : descNotes) {
			AudioLibrary.addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		System.out.print("playing	[" + printPlaylist.toString() + "]");
		AudioLibrary.audioPlayer().playList( audioFiles);
	}
}
