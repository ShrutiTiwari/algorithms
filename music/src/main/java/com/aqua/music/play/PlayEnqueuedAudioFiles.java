package com.aqua.music.play;

import static com.aqua.music.play.AudioLibrary.addFileIfFound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.PredefinedFrequency;

public class PlayEnqueuedAudioFiles {
	public static void play(List<PredefinedFrequency> notes) {
		List<File> audioFiles = new ArrayList<File>();
		StringBuffer printPlaylist = new StringBuffer();
		for (PredefinedFrequency each : notes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		System.out.println("playing	[" + printPlaylist.toString() + "]");
		AudioLibrary.audioPlayer().playList( audioFiles);
	}

	public static void play(PredefinedFrequency start, PredefinedFrequency[] middleNotes, PredefinedFrequency end) {
		StringBuffer printPlaylist = new StringBuffer();
		List<File> audioFiles = new ArrayList<File>();
		addFileIfFound(audioFiles, start);
		printPlaylist.append(start);
		for (PredefinedFrequency each : middleNotes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		addFileIfFound(audioFiles, end);
		printPlaylist.append(", " + end);
		System.out.print("playing	[" + printPlaylist.toString() + "]");
		AudioLibrary.audioPlayer().playList( audioFiles);
	}

	public static void playArray(PredefinedFrequency[] ascNotes, PredefinedFrequency[] descNotes) {
		StringBuffer printPlaylist = new StringBuffer();
		List<File> audioFiles = new ArrayList<File>();
		for (PredefinedFrequency each : ascNotes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		for (PredefinedFrequency each : descNotes) {
			AudioLibrary.addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		System.out.print("playing	[" + printPlaylist.toString() + "]");
		AudioLibrary.audioPlayer().playList( audioFiles);
	}
}
