package org.dsanderson.mn_baseball_radio;

public class UserSettings {
	static UserSettings instance = null;
	boolean locationEnabled = false;
	String defaultLocation = "";
	
	public UserSettings() {
		assert(instance == null);
		instance = this;
	}
	
	static public UserSettings getInstance() {
		return instance;
	}
	
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
	
	

}
