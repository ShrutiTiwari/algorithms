package com.aqua.music.audio.player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.aqua.music.audio.player.AudioPlayer.AudioPlayerType;
import com.aqua.music.model.Frequency;

public class AudioLibraryVLCPlayerTest
{
    @Test
    public void playNoteUsingVlcPlayer() {
        AudioLibrary.initializeWithGivenSeconds(1);
        List<File> audioFiles = new ArrayList<File>();
        AudioLibrary.addFileIfFound( audioFiles, Frequency.ClassicalNote.DHA );
        AudioPlayerType.VLC_BASED.blockingPlayer().playList( audioFiles);
    }
}
