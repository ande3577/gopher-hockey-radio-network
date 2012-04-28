package org.dsanderson.mn_baseball_radio;

import org.dsanderson.android.util.QuickDistanceSource;
import org.dsanderson.util.IDistanceSource;
import org.dsanderson.util.ILocationSource;

import android.content.Context;

public class Factory {
	final Context context;
	UserSettings settings = null;
	UserSettingsSource settingsSource = null;
	ILocationSource locationSource = null;
	StationList stationList = null;
	StationInfoParser stationInfoParser = null;
	IDistanceSource distanceSource = null;
	
	Factory instance = null;
	
	public Factory(Context context) {
		assert(instance == null);
		this.context = context;
		settings = new UserSettings();
		settingsSource = new UserSettingsSource(context, settings);
		locationSource = new CompoundLocationSource(settings, context);
		stationList = new StationList();
		stationInfoParser = new StationInfoParser(stationList);
		distanceSource = new QuickDistanceSource();
	}
	
	Factory getInstance() {
		assert(instance != null);
		return instance;
	}
	
	UserSettings getSettings() {
		return settings;
	}
	
	UserSettingsSource getSettingsSource() {
		return settingsSource;
	}
	
	ILocationSource getLocationSource() {
		return locationSource;
	}
	
	StationList getStationList() {
		return stationList;
	}
	
	StationInfoParser getStationInfoParser() {
		return stationInfoParser;
	}
	
	IDistanceSource getDistanceSource() {
		return distanceSource;
	}

}

