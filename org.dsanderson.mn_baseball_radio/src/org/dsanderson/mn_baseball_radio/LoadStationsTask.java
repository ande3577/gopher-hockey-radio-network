package org.dsanderson.mn_baseball_radio;

import org.dsanderson.android.util.Dialog;
import org.dsanderson.android.util.LocationCoder;
import org.dsanderson.util.IDistanceSource;

import android.content.Context;
import android.os.AsyncTask;

public class LoadStationsTask extends AsyncTask<Integer, Integer, Integer> {
	Factory factory = null;
	StationList list = null;
	Exception e = null;
	private final Context context;
	LocationCoder locationCoder = null;

	public LoadStationsTask(Factory factory, Context context) {
		this.factory = factory;
		this.list = factory.getStationList();
		this.context = context;
		locationCoder = new LocationCoder(context);
	}

	@Override
	protected void onPreExecute() {
		e = null;
		factory.getLocationSource().updateLocation();
	}

	@Override
	protected void onPostExecute(Integer result) {
		if (e != null) {
			e.printStackTrace();
			Dialog dialog = new Dialog(context, e);
			dialog.show();
		}
	}

	@Override
	protected Integer doInBackground(Integer... params) {
		try {
			list.load();

			String location = factory.getLocationSource().getLocation();
			IDistanceSource distanceSource = factory.getDistanceSource();

			for (int i = 0; i < list.size(); i++) {
				StationInfo info = list.get(i);
				String stationLocation = info.getLocation();
				if (stationLocation == null || stationLocation.length() == 0)
					stationLocation = locationCoder.getLocation(info.getCity()
							+ ", " + info.getState()).location;

				distanceSource.updateDistance(location, stationLocation, null);
				boolean valid = distanceSource.getDistanceValids().get(0);
				if (valid)
					info.setDistance(distanceSource.getDistances().get(0));
				else
					info.setDistance(Long.MAX_VALUE);
			}
		} catch (Exception e) {
			this.e = e;
		}

		return null;
	}

}
