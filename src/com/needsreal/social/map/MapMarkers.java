package com.needsreal.social.map;

import java.util.ArrayList;
import java.util.HashMap;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.needsreal.social.search.SearchItem;
import com.needsreal.social.server.Server;

public class MapMarkers
{
	private GoogleMap map;
	private HashMap<String, SearchItem> hashMap;

	public MapMarkers (GoogleMap map)
	{
		this.map = map;
		this.hashMap = new HashMap<String, SearchItem> ();
	}

	private ArrayList<SearchItem> getMarkersForLocation (Location location,
			float radius)
	{
		Log.d ("Markers", "getMarkers: " + location + " " + radius);
		ArrayList<SearchItem> markers = Server
				.getServerMgr ()
				.getServerRequests ()
				.getMarkersForLocation (location.getLatitude (),
						location.getLongitude (), radius);
		return markers;
	}

	private ArrayList<SearchItem> getMarkersForLocation ()
	{
		Object[] locationInfo = MapCamera.radiusDistanceCovered (map);
		return getMarkersForLocation ((Location) locationInfo[1],
				(Float) locationInfo[0]);
	}

	/**
	 * Update the list of markers in the list
	 * @param bForceUpdate force requests on the server TODO
	 */
	public void updateMarkers (boolean bForceUpdate)
	{
		ArrayList<SearchItem> items = getMarkersForLocation ();
		for (SearchItem item : items)
		{
			// if the item already exists, update the position
			if (hashMap.containsKey (item.getHash ()))
			{
				Log.d ("Markers", "An old item");
				SearchItem inHashItem = hashMap.get (item.getHash ());
				if (inHashItem.hasMoved (item.getLocation ()))
				{
					Log.d ("Markers",
							"An old item: the position has changed ; from "
									+ inHashItem.getLocation () + " to "
									+ item.getLocation ());
					inHashItem.updateLocation (item.getLocation ());
				}
			}
			else
			{
				Log.d ("Markers", "An new item");
				item.addMarkerToMap (map);
				hashMap.put (item.getHash (), item);
			}

		}

		// .icon (BitmapDescriptorFactory.defaultMarker
		// (BitmapDescriptorFactory.HUE_AZURE))
	}
}
