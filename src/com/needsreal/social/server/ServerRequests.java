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
			String hash = Integer.toString (random.nextInt (35));
			double lat = latitude + random.nextFloat () / 10;
			double lon = longitude + random.nextFloat () / 10;
			String name = "Ju" + i;
			String shortDesc = "Le bogoss " + i;
			SearchItem item = new SearchItem (hash, lat, lon, name, shortDesc);
			if (! markers.contains (item))
				markers.add (item);
		}
		return markers;
	}

}
