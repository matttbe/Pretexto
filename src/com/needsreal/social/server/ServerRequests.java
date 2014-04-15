package com.needsreal.social.server;

import java.util.ArrayList;
import java.util.Random;

import com.needsreal.social.search.SearchItem;

public class ServerRequests
{

	private Random random;

	public ServerRequests ()
	{
		random = new Random ();
	}

	/**
	 * Get markers around a point
	 *
	 * @param latitude the latitude of the center point
	 * @param longitude the longitude of the center point
	 * @param radius the radius that we have to cover
	 * @return all markers corresponding to the position
	 */
	public ArrayList<SearchItem> getMarkersForLocation (double latitude,
			double longitude, float radius)
	{
		// TODO Auto-generated method stub
		ArrayList<SearchItem> markers = new ArrayList<SearchItem> ();
		for (int i = 0; i < 15; i++)
		{
			double oneMeter = 0.00000898 * 1.05; // with extras
			String hash = Integer.toString (random.nextInt (500));
			double lat = latitude + (random.nextBoolean () ? 1.0 : -1.0)
					* random.nextFloat () * oneMeter * radius;
			double lon = longitude + (random.nextBoolean () ? 1.0 : -1.0)
					* random.nextFloat () * oneMeter * radius
					/ Math.cos (lat);
			String name = "Ju" + i;
			String shortDesc = "Le bogoss " + i;
			SearchItem item = new SearchItem (hash, lat, lon, name, shortDesc);
			if (! markers.contains (item))
				markers.add (item);
		}
		return markers;
	}

}
