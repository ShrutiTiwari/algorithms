package com.aqua.music.view.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import open.music.api.PlayApi;
import open.music.api.Playable;
import open.music.api.SingletonFactory;
import open.music.api.StateDependentUi;
import open.music.api.StateDependentUi.StartEndPointChangeListener;

import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.view.components.UiDropdown.NoteFragementAndOctaveActionListener;
import com.aqua.music.view.components.UiDropdown.ThaatAndPatternDropdownActionListener;

class MusicPanelForPractice extends MusicPanel {
	private final List<Playable> intialItemsList;
	private final JTextField playAllCounter;
	private final PlayApi playApi = SingletonFactory.PLAY_API;
	private final MusicPracticePanelType practicePanel;
	private JPanel specificComponentPanel;
	private final StateDependentUi stateDependentUi;

	/**
	 * Used for plain rehearsing - of thaat and songs
	 * 
	 * @param titleLabel
	 *            TODO
	 */
	public MusicPanelForPractice(MusicPracticePanelType practicePanel, StateDependentUi stateDependentUi) {
		super(practicePanel);
		this.practicePanel = practicePanel;
		this.intialItemsList = practicePanel.itemsList();
		this.playAllCounter = defaultTextField();
		this.stateDependentUi = stateDependentUi;
		if (practicePanel == MusicPracticePanelType.SONG) {
			return;
		}
		addCustomizationControl();
	}

	/**
	 * Used for patterned rehearse of thaat.
	 * 
	 * @param frequencySet
	 * @param patternItemsCount
	 * @param practicePanel
	 *            TODO
	 */
	public MusicPanelForPractice(MusicPracticePanelType practicePanel, StateDependentUi stateDependentUi, FrequencySet frequencySet,
			PermuatationsGenerator patternItemsCount) {
		super(practicePanel);
		this.practicePanel = practicePanel;
		this.playAllCounter = defaultTextField();
		this.intialItemsList = playApi.getAllPatternedThaat(frequencySet, patternItemsCount);
		this.stateDependentUi = stateDependentUi;
		final ThaatAndPatternDropdownActionListener thaatPatternListener = new ThaatAndPatternDropdownActionListener(this, frequencySet,
				patternItemsCount);

		final JComboBox thaatDropdown = UiDropdown.thaatDropDown(frequencySet);
		thaatDropdown.addActionListener(thaatPatternListener);

		final JComboBox patternDropdown = UiDropdown.patternThaatDropDown();
		patternDropdown.addActionListener(thaatPatternListener);

		addExtraTopControl(thaatDropdown);
		addExtraTopControl(patternDropdown);
		addExtraTopControl(Box.createVerticalStrut(5));
		addCustomizationControl();
	}

	@Override
	public void repaint() {
		specificComponentPanel.repaint();
	}

	@Override
	protected JPanel createMiddlePanel(final Object selectedConfiguration) {
		this.specificComponentPanel = UiJPanelBuilder.BOX_VERTICAL.createPanel();
		specificComponentPanel.setOpaque(true);
		specificComponentPanel.add(Box.createVerticalStrut(50));

		JPanel labelPanel = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
		labelPanel.add(practicePanel.uiLables());
		labelPanel.setSize(new Dimension(10, 40));

		JPanel playAreaPanel = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();

		List<Playable> itemsList = (List<Playable>) selectedConfiguration;
		if (itemsList == null) {
			itemsList = this.intialItemsList;
		}

		final Playable[] allPlayableItems = itemsList.toArray(new Playable[itemsList.size()]);
		JList playItemsList = new JList(allPlayableItems);
		playItemsList.addListSelectionListener(new PlaySingleItemActionListener(practicePanel, stateDependentUi, playItemsList,
				allPlayableItems));
		playItemsList.setBackground(UiColor.ALT_BG_CLR);
		playAreaPanel.add(new UiScrollPane().createScrollPane(playItemsList));

		JButton playAllButton = UiButtons.MusicButtons.PLAYER_FOR_ALL.getButton();
		playAllButton.addActionListener(new PlayAllItemsActionListener(practicePanel, stateDependentUi, playAllCounter, allPlayableItems));

		specificComponentPanel.add(labelPanel);
		specificComponentPanel.add(playAreaPanel);
		specificComponentPanel.add(Box.createVerticalGlue());

		JPanel playAllPanel = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
		playAllPanel.add(new JLabel("Repeat count"));
		playAllPanel.add(playAllCounter);
		playAllPanel.add(playAllButton);
		specificComponentPanel.add(playAllPanel);
		return specificComponentPanel;
	}

	private void addCustomizationControl() {
		final NoteFragementAndOctaveActionListener noteFragmentOctaveListener = new NoteFragementAndOctaveActionListener(this,
				stateDependentUi);
		ClassicalNote[] displayClassicalNotes = generateDisplayList();
		for (JComboBox each : new JComboBox[] { UiDropdown.noteFragmentDropDown(), UiDropdown.octaveDropDown(),
				UiDropdown.startNoteDropDown(displayClassicalNotes), UiDropdown.endNoteDropDown(displayClassicalNotes) }) {
			each.addActionListener(noteFragmentOctaveListener);
			addExtraTopControl2(each);
		}
	}

	private JTextField defaultTextField() {
		JTextField defaultField = new JTextField(" 5 ");
		defaultField.setBorder(BorderFactory.createEmptyBorder());
		return defaultField;
	}

	private ClassicalNote[] generateDisplayList() {
		List<ClassicalNote> allNotes = new ArrayList<ClassicalNote>();
		for (ClassicalNote each : ClassicalNote.values()) {
			if (!each.name().contains("_")) {
				allNotes.add(each);
			}
		}
		ClassicalNote[] displayClassicalNotes = allNotes.toArray(new ClassicalNote[allNotes.size()]);
		return displayClassicalNotes;
	}

	class PlayAllItemsActionListener implements ActionListener, StartEndPointChangeListener {
		private Playable[] playableItems;
		private final JTextField playAllCounter;
		private final MusicPracticePanelType practicePanel;

		public PlayAllItemsActionListener(MusicPracticePanelType practicePanel, StateDependentUi stateDependentUi,
				JTextField playAllCounter, final Playable[] playableItems) {
			stateDependentUi.registerStartEndPointChangeListener(this);
			this.practicePanel = practicePanel;
			this.playableItems = playableItems;
			this.playAllCounter = playAllCounter;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int count = 5;
			try {
				int val = Integer.parseInt(playAllCounter.getText().trim());
				if (val >= 1) {
					count = val;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			playAllCounter.setText(" " + count + " ");
			playApi.playAllItemsWithInteractiveDisplayInTextArea(playableItems, count);
		}

		@Override
		public void changedEndPoints(Frequency[] startEndPoints) {
			Playable[] newPlayableItems=practicePanel.changed(startEndPoints);
			if (newPlayableItems!=null) {
				this.playableItems=newPlayableItems;
			}
		}
	}

	class PlaySingleItemActionListener implements ListSelectionListener, StartEndPointChangeListener {
		private Playable[] allPlayableItems;
		private final JList jlist;
		private final MusicPracticePanelType practicePanel;

		public PlaySingleItemActionListener(MusicPracticePanelType practicePanel, StateDependentUi stateDependentUi, final JList jlist,
				Playable[] singlePlayableItem) {
			stateDependentUi.registerStartEndPointChangeListener(this);
			this.practicePanel = practicePanel;
			this.jlist = jlist;
			this.allPlayableItems = singlePlayableItem;

		}

		@Override
		public void changedEndPoints(Frequency[] startEndPoints) {
			Playable[] newPlayableItems=practicePanel.changed(startEndPoints);
			if (newPlayableItems!=null) {
				this.allPlayableItems=newPlayableItems;
			}
		}
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {
				int selectedIndex = jlist.getSelectedIndex();
				if (selectedIndex != -1) {
					Playable playableitem = allPlayableItems[selectedIndex];
					playApi.playInLoop(playableitem);
				}
			}
		}
	}
}