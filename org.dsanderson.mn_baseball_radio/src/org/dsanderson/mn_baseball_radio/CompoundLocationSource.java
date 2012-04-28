package org.dsanderson.mn_baseball_radio;

import org.dsanderson.android.util.LocationSource;
import org.dsanderson.util.ILocationSource;

import android.content.Context;

public class CompoundLocationSource implements ILocationSource {
	UserSettings settings;
	LocationSource locationSource;
	
	public CompoundLocationSource(UserSettings settings, Context context) {
		this.settings = settings;
		locationSource = new LocationSource(context, settings.getDefaultLocation());
	}

	public void updateLocation() {
		if (settings.getLocationEnabled())
			locationSource.updateLocation();
	}

	public String getLocation() {
		String location = settings.getDefaultLocation();
		if (settings.getLocationEnabled())
			location = locationSource.getLocation();
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
