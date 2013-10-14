package com.aqua.music.play;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public interface AudioPlayer
{
    static final String HOME_VLC_EXE_LOCATION_WINDOWS = "C:/Program Files/VideoLAN/VLC/vlc.exe";
    static final String OFFICE_VLC_EXE_LOCATION_WINDOWS = "C:/software/VideoLAN/VLC/vlc.exe";
    static final String VLC_EXE_LOCATION_LINUX = "/usr/bin/vlc-wrapper";

    void playList( Collection<File> audioFiles );

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
        
        class NomWaitingCommandExecutor
        {
            private final Runtime runtime = Runtime.getRuntime();

            public Process executeCommand( String[] command ) {
                try {
                    return runtime.exec( command );
                } catch( Exception e ) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }

    public class VLCPlayer implements AudioPlayer
    {
        static Process lastRunningProcess = null;
        private static final String vlcOption = "--play-and-exit";
        private final String vlcExeLoc;

        VLCPlayer() {
            this.vlcExeLoc = findAudioPlayerBasedOnOS();
        }

        public void playList( Collection<File> audioFiles ) {
            play( audioFiles.toArray( new File[audioFiles.size()] ) );
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
            lastRunningProcess=null;
        }

        private String findAudioPlayerBasedOnOS() {
            String os = System.getProperty( "os.name" );
            return (!os.contains( "Windows" )) ? VLC_EXE_LOCATION_LINUX : findWindowsLocation();
        }

        private String findWindowsLocation() {
            return new File( HOME_VLC_EXE_LOCATION_WINDOWS ).exists() ? HOME_VLC_EXE_LOCATION_WINDOWS
                    : OFFICE_VLC_EXE_LOCATION_WINDOWS;
        }

        private void play( File... audioFiles ) {
            if(lastRunningProcess!=null){
                destroy();
            }
            String[] command = new String[2 + audioFiles.length];
            command[0] = vlcExeLoc;
            command[1] = vlcOption;
            int i = 0;
            for( File each : audioFiles ) {
                command[i++ + 2] = each.getAbsolutePath();
            }
            lastRunningProcess = new CommandExecutor().executeCommand( command );
        }

        @Override
        public void nonblockingPlay(Collection<File> allAudioFiles  ) {
            
        }
    }

    void nonblockingPlay( Collection<File> allAudioFiles );

}
