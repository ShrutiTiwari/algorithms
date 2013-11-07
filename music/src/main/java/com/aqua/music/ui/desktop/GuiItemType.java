package com.aqua.music.ui.desktop;

import javax.swing.JButton;

import com.aqua.music.model.FrequencySet;

enum GuiItemType
{
	PLAYABLE("Play $$", "Click this to play $$", 200) {
		@Override
		public JButton createInstanceWith( Object[] arg ) {
			JButton dynamicButton = configurableNamedButton( this, ((FrequencySet) arg[0]).name() );
			return dynamicButton;
		}
	},
	QUIT("Quit", "Click this to quit!", 400) {
		@Override
		public JButton createInstanceWith( Object Object[] ) {
			return fixedNameButton(this);
		}
	},
	PLAY_ALL_TO_INFINITY("PLAY_TO_INFINITY", "Click this to play all!", 400) {
		@Override
		public JButton createInstanceWith( Object Object[] ) {
			return fixedNameButton(this);
		}
	},
	PLAYABLE_PATTERN("Play $$", "Click this to play $$", 200) {
		@Override
		public JButton createInstanceWith( Object[] arg ) {
			String freqSet =  ((FrequencySet)arg[0]).name();
			String pattern= displayText( (int[])arg[1]);
			return configurableNamedButton( this, freqSet + "  ==>  " + pattern );
		}
	};

	private final String text;
	private final String tooltip;
	private final int displayWidth;

	private GuiItemType( String text, String tooltip, int buttonWidth ) {
		this.text = text;
		this.tooltip = tooltip;
		this.displayWidth = buttonWidth;
	}

	private static JButton configurableNamedButton( GuiItemType itemType, String replaceName ) {
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
	
	private static JButton fixedNameButton( GuiItemType itemType) {
		JButton resultButton = new JButton( itemType.text) ;
		resultButton.setToolTipText( itemType.tooltip);
		return resultButton;
	}

	public abstract JButton createInstanceWith( Object[] arg );

	public int width() {
		return displayWidth;
	}
}
