package com.aqua.music.play;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.aqua.music.play.AudioLibrary;
import com.aqua.music.play.Playable.BaseNotes;

public class AudioLibraryVLCPlayerTest
{
    @Test
    public void playNoteUsingVlcPlayer() {
        AudioLibrary.initializeWithGivenSeconds(1);
        List<File> audioFiles = new ArrayList<File>();
        AudioLibrary.addFileIfFound( audioFiles, BaseNotes.DHA );
        AudioLibrary.audioPlayer().playList( audioFiles);
    }
}
