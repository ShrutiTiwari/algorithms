/**
 * 
 */
package com.aqua.music.view.components;

import java.util.Collection;

import javax.swing.JLabel;

import open.music.api.Playable;
import open.music.api.SingletonFactory;

import com.aqua.music.model.core.Frequency;
import com.aqua.music.view.components.UiTexts.UiLables;

public enum MusicPracticePanelType {
	PATTERN("Patterns", UiTexts.UiLables.PRACTICE_A_PATTERN, null, true) {
		@Override
		public Playable[] changed(Frequency[] startEndPoints) {
			return null;
		}
	},
	SONG("Songs", UiTexts.UiLables.PRACTICE_A_SONG, SingletonFactory.PLAY_API.getAllSongs(), false) {
		@Override
		public Playable[] changed(Frequency[] startEndPoints) {
			return null;
		}
	},
	THAAT("Thaats", UiTexts.UiLables.PRACTICE_A_THAAT, SingletonFactory.PLAY_API.getAllPlainThaat(), true) {
		@Override
		public Playable[] changed(Frequency[] startEndPoints) {
			Collection<Playable> itemsList = SingletonFactory.PLAY_API.getCustomizedThaat(startEndPoints);
			return itemsList.toArray(new Playable[itemsList.size()]);
		}
	};

	private final String title;
	private final UiLables uiLables;
	private final Collection<Playable> itemsList;
	private final boolean customizationAllowed;

	private MusicPracticePanelType(String title, UiLables uiLables, Collection<Playable> itemsList, boolean customizationAllowed) {
		this.title = title;
		this.uiLables = uiLables;
		this.itemsList = itemsList;
		this.customizationAllowed = customizationAllowed;
	}

	public Collection<Playable> itemsList() {
		return itemsList;
	}

	public String title() {
		return title;
	}

	public JLabel uiLables() {
		return uiLables.getLabel();
	}

	public boolean customizationAllowed() {
		return customizationAllowed;
	}

	/**
	 * @param startEndPoints
	 * @return
	 */
	public abstract Playable[] changed(Frequency[] startEndPoints);
}
