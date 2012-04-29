package org.dsanderson.mn_baseball_radio.list;

import java.io.Reader;
import java.util.List;

import org.dsanderson.android.util.CompoundXmlPullParserFactory;
import org.dsanderson.mn_baseball_radio.core.StationInfo;
import org.dsanderson.util.CompoundXmlParser;

public class StationInfoParser {
	private static final String STATION_LIST_TAG = "stationList";
	private static final String STATION_TAG = "station";
	private static final String CALLSIGN_TAG = "callsign";
	private static final String FREQUENCY_TAG = "frequency";
	private static final String CITY_TAG = "city";
	private static final String STATE_TAG = "state";
	
	private StationList stationList;
	
	public StationInfoParser(StationList stationList) {
		this.stationList = stationList;
	}
	
	public void parse(Reader reader) throws Exception {
		CompoundXmlPullParserFactory parserFactory = CompoundXmlPullParserFactory.getInstance();
	
		CompoundXmlParser tagParser = parserFactory.newParser();
		tagParser.parse(reader);
		List<CompoundXmlParser> stationInfoParser = tagParser
				.getParsers(STATION_LIST_TAG + ":" + STATION_TAG);
		
		stationList.clear();
		
		for (CompoundXmlParser parser : stationInfoParser) {
			StationInfo info = new StationInfo();
			
			String parserOutput;
			if ((parserOutput = parser.getValue(CALLSIGN_TAG)) != null)
				info.setCallsign(parserOutput);
			if ((parserOutput = parser.getValue(FREQUENCY_TAG)) != null)
				info.setFrequency(parserOutput);
			if ((parserOutput = parser.getValue(CITY_TAG)) != null)
				info.setCity(parserOutput);
			if ((parserOutput = parser.getValue(STATE_TAG)) != null)
				info.setState(parserOutput);
			
			stationList.add(info);
		}
	}
}
