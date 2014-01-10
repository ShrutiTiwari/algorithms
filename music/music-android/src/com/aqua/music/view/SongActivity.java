package com.aqua.music.view;

import java.util.List;

import open.music.api.Playable;
import android.widget.ListAdapter;

public class SongActivity extends BaseSoundPlayActivity {
	private int activityId=R.layout.activity_song;

	@Override
	protected int getActivityId() {
		return activityId;
	}

	@Override
	protected ListAdapter getDataAdapter() {
		List<Playable> songs = (List<Playable>) playApi.getAllSongs();
		final PlyablesArrayAdapter adapter = new PlyablesArrayAdapter(this,
				R.layout.list_item, songs);
		return adapter;
	}
}