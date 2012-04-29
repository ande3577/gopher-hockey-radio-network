package org.dsanderson.mn_baseball_radio;

import org.dsanderson.android.util.AndroidProgressBar;
import org.dsanderson.android.util.Dialog;
import org.dsanderson.android.util.LocationCoder;
import org.dsanderson.mn_baseball_radio.core.StationInfo;
import org.dsanderson.mn_baseball_radio.list.StationList;
import org.dsanderson.mn_baseball_radio.list.StationListDataBaseObjectFactory;
import org.dsanderson.util.IDistanceSource;
import org.dsanderson.util.Units;

import android.content.Context;
import android.os.AsyncTask;

public class LoadStationsTask extends AsyncTask<Integer, Integer, Integer> {
	Factory factory = null;
	StationList list = null;
	Exception e = null;
	private final Context context;
	LocationCoder locationCoder = null;
	AndroidProgressBar progressBar = null;
	boolean refresh = true;

	public LoadStationsTask(Factory factory, Context context) {
		this.factory = factory;
		this.list = factory.getStationList();
		this.context = context;
		locationCoder = new LocationCoder(context);
		progressBar = factory.getProgressBar();
	}

	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}

	@Override
	protected void onPreExecute() {
		e = null;
		if (refresh) {
			factory.getLocationSource().updateLocation();
			progressBar.setMessage("Loading stations...");
			progressBar.show();
		}
	}

	@Override
	protected void onPostExecute(Integer result) {
		progressBar.close();
		if (e != null) {
			e.printStackTrace();
			Dialog dialog = new Dialog(context, e);
			dialog.show();
		} else {
			factory.getPrinter().print();
		}
	}

	@Override
	protected Integer doInBackground(Integer... params) {
		try {
			if (refresh) {
				list.load();

				String location = factory.getLocationSource().getLocation();
				IDistanceSource distanceSource = factory.getDistanceSource();

				for (int i = 0; i < list.size(); i++) {
					StationInfo info = list.get(i);
					String stationLocation = info.getLocation();
					if (stationLocation == null
							|| stationLocation.length() == 0)
						stationLocation = locationCoder.getLocation(info
								.getCity() + ", " + info.getState()).location;

					distanceSource.updateDistance(location, stationLocation,
							progressBar);
					boolean valid = distanceSource.getDistanceValids().get(0);
					if (valid)
						info.setDistance((long) Units
								.metersToMiles(distanceSource.getDistances()
										.get(0)));
					else
						info.setDistance(Long.MAX_VALUE);

					if (list.find(info.getCallsign()) != null) {
						list.update(
								info,
								StationListDataBaseObjectFactory.COLUMN_DISTANCE,
								info.getDistance());
					} else {
						list.add(info);
					}
				}
			}
		} catch (Exception e) {
			this.e = e;
		}

		return null;
	}

}
