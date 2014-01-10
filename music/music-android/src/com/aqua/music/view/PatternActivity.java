package com.aqua.music.view;

import java.util.List;

import open.music.api.Playable;
import open.music.api.SingletonFactory;
import android.os.Bundle;
import android.widget.AbsListView;

public class PatternActivity extends SoundPlayActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pattern_play);
		intializeAudioSystem();
		populateList();
	}

	private void populateList() {
		final AbsListView listView = (AbsListView) findViewById(R.id.list);
		List<Playable> allPlainThaat = (List<Playable>) SingletonFactory.PLAY_API
				.getAllPlainThaat();

		final PlyablesArrayAdapter adapter = new PlyablesArrayAdapter(this,
				R.layout.list_item, allPlainThaat);
		listView.setAdapter(adapter);
		thaatClickListener = new PlyableClickListener(this);
		listView.setOnItemClickListener(thaatClickListener);
	}
}