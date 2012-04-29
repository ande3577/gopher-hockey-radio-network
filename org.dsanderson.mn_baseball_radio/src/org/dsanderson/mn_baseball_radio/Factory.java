package org.dsanderson.mn_baseball_radio;

import org.dsanderson.android.util.QuickDistanceSource;
import org.dsanderson.mn_baseball_radio.core.UserSettings;
import org.dsanderson.mn_baseball_radio.list.StationInfoParser;
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
	StationInfoParser stationInfoParser = null;
	IDistanceSource distanceSource = null;
	StationInfoPrinter printer = null;

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
		stationInfoParser = new StationInfoParser(stationList);
		distanceSource = new QuickDistanceSource();
		printer = new StationInfoPrinter(this, context);
	}

	Factory getInstance() {
		assert (instance != null);
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
	
	StationInfoPrinter getPrinter() {
		return printer;
	}

}
