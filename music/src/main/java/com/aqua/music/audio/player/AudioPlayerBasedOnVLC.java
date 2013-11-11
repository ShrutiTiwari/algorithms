package com.aqua.music.audio.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.aqua.music.items.PlayableItem;
import com.aqua.music.model.Frequency;

public class AudioPlayerBasedOnVLC implements AudioPlayer {
	private static final String HOME_VLC_EXE_LOCATION_WINDOWS = "C:/Program Files/VideoLAN/VLC/vlc.exe";
	private static final String OFFICE_VLC_EXE_LOCATION_WINDOWS = "C:/software/VideoLAN/VLC/vlc.exe";
	private static final String VLC_EXE_LOCATION_LINUX = "/usr/bin/vlc-wrapper";
	private static final String os = System.getProperty("os.name");
	private static final String vlcOption = "--play-and-exit";

	private final String vlcExeLoc;
	private AudioLifeCycleManager audioPlayCoordinator;
	private final ProcessHandler processHandler = new ProcessHandler();

	AudioPlayerBasedOnVLC() {
		this.vlcExeLoc = (!os.contains("Windows")) ? VLC_EXE_LOCATION_LINUX : findWindowsLocation();
	}

	public void play(PlayableItem playableItem) {
		audioPlayCoordinator.play(playableItem.frequencyList());
	}

	@Override
	public Runnable playTask(final Collection<Frequency> frequencyList) {
		Collection<File> playlist = new AudioFilesList(frequencyList).allAudioFiles();
		final File[] audioFilesArray = playlist.toArray(new File[playlist.size()]);

		return new Runnable() {
			@Override
			public void run() {
				play(audioFilesArray);
			}
		};
	}

	public void setCoordinator(AudioLifeCycleManager audioPlayCoordinator2) {
		this.audioPlayCoordinator = audioPlayCoordinator2;
	}

	public void stop() {
		processHandler.stopVlcIfRunning();
	}

	private String findWindowsLocation() {
		return new File(HOME_VLC_EXE_LOCATION_WINDOWS).exists() ? HOME_VLC_EXE_LOCATION_WINDOWS : OFFICE_VLC_EXE_LOCATION_WINDOWS;
	}

	private void play(File... audioFiles) {
		try {
			audioPlayCoordinator.acquireRightToPlay();
			processHandler.startVlcWith(audioFiles);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			audioPlayCoordinator.releaseRightToPlay();
		}
	}

	public static class AudioFilesList {
		Collection<File> allAudioFiles = new ArrayList<File>();
		StringBuffer prettyPrintText = new StringBuffer();
		Map<String, File> audioLib = AudioLibrary.library();

		public AudioFilesList(Collection<Frequency> allNotes) {
			for (Frequency each : allNotes) {
				addIfFileFound(each);
			}
		}

		public void addIfFileFound(Frequency singleNote) {
			addIfFileFound(singleNote, true);
		}

		public void addIfFileFound(Frequency singleNote, boolean appendComma) {
			String code = singleNote.fileCode();
			File audioFile = audioLib.get(code);
			if (audioFile == null) {
				System.out.println("No audio found for [" + singleNote + "] in the list of files[" + audioLib.keySet() + "]");
			} else {
				allAudioFiles.add(audioFile);
				prettyPrintText.append((appendComma ? ", " : "") + code);
			}
		}

		public void addIfFileFound(Frequency[] notes) {
			for (Frequency each : notes) {
				addIfFileFound(each);
			}
		}

		public void addText(String value) {
			prettyPrintText.append(value);
		}

		public Collection<File> allAudioFiles() {
			return allAudioFiles;
		}
	}

	class ProcessHandler {
		private final Runtime runtime = Runtime.getRuntime();
		// mutable variable
		private volatile Process lastRunningVlcProcess = null;

		public void startVlcWith(File[] audioFiles) {
			String[] command = buildCommand(audioFiles);
			try {
				lastRunningVlcProcess = runtime.exec(command);
				int success = lastRunningVlcProcess.waitFor();
				if (success != 0) {
					printError(lastRunningVlcProcess);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lastRunningVlcProcess.destroy();
				lastRunningVlcProcess = null;
			}
		}

		public void stopVlcIfRunning() {
			if (lastRunningVlcProcess == null)
				return;

			try {
				try {
					lastRunningVlcProcess.getOutputStream().close();
				} catch (Exception ignored) {
					// nop
				}
				try {
					lastRunningVlcProcess.destroy();
				} catch (Exception e) {
				}
			} finally {
				lastRunningVlcProcess = null;
			}
		}

		private void printError(Process p) throws IOException {
			System.out.println("process execution failed " + p.exitValue());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String s = null;
			while ((s = bufferedReader.readLine()) != null) {
				System.out.println(s);
			}
		}

		private String[] buildCommand(File... audioFiles) {
			String[] command = new String[2 + audioFiles.length];
			command[0] = vlcExeLoc;
			command[1] = vlcOption;
			int i = 0;
			for (File each : audioFiles) {
				command[i++ + 2] = each.getAbsolutePath();
			}
			return command;
		}
	}
}