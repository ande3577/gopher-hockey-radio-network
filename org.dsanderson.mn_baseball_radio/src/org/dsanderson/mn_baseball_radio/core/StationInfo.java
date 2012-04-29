package org.dsanderson.mn_baseball_radio.core;

import org.dsanderson.util.DatabaseObject;

public class StationInfo extends DatabaseObject {
	String callsign = "";
	String name = "";
	String frequency = "";
	String city = "";
	String state = "";
	String location = "";
	long range = 0;
	long distance = Long.MAX_VALUE;
	
	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}
	
	public String getCallsign() {
		return callsign;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setFrequency(String freqeuency) {
		this.frequency = freqeuency;
	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setRange(long range) {
		this.range = range;
	}
	
	public long getRange() {
		return range;
	}
	
	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	public long getDistance() {
		return distance;
	}
	
}
