package org.dsanderson.mn_baseball_radio.list;

import org.dsanderson.android.util.GenericDatabase;
import org.dsanderson.android.util.IDatabaseObjectFactory;
import org.dsanderson.mn_baseball_radio.core.StationInfo;
import org.dsanderson.util.DatabaseObject;

import android.content.ContentValues;
import android.database.Cursor;

public class StationListDataBaseObjectFactory implements IDatabaseObjectFactory {
	public static String COLUMN_CALLSIGN = "callsign";
	private static String TYPE_CALLSIGN = "string not null";

	public static String COLUMN_FREQUENCY = "frequency";
	private static String TYPE_FREQUENCY = "string not null";

	public static String COLUMN_CITY = "city";
	private static String TYPE_CITY = "string not null";

	public static String COLUMN_STATE = "state";
	private static String TYPE_STATE = "string not null";

	public static String COLUMN_LOCATION = "location";
	private static String TYPE_LOCATION = "string not null";

	public static String COLUMN_DISTANCE = "distance";
	private static String TYPE_DISTANCE = "integer not null";

	public static String DATABASE_NAME = "station_info_database.db";
	public static String TABLE_NAME = "station_info";

	public void registerColumns(GenericDatabase database) {
		database.addColumn(COLUMN_CALLSIGN, TYPE_CALLSIGN) //
				.addColumn(COLUMN_FREQUENCY, TYPE_FREQUENCY) //
				.addColumn(COLUMN_CITY, TYPE_CITY) //
				.addColumn(COLUMN_STATE, TYPE_STATE) //
				.addColumn(COLUMN_LOCATION, TYPE_LOCATION) //
				.addColumn(COLUMN_DISTANCE, TYPE_DISTANCE) //
		;
	}

	public void buildContentValues(DatabaseObject object, ContentValues values) {
		StationInfo info = (StationInfo) object;
		values.put(COLUMN_CALLSIGN, info.getCallsign());
		values.put(COLUMN_FREQUENCY, info.getFrequency());
		values.put(COLUMN_CITY, info.getCity());
		values.put(COLUMN_STATE, info.getState());
		values.put(COLUMN_LOCATION, info.getLocation());
		values.put(COLUMN_DISTANCE, info.getDistance());
	}

	public DatabaseObject getObject(Cursor cursor, DatabaseObject object) {
		StationInfo info = new StationInfo();
		info.setCallsign(cursor.getString(cursor.getColumnIndex(COLUMN_CALLSIGN)));
		info.setFrequency(cursor.getString(cursor.getColumnIndex(COLUMN_FREQUENCY)));
		info.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)));
		info.setState(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
		info.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)));
		return (DatabaseObject) info;
	}

}
