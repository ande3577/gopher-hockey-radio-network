package org.dsanderson.mn_baseball_radio;

import org.dsanderson.android.util.Dialog;

import android.content.Context;
import android.os.AsyncTask;

public class LoadStationsTask extends AsyncTask<Integer, Integer, Integer> {
	StationList list = null;
	Exception e = null;
	private final Context context;

	public LoadStationsTask(StationList list, Context context) {
		this.list = list;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		e = null;
	}

	@Override
	protected void onPostExecute(Integer result) {
		if (e != null) {
			e.printStackTrace();
			Dialog dialog = new Dialog(context, e);
			dialog.show();
		}
	}

	@Override
	protected Integer doInBackground(Integer... params) {
		try {
			list.load();
		} catch (Exception e) {
			this.e = e;
		}

		return null;
	}

}
