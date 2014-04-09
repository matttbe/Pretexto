package com.needsreal.social.map;

import java.util.ArrayList;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.needsreal.social.search.SearchItem;

public class MapMarkers
{
	private GoogleMap map;

	public MapMarkers (GoogleMap map)
	{
		this.map = map;
	}

	private ArrayList<SearchItem> getMarkersForLocation (Location location,
			float radius)
	{
		Log.d ("Markers", "getMarkers: " + location + " " + radius);
		return null;
	}

	private ArrayList<SearchItem> getMarkersForLocation ()
	{
		Object[] locationInfo = MapCamera.radiusDistanceCovered (map);
		return getMarkersForLocation ((Location) locationInfo[1],
				(Float) locationInfo[0]);
	}

	/**
	 * Get Markers for my location if I can be located
	 */
	private ArrayList<SearchItem> getMarkersForMyLocation ()
	{
		Location myLocation = map.getMyLocation ();
		if (myLocation == null) // no signal...
			return null;

		Object[] locationInfo = MapCamera.radiusDistanceCovered (map);
		return getMarkersForLocation (map.getMyLocation (),
				(Float) locationInfo[0]);
	}

	/**
	 * Update the list of markers in the list
	 * @param bForceUpdate force requests on the server TODO
	 */
	public void updateMarkers (Location location, boolean bForceUpdate)
	{
		// TODO
		getMarkersForLocation ();
		getMarkersForMyLocation ();
		SearchItem testSearchItem = new SearchItem (0, 50.6684991, 4.621698,
				"Beau gosse", "Viens me rencontrer, moi, Julien le beau gosse");

		testSearchItem.addMarkerToMap (map);
		// .icon (BitmapDescriptorFactory.defaultMarker
		// (BitmapDescriptorFactory.HUE_AZURE))
	}
}
