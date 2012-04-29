package org.dsanderson.mn_baseball_radio;

import org.dsanderson.mn_baseball_radio.core.Factory;
import org.dsanderson.mn_baseball_radio.core.UserSettings;
import org.dsanderson.mn_baseball_radio.list.StationList;
import org.dsanderson.mn_baseball_radio.list.StationListDataBaseObjectFactory;

import android.app.ListActivity;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class StationInfoPrinter {

	private final ListActivity context;
	private final UserSettings settings;
	private StationList list;

	public StationInfoPrinter(Factory factory, ListActivity context) {
		this.context = context;
		list = factory.getStationList();
		settings = factory.getSettings();
	}

	public void print() {
		list.filter();
		list.sort();

		String from[] = { StationListDataBaseObjectFactory.COLUMN_CALLSIGN,
				StationListDataBaseObjectFactory.COLUMN_FREQUENCY,
				StationListDataBaseObjectFactory.COLUMN_CITY,
				StationListDataBaseObjectFactory.COLUMN_STATE,
				StationListDataBaseObjectFactory.COLUMN_DISTANCE };
		int to[] = { R.id.callsignView, R.id.frequencyView, R.id.cityView,
				R.id.stateView, R.id.distanceView };
		Cursor cursor = list.getCursorWithCount(settings.getQueryCount());

		context.setListAdapter(new SimpleCursorAdapter(context, R.layout.row,
				cursor, from, to));
	}
}
