package com.aqua.music.action.listeners;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.bo.audio.manager.AudioTask;
import com.aqua.music.model.song.Song;

public class PlayAllSongsActionListener implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(PlayAllSongsActionListener.class);
	private final TextArea textArea;
	private final Song[] songs;

	public PlayAllSongsActionListener(final TextArea textArea, final Song[] songs) {
		this.textArea = textArea;
		this.songs = songs;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		AudioTask<Song> audioTask = new AudioTask<Song>() {
			@Override
			public Song[] forLoopParameter() {
				return songs;
			}

			@Override
			public void forLoopBody(final Song song) {
				String text = song.name() + "===>\n" + song.asText();
				String displayText = "\n\n Playing::" + text;
				logger.info(displayText);
				textArea.append(displayText);
				song.play(AudioPlayConfig.SYNCHRONOUS_DYNAMIC_PLAYER);
			}

			@Override
			public void beforeForLoop() {
				textArea.setText("Playing all items:\n");
				logger.info(""+songs.length);
			}
		};
		AudioLifeCycleManager.instance.execute(audioTask);
	}
}
