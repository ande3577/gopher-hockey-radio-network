package org.dsanderson.mn_baseball_radio;

import org.dsanderson.android.util.LocationCoder;
import org.dsanderson.android.util.LocationSource;
import org.dsanderson.util.ILocationSource;

import android.content.Context;

public class CompoundLocationSource implements ILocationSource {
	UserSettings settings;
	LocationSource locationSource;
	LocationCoder locationCoder;

	public CompoundLocationSource(UserSettings settings, Context context) {
		this.settings = settings;
		locationSource = new LocationSource(context,
				settings.getDefaultLocation());
		locationCoder = new LocationCoder(context);
	}

	public void updateLocation() {
		if (settings.getLocationEnabled())
			locationSource.updateLocation();
	}

	public String getLocation() {
		String location = settings.getDefaultLocation();
		if (settings.getLocationEnabled() && locationSource.getHasNewLocation())
			location = locationSource.getLocation();
		else {
			try {
				location = locationCoder.getLocation(settings.getDefaultLocation()).location;				
			} catch (Exception e) {
				// ignore exception
			}
		}
		return location;
	}

	public void setLocation(String location) {
		if (settings.getLocationEnabled())
			locationSource.setLocation(location);
	}

	public boolean getHasNewLocation() {
		if (settings.getLocationEnabled())
			return locationSource.getHasNewLocation();

		return false;
	}

}
