package com.aqua.music.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import open.music.api.PlayApi;
import open.music.api.Playable;
import open.music.api.SingletonFactory;
import open.music.api.StateDependentUi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.player.AndroidConfigBuilder;

public class MainActivity extends Activity {
	private PlayApi playApi = SingletonFactory.PLAY_API;
	private Playable currentPlayable = null;
	private ThaatClickListener thaatClickListener;

	public void decreaseTempo(View view) {
		stopAudio();
		AudioLifeCycleManager.instance.decreaseTempo();
		if (currentPlayable != null) {
			playApi.playInLoop(currentPlayable);
		}
	}

	public void increaseTempo(View view) {
		stopAudio();
		AudioLifeCycleManager.instance.increaseTempo();
		if (currentPlayable != null) {
			playApi.playInLoop(currentPlayable);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void stop(View view) {
		stopAudio();
		this.currentPlayable = null;
		this.thaatClickListener.unhighlightActiveView();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intializeAudioSystem();
		populateList();
	}

	@Override
	protected void onDestroy() {
		stopAudio();
		super.onDestroy();
	}

	private void intializeAudioSystem() {
		StateDependentUi stateDependentUi = StaticImpl.stateUi();
		playApi.initialize(stateDependentUi,
				AndroidConfigBuilder.AndroidConfig.DYNAMIC);
		AudioLifeCycleManager.instance.increaseTempo();
		AudioLifeCycleManager.instance.increaseTempo();
	}

	private void populateList() {
		final AbsListView listView = (AbsListView) findViewById(R.id.list);
		List<Playable> allPlainThaat = (List<Playable>) SingletonFactory.PLAY_API
				.getAllPlainThaat();

		final ThaatArrayAdapter adapter = new ThaatArrayAdapter(this,
				R.layout.list_item, allPlainThaat);
		listView.setAdapter(adapter);
		this.thaatClickListener = new ThaatClickListener(this);
		listView.setOnItemClickListener(thaatClickListener);
	}

	private void stopAudio() {
		AudioLifeCycleManager.instance.stop();
	}

	class ThaatArrayAdapter extends ArrayAdapter<Playable> {
		Map<Playable, Integer> mIdMap = new HashMap<Playable, Integer>();

		public ThaatArrayAdapter(Context context, int resource,
				List<Playable> objects) {
			super(context, resource, objects);

			for (int i = 0; i < objects.size(); i++) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			Playable item = super.getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
	}

	class ThaatClickListener implements AdapterView.OnItemClickListener {
		private View activeView;
		private Playable playable;
		private MainActivity mainActivity;

		public ThaatClickListener(MainActivity mainActivity) {
			this.mainActivity = mainActivity;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			this.playable = (Playable) parent.getItemAtPosition(position);
			unhighlightActiveView();
			view.setBackgroundColor(Color.LTGRAY);
			this.activeView = view;
			Log.i("MainActivity", "Clicked:" + playable.name());
			stopAudio();
			playApi.playInLoop(playable);
			mainActivity.currentPlayable = playable;
		}

		Playable activePlayable() {
			return playable;
		}

		void unhighlightActiveView() {
			if (activeView != null) {
				activeView.setBackgroundColor(Color.TRANSPARENT);
			}
		}
	}
}
