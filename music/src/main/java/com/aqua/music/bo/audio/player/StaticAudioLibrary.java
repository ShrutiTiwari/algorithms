package com.aqua.music.bo.audio.player;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.core.Frequency;

/**
 * @author "Shruti Tiwari"
 *
 */
class StaticAudioLibrary {
	private static final Logger logger = LoggerFactory.getLogger(StaticAudioLibrary.class);
	private static Map<String, File> library = Duration.ONE_SEC.library;

	private static final String AUDIO_LIBRARY = "recognition-puzzles/";
	private static final String FOLDER_PREFIX = "note-recognition-";

	public static void addFileIfFound(List<File> audioFiles, Frequency note) {
		File audioFile = library.get(note.fileCode());
		if (audioFile == null) {
			logger.info("No audio found for [" + note + "] in the list of files[" + library.keySet() + "]");
		} else {
			audioFiles.add(audioFile);
		}
	}

	public static Map<String, File> library() {
		return library;
	}

	public static void initializeWithGivenSeconds(int seconds) {
		library = Duration.libraryFor(seconds);
		logger.info("initializaed with [" + library + "]");
	}

	enum Duration {
		ONE_SEC(1),
		TWO_SEC(2);

		private final Map<String, File> library;

		Duration(int duration) {
			this.library = findAllNotesAudiosFor(duration);
		}

		public static Map<String, File> libraryFor(int duration) {
			switch (duration) {
			case 1:
				return ONE_SEC.library;
			default:
				return TWO_SEC.library;
			}
		}

		private static Map<String, File> findAllNotesAudiosFor(int duration) {
			String dir = AUDIO_LIBRARY + directoryName(duration) + "/";
			String path = Thread.currentThread().getContextClassLoader().getResource(dir).getPath();
			File sourceDirectory = new File(path);
			logger.info(sourceDirectory.getAbsolutePath());
			Map<String, File> allNoteAudios = new LinkedHashMap<String, File>();
			for (File audioFile : sourceDirectory.listFiles()) {
				String noteName = audioFile.getName().replace(".m4a", "");
				allNoteAudios.put(noteName, audioFile);
			}
			return allNoteAudios;
		}

		private static String directoryName(int duration) {
			return FOLDER_PREFIX + duration + "s";
		}
	}

}