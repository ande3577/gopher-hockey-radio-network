package org.dsanderson.mn_baseball_radio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import android.content.Context;

public class FileFactory {
	final Context context;
	static FileFactory instance = null;
	
	public FileFactory(Context context) {
		this.context = context;
		assert(instance == null);
		instance = this;
	}
	
	static public FileFactory getInstance() {
		assert(instance != null);
		return instance;
	}
	
	public Reader getStationInfoReader() throws IOException {
		InputStream stream = context.getAssets().open("stations.xml");
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		return reader;
	}

}
