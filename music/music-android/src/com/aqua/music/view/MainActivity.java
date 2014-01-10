package com.aqua.music.view;

import java.util.List;

import open.music.api.Playable;
import open.music.api.SingletonFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

public class MainActivity extends SoundPlayActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	public void playPattern(View view) {
		startActivity(new Intent(this, PatternActivity.class));
	}
}