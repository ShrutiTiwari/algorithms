package com.aqua.music.items;

import static com.aqua.music.play.AudioLibrary.addFileIfFound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.Playable;
import com.aqua.music.play.AudioLibrary;

public class PlayEnqueuedAudioFiles {
	public static void play(List<Playable> notes) {
		List<File> audioFiles = new ArrayList<File>();
		StringBuffer printPlaylist = new StringBuffer();
		for (Playable each : notes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		System.out.println("playing	[" + printPlaylist.toString() + "]");
		AudioLibrary.audioPlayer().playList( audioFiles);
	}

	public static void play(Playable start, Playable[] middleNotes, Playable end) {
		StringBuffer printPlaylist = new StringBuffer();
		List<File> audioFiles = new ArrayList<File>();
		addFileIfFound(audioFiles, start);
		printPlaylist.append(start);
		for (Playable each : middleNotes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		addFileIfFound(audioFiles, end);
		printPlaylist.append(", " + end);
		System.out.print("playing	[" + printPlaylist.toString() + "]");
		AudioLibrary.audioPlayer().playList( audioFiles);
	}

	public static void playArray(Playable[] ascNotes, Playable[] descNotes) {
		StringBuffer printPlaylist = new StringBuffer();
		List<File> audioFiles = new ArrayList<File>();
		for (Playable each : ascNotes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		for (Playable each : descNotes) {
			AudioLibrary.addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		System.out.print("playing	[" + printPlaylist.toString() + "]");
		AudioLibrary.audioPlayer().playList( audioFiles);
	}
}
