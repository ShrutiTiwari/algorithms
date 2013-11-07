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

public class AudioPlayerBasedOnVLC implements AudioPlayer
{
    static final String HOME_VLC_EXE_LOCATION_WINDOWS = "C:/Program Files/VideoLAN/VLC/vlc.exe";
    static final String OFFICE_VLC_EXE_LOCATION_WINDOWS = "C:/software/VideoLAN/VLC/vlc.exe";
    static final String VLC_EXE_LOCATION_LINUX = "/usr/bin/vlc-wrapper";

    static Process lastRunningProcess = null;
    private final static String os = System.getProperty( "os.name" );
    private static final String vlcOption = "--play-and-exit";

    private final boolean blockingPlay;

    private final String vlcExeLoc;
	private AudioPlayCoordinator audioPlayCoordinator;

    AudioPlayerBasedOnVLC( boolean blockingPlay ) {
        this.vlcExeLoc = (!os.contains( "Windows" )) ? VLC_EXE_LOCATION_LINUX : findWindowsLocation();
        this.blockingPlay = blockingPlay;
    }

    public void play( PlayableItem playableItem ) {
        Collection<File> playlist = new AudioFilesList( playableItem.frequencyList() ).allAudioFiles();
        playList( playlist );
    }

    public void playWithoutBlocking( File... audioFiles ) {
        if( lastRunningProcess != null ) {
            destroy();
        }
        String[] command = new String[2 + audioFiles.length];
        command[0] = vlcExeLoc;
        command[1] = vlcOption;
        int i = 0;
        for( File each : audioFiles ) {
            command[i++ + 2] = each.getAbsolutePath();
        }

        try {
            lastRunningProcess = Runtime.getRuntime().exec( command );
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    private void destroy() {
        if( lastRunningProcess == null )
            return;
        try {
            lastRunningProcess.getOutputStream().close();
        } catch( Exception ignored ) {
            // nop
        }
        try {
            lastRunningProcess.destroy();
        } catch( Exception e ) {} finally {}
        lastRunningProcess = null;
    }

    private String findWindowsLocation() {
        return new File( HOME_VLC_EXE_LOCATION_WINDOWS ).exists() ? HOME_VLC_EXE_LOCATION_WINDOWS : OFFICE_VLC_EXE_LOCATION_WINDOWS;
    }

    private void play( File... audioFiles ) {
        String[] command = new String[2 + audioFiles.length];
        command[0] = vlcExeLoc;
        command[1] = vlcOption;
        int i = 0;
        for( File each : audioFiles ) {
            command[i++ + 2] = each.getAbsolutePath();
        }
        new CommandExecutor().executeCommand( command );
    }

    @Override
    public void playList( Collection<File> playlist ) {
        File[] audioFilesArray = playlist.toArray( new File[playlist.size()] );
        if( blockingPlay ) {
            play( audioFilesArray );
        } else {
            playWithoutBlocking( audioFilesArray );
        }

    }
    public void setCoordinator(AudioPlayCoordinator audioPlayCoordinator2) {
		this.audioPlayCoordinator=audioPlayCoordinator2;
	}
    @Override
    public void play( Collection<Frequency> collectedFrequencies ) {

    }

    class CommandExecutor
    {
        private final Runtime runtime = Runtime.getRuntime();

        public Process executeCommand( String[] command ) {
            try {
                Process nativeProcess = runtime.exec( command );
                int success = nativeProcess.waitFor();
                if( success != 0 ) {
                    printError( nativeProcess );
                }
                return nativeProcess;
            } catch( Exception e ) {
                e.printStackTrace();
            }
            return null;
        }

        private void printError( Process p ) throws IOException {
            System.out.println( "process execution failed " + p.exitValue() );
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( p.getErrorStream() ) );
            String s = null;
            while( (s = bufferedReader.readLine()) != null ) {
                System.out.println( s );
            }
        }
    }

    public static class AudioFilesList
    {
        Collection<File> allAudioFiles = new ArrayList<File>();
        StringBuffer prettyPrintText = new StringBuffer();
        Map<String, File> audioLib = AudioLibrary.library();

        public AudioFilesList( Collection<Frequency> allNotes ) {
            for( Frequency each : allNotes ) {
                addIfFileFound( each );
            }
        }

        public void addIfFileFound( Frequency singleNote ) {
            addIfFileFound( singleNote, true );
        }

        public void addIfFileFound( Frequency singleNote, boolean appendComma ) {
            String code = singleNote.fileCode();
            File audioFile = audioLib.get( code );
            if( audioFile == null ) {
                System.out.println( "No audio found for [" + singleNote + "] in the list of files[" + audioLib.keySet() + "]" );
            } else {
                allAudioFiles.add( audioFile );
                prettyPrintText.append( (appendComma ? ", " : "") + code );
            }
        }

        public void addIfFileFound( Frequency[] notes ) {
            for( Frequency each : notes ) {
                addIfFileFound( each );
            }
        }

        public void addText( String value ) {
            prettyPrintText.append( value );
        }

        public Collection<File> allAudioFiles() {
            return allAudioFiles;
        }
    }
    

}