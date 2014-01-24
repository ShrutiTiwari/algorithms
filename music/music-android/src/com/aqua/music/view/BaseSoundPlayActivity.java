package com.aqua.music.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import open.music.api.PlayApi;
import open.music.api.Playable;
import open.music.api.SingletonFactory;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;

public abstract class BaseSoundPlayActivity extends Activity {
	protected PlayApi playApi = SingletonFactory.PLAY_API;
	private Playable currentPlayable = null;
	private PlyableClickListener plyableClickListener = null;
	protected List<Playable> allThaats = (List<Playable>) SingletonFactory.PLAY_API
			.getAllPlainThaat();
	protected AbsListView listView;
	private StaticImpl staticImpl;


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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.home:
			startActivity(new Intent(this, MainActivity.class));
			return true;
		case R.id.song:
			startActivity(new Intent(this, SongActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void setCurrentPlayable(Playable playable) {
		this.currentPlayable = playable;
	}

	public void stop(View view) {
		stopAudio();
		this.currentPlayable = null;
		this.plyableClickListener.unhighlightActiveView();
	}

	public void updateTempo(int tempoMultipler) {
		TextView tview = (TextView) findViewById(R.id.tempo);
		tview.setText("Tempo[" + tempoMultipler + "]");

	}

	protected abstract int getActivityId();

	protected abstract ListAdapter getDataAdapter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getActivityId());
		new StaticImpl().register(this);
		this.plyableClickListener = new PlyableClickListener(this);
		this.listView = (AbsListView) findViewById(R.id.list);
		listView.setOnItemClickListener(plyableClickListener);
		listView.setAdapter(getDataAdapter());
	}

	@Override
	protected void onDestroy() {
		stopAudio();
		super.onDestroy();
	}

	void refreshListView(ListAdapter dataAdapter) {
		listView.setAdapter(dataAdapter);
	}

	private void stopAudio() {
		AudioLifeCycleManager.instance.stop();
	}

	class PlyableClickListener implements AdapterView.OnItemClickListener {
		private View activeView;
		private Playable playable;
		private BaseSoundPlayActivity sourceActivity;

		public PlyableClickListener(BaseSoundPlayActivity sourceActivity) {
			this.sourceActivity = sourceActivity;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			this.playable = (Playable) parent.getItemAtPosition(position);
			unhighlightActiveView();
			view.setBackgroundColor(Color.LTGRAY);
			this.activeView = view;
			Log.i(this.getClass().getName(), "Clicked:" + playable.name());
			stopAudio();

			TextView tview = (TextView) findViewById(R.id.plyaingNow);
			tview.setText("Playing[" + playable.name() + "]");

			playApi.playInLoop(playable);
			sourceActivity.setCurrentPlayable(playable);
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

	class PlyablesArrayAdapter extends ArrayAdapter<Playable> {
		Map<Playable, Integer> mIdMap = new HashMap<Playable, Integer>();

		public PlyablesArrayAdapter(Context context, int resource,
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
}
