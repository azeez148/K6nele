/*
 * Copyright 2011, Institute of Cybernetics at Tallinn University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ee.ioc.phon.android.speak;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * <p>Preferences activity. Updates some preference-summaries automatically,
 * if the user changes a preference.</p>
 * 
 * @author Kaarel Kaljurand
 */
public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);

		SharedPreferences sp = getPreferenceScreen().getSharedPreferences();

		Preference prefAutoStopAfterTime = (Preference) findPreference(getString(R.string.keyAutoStopAfterTime));
		updateSummary(prefAutoStopAfterTime, sp.getString(getString(R.string.keyAutoStopAfterTime), "?"));
	}


	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}


	@Override
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

		SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
		Preference service = (Preference) findPreference(getString(R.string.keyService));
		service.setSummary(sp.getString(getString(R.string.keyService), ""));
	}


	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		Preference pref = findPreference(key);
		if (pref instanceof EditTextPreference) {
			EditTextPreference etp = (EditTextPreference) pref;
			pref.setSummary(etp.getText());
		} else if (pref instanceof ListPreference) {
			ListPreference lp = (ListPreference) pref;
			updateSummary(pref, lp.getValue());
		}
	}


	private void updateSummary(Preference pref, String str) {
		pref.setSummary(String.format(getString(R.string.summaryAutoStopAfterTime), str));		
	}
}