package org.dsanderson.mn_baseball_radio;

import org.dsanderson.mn_baseball_radio.core.UserSettings;
import org.dsanderson.util.IUserSettingsSource;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class UserSettingsSource implements IUserSettingsSource {
	SharedPreferences preference;
	final UserSettings settings;

	public UserSettingsSource(Context context, UserSettings settings) {
		preference = PreferenceManager.getDefaultSharedPreferences(context);
		this.settings = settings;
		preference
				.registerOnSharedPreferenceChangeListener(preferenceChangedListener);
	}

	private OnSharedPreferenceChangeListener preferenceChangedListener = new OnSharedPreferenceChangeListener() {

		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
			if (key.equals("enableLocation")) {
				boolean value = sharedPreferences.getBoolean(key,
						settings.getLocationEnabled());
				settings.setLocationEnabled(value);
			} else if (key.equals("defaultLocation")) {
				String locationString = sharedPreferences.getString(key,
						settings.getDefaultLocation());
				settings.setDefaultLocation(locationString);
			} else if (key.equals("queryCount")) {
				settings.setQueryCount(Integer.parseInt(preference.getString(
						"queryCount",
						Integer.toString(settings.getQueryCount()))));
			}
		}

	};

	public void loadUserSettings() {
		settings.setLocationEnabled(preference.getBoolean("enableLocation",
				settings.getLocationEnabled()));
		settings.setDefaultLocation(preference.getString("defaultLocation",
				settings.getDefaultLocation()));
		settings.setQueryCount(Integer.parseInt(preference.getString(
				"queryCount", Integer.toString(settings.getQueryCount()))));
	}

	public void saveUserSettings() {
	}

}
