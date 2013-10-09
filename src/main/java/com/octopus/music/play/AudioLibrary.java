package com.octopus.music.play;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AudioLibrary {

	//static final String VLC_PLAYER = "C:/Program Files/VideoLAN/VLC/vlc.exe";
    static final String VLC_PLAYER = "C:/software/VideoLAN/VLC/vlc.exe";
	//private static final String AUDIO_LIBRARY = "C:\\recognition-puzzles\\";
	private static final String AUDIO_LIBRARY = "recognition-puzzles/";
	private static final String FOLDER_PREFIX = "note-recognition-";
	static Map<String, File> allNoteAudios;

	public static void initialize() {
		initializeWithGivenSeconds(2);
	}

	public static void initializeWithGivenSeconds(int seconds) {
		AudioLibrary.allNoteAudios = findAllNotesAudios(seconds);
	}

	static void addFileIfFound(List<File> audioFiles, Playable note) {
		File audioFile = allNoteAudios.get(note.code());
		if (audioFile == null) {
			System.out.println("No audio found for [" + note + "] in the list of files[" + allNoteAudios.keySet() + "]");
		} else {
			audioFiles.add(audioFile);

		}
	}

	private static String directoryName(int duration) {
		return FOLDER_PREFIX + duration + "s";
	}

	private static Map<String, File> findAllNotesAudios(int duration) {
		String dir =AUDIO_LIBRARY +directoryName(duration)+"/";
		String path = Thread.currentThread().getContextClassLoader().getResource(dir).getPath();
		File sourceDirectory=new File(path );
		System.out.println(sourceDirectory.getAbsolutePath());
		Map<String, File> allNoteAudios = new LinkedHashMap<String, File>();
		for (File audioFile : sourceDirectory.listFiles()) {
			String noteName = audioFile.getName().replace(".m4a", "");
			allNoteAudios.put(noteName, audioFile);
		}
		return allNoteAudios;
	}
	static class WindowsBasedVLCPlayer {
		private static final String vlcOption = "--play-and-exit";
		private final Runtime runtime = Runtime.getRuntime();
		private final String[] command;

		private WindowsBasedVLCPlayer(String[] command) {
			this.command=command;
		}

		static void play(File... audioFiles) {
			String[] command = new String[2 + audioFiles.length];
			command[0] = VLC_PLAYER;
			command[1] = vlcOption;
			int i = 0;
			for (File each : audioFiles) {
				command[i++ + 2] = each.getAbsolutePath();
			}
			new WindowsBasedVLCPlayer(command).play();
		}

		static void playList(List<File> audioFiles) {
			play(audioFiles.toArray(new File[audioFiles.size()]));
		}

		private void play() {
			try {
				Process p = runtime.exec(command);
				int success = p.waitFor();
				if (success != 0) {
					System.out.println("process execution failed ");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}