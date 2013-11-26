package com.aqua.music.bo.audio.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.aqua.music.bo.audio.manager.AudioPlayRightsManager;
import com.aqua.music.model.core.DynamicFrequency;
import com.aqua.music.model.core.Frequency;

class AudioPlayerImplWithStaticSoundBasedOnVLC implements AudioPlayer {
	private static final String HOME_VLC_EXE_LOCATION_WINDOWS = "C:/Program Files/VideoLAN/VLC/vlc.exe";
	private static final String OFFICE_VLC_EXE_LOCATION_WINDOWS = "C:/software/VideoLAN/VLC/vlc.exe";
	private static final String os = System.getProperty("os.name");
	private static final String VLC_EXE_LOCATION_LINUX = "/usr/bin/vlc-wrapper";
	private static final String vlcOption = "--play-and-exit";

	private AudioPlayRightsManager audioPlayRightsManager;
	private final ProcessHandler processHandler = new ProcessHandler();
	private final String vlcExeLoc;

	AudioPlayerImplWithStaticSoundBasedOnVLC() {
		this.vlcExeLoc = (!os.contains("Windows")) ? VLC_EXE_LOCATION_LINUX : findWindowsLocation();
	}

	@Override
	public Runnable playTask(final Collection<? extends DynamicFrequency> frequencyList) {
		Collection<File> playlist = new AudioFilesList(frequencyList).allAudioFiles();
		final File[] audioFilesArray = playlist.toArray(new File[playlist.size()]);

		return new Runnable() {
			@Override
			public void run() {
				play(audioFilesArray);
			}
		};
	}

	@Override
	public Runnable playTaskInLoop(Collection<? extends DynamicFrequency> frequencyList) {
		Collection<File> playlist = new AudioFilesList(frequencyList).allAudioFiles();
		final File[] audioFilesArray = playlist.toArray(new File[playlist.size()]);

		return new Runnable() {
			@Override
			public void run() {
				play(audioFilesArray);
			}
		};
	}

	public void setAudioPlayRigthsManager(AudioPlayRightsManager audioPlayRightsManager) {
		this.audioPlayRightsManager = audioPlayRightsManager;
	}

	public void stop() {
		processHandler.stopVlcIfRunning();
	}

	private String findWindowsLocation() {
		return new File(HOME_VLC_EXE_LOCATION_WINDOWS).exists() ? HOME_VLC_EXE_LOCATION_WINDOWS : OFFICE_VLC_EXE_LOCATION_WINDOWS;
	}

	private void play(File... audioFiles) {
		try {
			audioPlayRightsManager.acquireRightToPlay();
			processHandler.startVlcWith(audioFiles);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			audioPlayRightsManager.releaseRightToPlay();
		}
	}

	public static class AudioFilesList {
		Collection<File> allAudioFiles = new ArrayList<File>();
		Map<String, File> audioLib = StaticAudioLibrary.library();
		StringBuffer prettyPrintText = new StringBuffer();

		public AudioFilesList(Collection<? extends DynamicFrequency> allNotes) {
			for (DynamicFrequency each : allNotes) {
				addIfFileFound(each);
			}
		}

		public void addIfFileFound(DynamicFrequency singleNote) {
			addIfFileFound(singleNote, true);
		}

		public void addIfFileFound(DynamicFrequency singleNote, boolean appendComma) {
			String code = singleNote.fileCode();
			File audioFile = audioLib.get(code);
			if (audioFile == null) {
				logger.info("No audio found for [" + singleNote + "] in the list of files[" + audioLib.keySet() + "]");
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
		// mutable variable
		private volatile Process lastRunningVlcProcess = null;
		private final Runtime runtime = Runtime.getRuntime();

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

		private void printError(Process p) throws IOException {
			logger.info("process execution failed " + p.exitValue());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String s = null;
			while ((s = bufferedReader.readLine()) != null) {
				logger.info(s);
			}
		}
	}

	@Override
	public void decreaseTempo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void increaseTempo() {
		// TODO Auto-generated method stub
		
	}
}