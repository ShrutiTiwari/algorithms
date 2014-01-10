package com.aqua.music.view;

import android.content.Intent;
import android.view.View;
import android.widget.ListAdapter;

public class MainActivity extends BaseSoundPlayActivity {
	private final int activityId = R.layout.activity_main;

	public void playPattern(View view) {
		startActivity(new Intent(this, PatternActivity.class));
	}

	@Override
	protected int getActivityId() {
		return activityId;
	}

	@Override
	protected ListAdapter getDataAdapter() {
		return new PlyablesArrayAdapter(this, R.layout.list_item, allThaats);
	}
}