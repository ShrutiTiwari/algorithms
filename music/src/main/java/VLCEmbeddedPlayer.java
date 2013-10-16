import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.aqua.music.play.AudioLibrary;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class VLCEmbeddedPlayer
{
    private static final String AUDIO_LIBRARY = "recognition-puzzles/";
    private static final String FOLDER_PREFIX = "note-recognition-";
    
	public static void main( String[] args ) {
		NativeLibrary.addSearchPath( RuntimeUtil.getLibVlcLibraryName(), "C:\\software\\VideoLAN\\VLC\\" );
		Native.loadLibrary( RuntimeUtil.getLibVlcLibraryName(), LibVlc.class );

		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				attachEmbededMediaPlayerInFrameAndPlay( );
			}
		} );
	}

	private static void attachEmbededMediaPlayerInFrameAndPlay( ) {
		JFrame frame = new JFrame( "vlcj Tutorial" );

		EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

		frame.setContentPane( mediaPlayerComponent );

		frame.setLocation( 200, 200 );
		frame.setSize( 200, 200 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );

		EmbeddedMediaPlayer mediaPlayer = mediaPlayerComponent.getMediaPlayer();
		mediaPlayer.attachVideoSurface();
		
		String dir = AUDIO_LIBRARY + directoryName( 1 ) + "/";
		String path = Thread.currentThread().getContextClassLoader().getResource( dir ).getPath();
		
		mediaPlayer.playMedia( AudioLibrary.library().get( "Dha" ).getAbsolutePath() );
	}
	
	private static String directoryName( int duration ) {
        return FOLDER_PREFIX + duration + "s";
    }

}
