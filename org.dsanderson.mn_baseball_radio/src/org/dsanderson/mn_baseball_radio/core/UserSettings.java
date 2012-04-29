package org.dsanderson.mn_baseball_radio.core;

public class UserSettings {
	boolean locationEnabled = false;
	String defaultLocation = "Minneapolis, MN";
	int queryCount = 5;
	
	public void setLocationEnabled(boolean locationEnabled) {
		this.locationEnabled = locationEnabled;
	}
	
	public boolean getLocationEnabled() {
		return locationEnabled;
	}
	
	public void setDefaultLocation(String defaultLocation) {
		this.defaultLocation = defaultLocation;
	}
	
	public String getDefaultLocation() {
		return defaultLocation;
	}
	
	public void setQueryCount(int queryCount) {
		this.queryCount = queryCount;
	}
	
	public int getQueryCount() {
		return queryCount;
	}

}
