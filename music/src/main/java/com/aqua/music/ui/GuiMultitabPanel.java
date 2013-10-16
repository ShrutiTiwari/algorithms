package com.aqua.music.ui;

import static com.aqua.music.ui.GuiItemBuilder.preferredSizeForThaatPanel;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.items.PatternGenerator;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public class GuiMultitabPanel extends JPanel
{
	public GuiMultitabPanel() {
		super( new GridLayout( 1, 1 ) );

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Rehearse plain items", plainReahearseTab() );
		tabbedPane.addTab( "Rehearse kafi with pattern", rehearseWithPatternsTab() );

		add( tabbedPane );
	}

	private Component plainReahearseTab() {
		GuiItemBuilder displayItemFactory = new GuiItemBuilder();

		JPanel playablePanel = new JPanel();
		playablePanel.setLayout( null );
		playablePanel.setPreferredSize( preferredSizeForThaatPanel );

		// add individual frequency-set buttons
		for( FrequencySet eachFrequencySet : SymmetricalSet.values() ) {
			playablePanel
					.add( displayItemFactory.createWith( GuiItemType.PLAYABLE, new Object[] { eachFrequencySet } ) );
		}

		// add quit button
		playablePanel.add( displayItemFactory.createWith( GuiItemType.QUIT, null ) );
		return playablePanel;
	}

	private Component rehearseWithPatternsTab() {
		GuiItemBuilder displayItemFactory = new GuiItemBuilder();

		FrequencySet frequencySet = SymmetricalSet.THAAT_KAFI;
		List<int[]> pairPaterrns = PatternGenerator.PAIR.generatePatterns( frequencySet.ascendNotes() );

		JPanel playablePanel = new JPanel();
		playablePanel.setLayout( null );
		playablePanel.setPreferredSize( preferredSizeForThaatPanel );

		// add individual pattern button for each set
		for( int[] eachPattern : pairPaterrns ) {
			playablePanel.add( displayItemFactory.createWith( GuiItemType.PLAYABLE_PATTERN, new Object[] {
					frequencySet, eachPattern } ) );
		}

		// add quit button
		playablePanel.add( displayItemFactory.createWith( GuiItemType.QUIT, null ) );
		return playablePanel;
	}
}
