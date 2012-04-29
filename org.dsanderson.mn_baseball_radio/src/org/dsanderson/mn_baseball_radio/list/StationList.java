package org.dsanderson.mn_baseball_radio.list;

import java.util.Date;

import org.dsanderson.android.util.GenericDatabase;
import org.dsanderson.mn_baseball_radio.FileFactory;
import org.dsanderson.mn_baseball_radio.core.StationInfo;
import org.dsanderson.util.IList;

import android.content.Context;

public class StationList extends GenericDatabase implements IList<StationInfo> {
	public StationList(Context context,
			StationListDataBaseObjectFactory objectFactory, int databaseVersion) {
		super(context, StationListDataBaseObjectFactory.DATABASE_NAME,
				databaseVersion, StationListDataBaseObjectFactory.TABLE_NAME,
				objectFactory, StationListDataBaseObjectFactory.COLUMN_CALLSIGN);
	}

	public void add(StationInfo object) {
		super.add(object);
	}

	public void remove(StationInfo object) {
		super.remove(object);
	}

	public void remove(int index) {
		super.remove(index);
	}

	public StationInfo get(int index) {
		return (StationInfo) super.get(index);
	}

	public StationInfo find(String name) {
		return (StationInfo) super.find(name);
	}

	public void load() throws Exception {
		super.load();
		
		if (size() == 0) {
			StationInfoParser parser = new StationInfoParser(this);
			parser.parse(FileFactory.getInstance().getStationInfoReader());
		}
	}

	public void save() throws Exception {
		super.save();
	}

	public void close() {
		super.close();
	}

	public Date getTimestamp() {
		return super.getTimestamp();
	}

	public void clear() {
		super.clear();
	}

	public int size() {
		return super.size();
	}

	public void sort() {
		clearSortOrder();
		addSortOrder(StationListDataBaseObjectFactory.COLUMN_DISTANCE, false);
	}

	public void filter() {
		clearFilter();
		addFilter(StationListDataBaseObjectFactory.COLUMN_DISTANCE + "<"
				+ Long.MAX_VALUE);
	}

}
