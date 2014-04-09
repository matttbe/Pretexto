package com.needsreal.social.map;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.needsreal.social.search.SearchItem;

public class MapMarkers
{
	private GoogleMap map;

	public MapMarkers (GoogleMap map)
	{
		this.map = map;
	}

	/**
	 * Update the list of markers in the list
	 * @param bForceUpdate force requests on the server TODO
	 */
	public void updateMarkers (Location location, boolean bForceUpdate)
	{
		// TODO
		SearchItem testSearchItem = new SearchItem (0, 50.6684991, 4.621698,
				"Beau gosse", "Viens me rencontrer, moi, Julien le beau gosse");

		testSearchItem.addMarkerToMap (map);
		// .icon (BitmapDescriptorFactory.defaultMarker
		// (BitmapDescriptorFactory.HUE_AZURE))
	}
}
