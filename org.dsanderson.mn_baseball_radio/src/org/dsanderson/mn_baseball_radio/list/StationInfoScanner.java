package org.dsanderson.mn_baseball_radio.list;

import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dsanderson.mn_baseball_radio.core.StationInfo;

public class StationInfoScanner {
	
	

	public static void scan(Reader reader, StationList list) {
		String state = null;
		String token = null;
		Scanner scanner = new Scanner(reader);
		scanner.useDelimiter("[\\t\\n]");
		StationInfo newStation = new StationInfo();

		Pattern statePattern = Pattern.compile("\\<.*\\>");

		while (scanner.hasNext()) {
			token = scanner.next().trim();
			Matcher matcher = statePattern.matcher(token);
			if (matcher.matches()) {
				state = matcher.group().replaceAll("[\\<\\>]", "");
			} else {
				newStation.setCity(token);
				newStation.setState(state);

				token = scanner.next().trim();
				newStation.setCallsign(token);

				token = scanner.next().trim();
				newStation.setFrequency(token);
				
				list.add(newStation);
				
			}
		}
	}
}
