package com.aqua.music.ui.desktop;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.aqua.music.audio.player.AudioPlayer.AudioPlayerType;
import com.aqua.music.items.PlayableItem;
import com.aqua.music.items.SymmetricalPatternApplicator;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

public class GuiItemBuilder
{
	static final int HORIZONAL_COORIDNATE = 30;
	static final int BUTTON_WIDTH = 400;
	static final int BUTTON_HEIGHT = 30;
	static final Dimension preferredSizeForMainPane = new Dimension( 450, 450 );
	static final Dimension preferredSizeForThaatPanel = new Dimension( 400, 400 );

	private int verticalIndex = 10;

	JButton createWith( GuiItemType buttonType, Object[] arg ) {
		JButton displayItem = buttonType.createInstanceWith( arg );

		// set listener
		ActionListener actionListener = new GuiItemBuilder.ActionListenerBuilder( arg )
				.actionListener( buttonType );
		displayItem.addActionListener( actionListener );

		// set bounds
		displayItem.setBounds( HORIZONAL_COORIDNATE, verticalIndex(), buttonType.width(), BUTTON_HEIGHT );

		return displayItem;
	}

	private int verticalIndex() {
		verticalIndex += (BUTTON_HEIGHT) + 10;
		return verticalIndex;
	}

	static class ActionListenerBuilder
	{
		private final Object[] arg;

		static{
		    PlayableItem.factory.configureAudioPlayerType( AudioPlayerType.FREQUENCY_BASED ).andNonBlocking();
		}
		
		ActionListenerBuilder( Object[] arg ) {
			this.arg = arg;
			
		}

		ActionListener actionListener( GuiItemType displayItemType ) {
			switch( displayItemType ) {
			case PLAYABLE:
				return new ActionListener() {
					@Override
					public void actionPerformed( ActionEvent arg0 ) {
						FrequencySet freqSet = (FrequencySet) arg[0];
						System.out.println("Playing::" + freqSet.name());
						PlayableItem.factory.forSet( freqSet ).play();
					}
				};
			case QUIT:
				return new ActionListener() {
					@Override
					public void actionPerformed( ActionEvent arg0 ) {
						System.exit( 0 );
					}
				};
			case PLAYABLE_PATTERN:
				return new ActionListener() {
					@Override
					public void actionPerformed( ActionEvent arg0 ) {
						FrequencySet freqSet = (FrequencySet) arg[0];
						SymmetricalPatternApplicator<Frequency> pattern = new SymmetricalPatternApplicator<Frequency>(
								(int[]) arg[1] );
						System.out.println("Playing::" + freqSet.name());
						PlayableItem.factory.forSet( freqSet ).andPattern( pattern )
								.play();
					}
				};
			}
			return null;
		}
	}
}
