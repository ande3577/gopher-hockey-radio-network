package org.dsanderson.mn_baseball_radio;

import java.util.ArrayList;
import java.util.List;

import org.dsanderson.mn_baseball_radio.core.StationInfo;
import org.dsanderson.mn_baseball_radio.list.StationList;
import org.dsanderson.mn_baseball_radio.list.StationListDataBaseObjectFactory;

import android.app.ListActivity;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class StationInfoPrinter {

	private final ListActivity context;
	private StationList list;

	public StationInfoPrinter(Factory factory, ListActivity context) {
		this.context = context;
		list = factory.getStationList();
	}

	public void print() {
		list.filter();
		list.sort();

		int queryCount = context.getResources()
				.getInteger(R.integer.queryCount);
		String from[] = { StationListDataBaseObjectFactory.COLUMN_CALLSIGN,
				StationListDataBaseObjectFactory.COLUMN_NAME,
				StationListDataBaseObjectFactory.COLUMN_FREQUENCY,
				StationListDataBaseObjectFactory.COLUMN_CITY,
				StationListDataBaseObjectFactory.COLUMN_STATE,
				StationListDataBaseObjectFactory.COLUMN_DISTANCE };
		int to[] = { R.id.callsignView, R.id.nameView, R.id.frequencyView,
				R.id.cityView, R.id.stateView, R.id.distanceView };
		Cursor cursor = list.getCursorWithCount(queryCount);
		
		/**
		int size = cursor.getCount();
		List<StationInfo> infoList = new ArrayList<StationInfo>();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			infoList.add((StationInfo) list.get(cursor));
			cursor.moveToNext();
		}
		*/

		context.setListAdapter(new SimpleCursorAdapter(context, R.layout.row,
				cursor, from, to));
	}
}
