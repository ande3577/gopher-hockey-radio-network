package org.dsanderson.mn_baseball_radio;

import org.dsanderson.mn_baseball_radio.core.Factory;
import org.dsanderson.mn_baseball_radio.core.UserSettings;
import org.dsanderson.util.ICompoundLocationSource;
import org.dsanderson.util.IUserSettingsSource;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class UserSettingsSource implements IUserSettingsSource {
	SharedPreferences preference;
	final UserSettings settings;
	final ICompoundLocationSource locationSource;

	public UserSettingsSource(Context context, Factory factory) {
		preference = PreferenceManager.getDefaultSharedPreferences(context);
		this.settings = factory.getSettings();
		this.locationSource = factory.getLocationSource();
		preference
				.registerOnSharedPreferenceChangeListener(preferenceChangedListener);
	}

	private OnSharedPreferenceChangeListener preferenceChangedListener = new OnSharedPreferenceChangeListener() {

		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
			if (key.equals("enableLocation")) {
				boolean value = sharedPreferences.getBoolean(key,
						locationSource.getLocationEnabled());
				locationSource.setLocationEnabled(value);
			} else if (key.equals("defaultLocation")) {
				String locationString = sharedPreferences.getString(key,
						locationSource.getDefaultLocation());
				locationSource.setDefaultLocation(locationString);
			} else if (key.equals("queryCount")) {
				settings.setQueryCount(Integer.parseInt(preference.getString(
						"queryCount",
						Integer.toString(settings.getQueryCount()))));
			}
		}

	};

	public void loadUserSettings() {
		locationSource.setLocationEnabled(preference.getBoolean(
				"enableLocation", locationSource.getLocationEnabled()));
		locationSource.setDefaultLocation(preference.getString(
				"defaultLocation", locationSource.getDefaultLocation()));
		settings.setQueryCount(Integer.parseInt(preference.getString(
				"queryCount", Integer.toString(settings.getQueryCount()))));
	}

	public void saveUserSettings() {
	}

}
