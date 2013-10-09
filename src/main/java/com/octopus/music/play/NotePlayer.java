package com.octopus.music.play;

import static com.octopus.music.play.AudioLibrary.addFileIfFound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class NotePlayer {
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

	public static void play(Playable start, Playable[] arohiNotes, Playable end, Playable[] avrohiNotes) {
		StringBuffer printPlaylist = new StringBuffer();
		List<File> audioFiles = new ArrayList<File>();
		addFileIfFound(audioFiles, start);
		printPlaylist.append(start);
		for (Playable each : arohiNotes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}
		addFileIfFound(audioFiles, end);
		addFileIfFound(audioFiles, end);
		printPlaylist.append(", " + end + " |||  " + end);

		for (Playable each : avrohiNotes) {
			addFileIfFound(audioFiles, each);
			printPlaylist.append(", " + each);
		}

		addFileIfFound(audioFiles, start);
		printPlaylist.append(", " + start);

		System.out.print("\t[" + printPlaylist.toString() + "]");
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
