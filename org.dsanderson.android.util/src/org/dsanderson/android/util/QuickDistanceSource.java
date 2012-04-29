/**
 * @author David S Anderson
 *
 *
 * Copyright (C) 2012 David S Anderson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dsanderson.android.util;

import java.util.ArrayList;
import java.util.List;

import org.dsanderson.util.IDistanceSource;
import org.dsanderson.util.IProgressBar;


/**
 * 
 */
public class QuickDistanceSource implements IDistanceSource {

	List<Integer> distances = new ArrayList<Integer>();
	List<Boolean> distanceValids = new ArrayList<Boolean>();
	List<Integer> durations = new ArrayList<Integer>();
	List<Boolean> durationValids = new ArrayList<Boolean>();

	public QuickDistanceSource() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dsanderson.util.IDistanceSource#updateDistances(java.lang.String,
	 * java.util.List, org.dsanderson.util.IProgressBar)
	 */
	public void updateDistances(String src, List<String> dests,
			IProgressBar progressBar) throws Exception {

		distances.clear();
		distanceValids.clear();
		durations.clear();
		durationValids.clear();

		if (src.length() == 0 || getMaxStringLength(dests) == 0)
			return;

		String srcCoords[] = src.split("\\,");
		if (srcCoords.length != 2)
			throw new Exception("Invalid source: " + src);
		double srcLat = Double.parseDouble(srcCoords[0]);
		double srcLong = Double.parseDouble(srcCoords[1]);

		for (String dest : dests) {
			String destCoords[] = dest.split("\\,");
			if (destCoords.length == 2) {
				double destLat = Double.parseDouble(destCoords[0]);
				double destLong = Double.parseDouble(destCoords[1]);

				double R = 6371 * 1000; // m
				double dLat = Math.toRadians(destLat - srcLat);
				double dLon = Math.toRadians(destLong - srcLong);
				double lat1 = Math.toRadians(srcLat);
				double lat2 = Math.toRadians(destLat);

				double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
						+ Math.sin(dLon / 2) * Math.sin(dLon / 2)
						* Math.cos(lat1) * Math.cos(lat2);
				double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
				double d = R * c;
				distances.add((int) d);
				distanceValids.add(true);
			} else {
				distances.add(0);
				distanceValids.add(false);
			}
			durations.add(0);
			durationValids.add(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dsanderson.util.IDistanceSource#getDistances()
	 */
	public List<Integer> getDistances() {
		return distances;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dsanderson.util.IDistanceSource#getDurations()
	 */
	public List<Integer> getDurations() {
		return durations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dsanderson.util.IDistanceSource#getDistanceValids()
	 */
	public List<Boolean> getDistanceValids() {
		return distanceValids;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dsanderson.util.IDistanceSource#getDurationValids()
	 */
	public List<Boolean> getDurationValids() {
		return durationValids;
	}

	private int getMaxStringLength(List<String> strings) {
		int length = 0;
		for (String str : strings) {
			if (str.length() > length)
				length = str.length();
		}
		return length;
	}

	public void updateDistance(String src, String dest, IProgressBar progressBar)
			throws Exception {
		List<String> dests = new ArrayList<String>();
		dests.add(dest);
		updateDistances(src, dests, progressBar);
	}

}
