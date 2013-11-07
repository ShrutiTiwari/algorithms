package com.aqua.music.ui.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;

import com.aqua.music.audio.player.AudioPlayCoordinator;
import com.aqua.music.items.PlayableItem;
import com.aqua.music.items.SymmetricalPatternApplicator;
import com.aqua.music.items.SymmetricalPlayableItem;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

enum GuiButtons
{
	PLAYABLE("Play $$", "Click this to play $$", 200) {
		@Override
		public JButton createInstanceWith( Object[] arg ) {
			JButton dynamicButton = configurableNamedButton( this, ((FrequencySet) arg[0]).name() );
			return dynamicButton;
		}

		@Override
		public ActionListener actionListener(final Object[] arg) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrequencySet freqSet = (FrequencySet) arg[0];
					System.out.println("Playing::" + freqSet.name());
					PlayableItem.nonBlockingFrequencyPlayerConfig.forSet(freqSet).play();
				}
			};
		}
	},
	QUIT("Quit", "Click this to quit!", 400) {
		@Override
		public JButton createInstanceWith( Object Object[] ) {
			return fixedNameButton(this);
		}

		@Override
		public ActionListener actionListener(final Object[] arg) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			};
		}
	},
	PLAY_ALL_TO_INFINITY("PLAY_TO_INFINITY", "Click this to play all!", 400) {
		@Override
		public JButton createInstanceWith( Object Object[] ) {
			return fixedNameButton(this);
		}

		@Override
		public ActionListener actionListener(final Object[] arg) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					AudioPlayCoordinator audioPlayer = PlayableItem.nonBlockingFrequencyPlayerConfig.audioPlayer();
					
					Collection<Frequency> frequencies=new ArrayList<Frequency>();
					for (Object each : arg) {
						FrequencySet freqSet = (FrequencySet) each;
						frequencies.addAll((new SymmetricalPlayableItem(freqSet, audioPlayer)).frequencyList());
					}
					audioPlayer.play(frequencies);
				}
			};
		}
	},
	PLAYABLE_PATTERN("Play $$", "Click this to play $$", 200) {
		@Override
		public JButton createInstanceWith( Object[] arg ) {
			String freqSet =  ((FrequencySet)arg[0]).name();
			String pattern= displayText( (int[])arg[1]);
			return configurableNamedButton( this, freqSet + "  ==>  " + pattern );
		}

		@Override
		public ActionListener actionListener(final Object[] arg) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrequencySet freqSet = (FrequencySet) arg[0];
					SymmetricalPatternApplicator<Frequency> pattern = new SymmetricalPatternApplicator<Frequency>((int[]) arg[1]);
					System.out.println("Playing::" + freqSet.name());
					PlayableItem.nonBlockingFrequencyPlayerConfig.forSet(freqSet).andPattern(pattern).play();
				}
			};
		}
	};

	private final String text;
	private final String tooltip;
	private final int displayWidth;

	private GuiButtons( String text, String tooltip, int buttonWidth ) {
		this.text = text;
		this.tooltip = tooltip;
		this.displayWidth = buttonWidth;
	}

	private static JButton configurableNamedButton( GuiButtons itemType, String replaceName ) {
		JButton resultButton = new JButton( itemType.text.replace( "$$", replaceName ) );
		resultButton.setToolTipText( itemType.tooltip.replace( "$$", replaceName ) );
		return resultButton;
	}

	private static String displayText(int[] result) {
		String displayName = ""+result[0];
		int i = 0;
		for (int each : result) {
			if (i++ != 0) {
				displayName += ("-" + each);
			}
		}
		return displayName;
	}
	
	private static JButton fixedNameButton( GuiButtons itemType) {
		JButton resultButton = new JButton( itemType.text) ;
		resultButton.setToolTipText( itemType.tooltip);
		return resultButton;
	}

	public abstract JButton createInstanceWith( Object[] arg );
	
	public JButton createButton( Object[] arg ){
		JButton buttonItem=createInstanceWith(arg);
		buttonItem.addActionListener(actionListener(arg));
		buttonItem.setOpaque(true);
		return buttonItem;
	}

	public abstract ActionListener actionListener(final Object[] arg);
	
	public int width() {
		return displayWidth;
	}
}
