package com.shruti.music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.shruti.music.AudioLibrary.VLCBasedPlayer;
import com.shruti.music.Playable.BaseNotes;

public class AudioLibraryVLCPlayerTest
{
    @Test
    public void playNoteUsingVlcPlayer() {
        AudioLibrary.initializeWithGivenSeconds(1);
        List<File> audioFiles = new ArrayList<File>();
        AudioLibrary.addFileIfFound( audioFiles, BaseNotes.DHA );
        VLCBasedPlayer.playList( audioFiles );
    }
}
