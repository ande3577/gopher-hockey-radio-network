package org.dsanderson.mn_baseball_radio.core;

import org.dsanderson.android.util.AndroidProgressBar;
import org.dsanderson.android.util.QuickDistanceSource;
import org.dsanderson.mn_baseball_radio.StationInfoPrinter;
import org.dsanderson.mn_baseball_radio.UserSettingsSource;
import org.dsanderson.mn_baseball_radio.list.StationList;
import org.dsanderson.mn_baseball_radio.list.StationListDataBaseObjectFactory;
import org.dsanderson.util.IDistanceSource;
import org.dsanderson.util.ILocationSource;

import android.app.ListActivity;
import android.content.Context;

public class Factory {
	final Context context;
	UserSettings settings = null;
	UserSettingsSource settingsSource = null;
	ILocationSource locationSource = null;
	StationListDataBaseObjectFactory objectFactory = null;
	StationList stationList = null;
	IDistanceSource distanceSource = null;
	StationInfoPrinter printer = null;
	AndroidProgressBar progressBar = null;

	Factory instance = null;

	public Factory(ListActivity context, int databaseVersion) {
		assert (instance == null);
		this.context = context;
		settings = new UserSettings();
		settingsSource = new UserSettingsSource(context, settings);
		locationSource = new CompoundLocationSource(settings, context);
		objectFactory = new StationListDataBaseObjectFactory();
		stationList = new StationList(context, objectFactory, databaseVersion);
		try {
			stationList.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		distanceSource = new QuickDistanceSource();
		printer = new StationInfoPrinter(this, context);
		progressBar = new AndroidProgressBar(context);
	}

	public Factory getInstance() {
		assert (instance != null);
		return instance;
	}

	public UserSettings getSettings() {
		return settings;
	}

	public UserSettingsSource getSettingsSource() {
		return settingsSource;
	}

	public ILocationSource getLocationSource() {
		return locationSource;
	}

	public StationList getStationList() {
		return stationList;
	}

	public IDistanceSource getDistanceSource() {
		return distanceSource;
	}
	
	public StationInfoPrinter getPrinter() {
		return printer;
	}
	
	public AndroidProgressBar getProgressBar() {
		return progressBar;
	}
	
}
