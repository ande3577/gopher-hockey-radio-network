package org.dsanderson.mn_baseball_radio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dsanderson.util.IList;

public class StationList implements IList<StationInfo> {
	List<StationInfo> stationList = new ArrayList<StationInfo>();

	public void add(StationInfo object) {
		stationList.add(object);
	}

	public void remove(StationInfo object) {
		stationList.remove(object);
	}

	public void remove(int index) {
		stationList.remove(index);
		
	}

	public StationInfo get(int index) {
		return stationList.get(index);
	}

	public StationInfo find(String name) {
		for (StationInfo info : stationList) {
			if (info.getCallsign().equals(name))
				return info;
		}
		return null;
	}

	public void load() throws Exception {
		StationInfoParser parser = new StationInfoParser(this);
		parser.parse(FileFactory.getInstance().getStationInfoReader());
	}

	public void save() throws Exception {
	}

	public void close() {
	}

	public Date getTimestamp() {
		return null;
	}

	public void clear() {
		stationList.clear();
	}

	public int size() {
		return stationList.size();
	}

}
