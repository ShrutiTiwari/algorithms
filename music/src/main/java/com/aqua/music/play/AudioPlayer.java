package com.aqua.music.play;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import com.aqua.music.items.PlayableItem;

public interface AudioPlayer {
	static final String HOME_VLC_EXE_LOCATION_WINDOWS = "C:/Program Files/VideoLAN/VLC/vlc.exe";
	static final String OFFICE_VLC_EXE_LOCATION_WINDOWS = "C:/software/VideoLAN/VLC/vlc.exe";
	static final String VLC_EXE_LOCATION_LINUX = "/usr/bin/vlc-wrapper";

	void playList(Collection<File> audioFiles);
	
	void play(PlayableItem item);
	
	public class VLCPlayer implements AudioPlayer {
		private static final String vlcOption = "--play-and-exit";
		private final String vlcExeLoc;

		VLCPlayer() {
			this.vlcExeLoc = findAudioPlayerBasedOnOS();
		}

		public void playList(Collection<File> audioFiles) {
			play(audioFiles.toArray(new File[audioFiles.size()]));
		}

		private void play(File... audioFiles) {
			String[] command = new String[2 + audioFiles.length];
			command[0] = vlcExeLoc;
			command[1] = vlcOption;
			int i = 0;
			for (File each : audioFiles) {
				command[i++ + 2] = each.getAbsolutePath();
			}
			new CommandExecutor().executeCommand(command);
		}
		
		private String findAudioPlayerBasedOnOS() {
			String os = System.getProperty("os.name");
			return (!os.contains("Windows")) ? VLC_EXE_LOCATION_LINUX
					: findWindowsLocation();
		}

		private String findWindowsLocation() {
			return new File(HOME_VLC_EXE_LOCATION_WINDOWS).exists()?HOME_VLC_EXE_LOCATION_WINDOWS:OFFICE_VLC_EXE_LOCATION_WINDOWS;
		}

		@Override
		public void play(PlayableItem item) {
			playList(item.getPlayList());
		}
	}

	class CommandExecutor {
		private final Runtime runtime = Runtime.getRuntime();

		public void executeCommand(String[] command) {
			try {
				for (String each : command) {
					//System.out.println(each);
				}

				Process p = runtime.exec(command);
				int success = p.waitFor();
				if (success != 0) {
					printError(p);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private void printError(Process p) throws IOException {
			System.out.println("process execution failed " + p.exitValue());
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));
			String s = null;
			while ((s = bufferedReader.readLine()) != null) {
				System.out.println(s);
			}
		}
	}
	
}
