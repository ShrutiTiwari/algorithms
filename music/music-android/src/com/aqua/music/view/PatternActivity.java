package com.aqua.music.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import open.music.api.Playable;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.SymmetricalSet;

public class PatternActivity extends BaseSoundPlayActivity {
	private final int activityId = R.layout.pattern_play;
	private final SymmetricalSet defaultSelection = SymmetricalSet.THAAT_BILAWAL;

	@Override
	protected int getActivityId() {
		return activityId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		populateThaatDropDown();
	}

	@Override
	protected ListAdapter getDataAdapter() {
		return getDataAdapter(defaultSelection);
	}

	private ListAdapter getDataAdapter(FrequencySet selectedThaat) {
		List<Playable> allPatternPlayables = (List<Playable>) playApi
				.getAllPatternedThaat(selectedThaat,
						PermuatationsGenerator.TUPLE);
		final PlyablesArrayAdapter adapter = new PlyablesArrayAdapter(this,
				R.layout.list_item, allPatternPlayables);
		return adapter;
	}

	private void populateThaatDropDown() {
		Spinner spinner = (Spinner) findViewById(R.id.thaatDD);
		spinner.setOnItemSelectedListener(new ThaatDropdownListener());

		// populate data
		List<SymmetricalSet> list = Arrays.asList(SymmetricalSet.values());
		final SymmetricalSetArrayAdapter adapter = new SymmetricalSetArrayAdapter(
				this, R.layout.list_item, list);
		spinner.setAdapter(adapter);

		// select default
		int selectedThatPosition = 0;
		for (SymmetricalSet each : list) {
			if (each.name().equals(SymmetricalSet.THAAT_BILAWAL.name())) {
				break;
			}
			selectedThatPosition++;
		}
		spinner.setSelection((selectedThatPosition > allThaats.size() ? 0
				: selectedThatPosition));
	}

	private class SymmetricalSetArrayAdapter extends
			ArrayAdapter<SymmetricalSet> {
		Map<SymmetricalSet, Integer> mIdMap = new HashMap<SymmetricalSet, Integer>();

		public SymmetricalSetArrayAdapter(Context context, int resource,
				List<SymmetricalSet> objects) {
			super(context, resource, objects);

			for (int i = 0; i < objects.size(); i++) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			SymmetricalSet item = super.getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
	}

	private class ThaatDropdownListener implements
			AdapterView.OnItemSelectedListener {
		private FrequencySet playable;

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			this.playable = (FrequencySet) parent.getItemAtPosition(position);
			Log.i(this.getClass().getName(), "Clicked:" + playable.name());
			refreshListView(getDataAdapter(playable));
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	}

}