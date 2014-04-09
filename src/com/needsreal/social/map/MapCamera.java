package com.needsreal.social.map;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class MapCamera
{
	/**
	 * Move camera to a location with a custom zoom
	 * @param location the center position
	 * @param zoom between 2 and 21, see CameraUpdateFactory.newLatLngZoom
	 * zoomLevel - At zoomLevel 1, the equator of the earth is 256 pixels long.
	 * Each successive zoom level is magnified by a factor of 2. 
	 */
	public static void moveCamera (GoogleMap map, final Location location, float zoom)
	{
		final LatLng latLng = new LatLng (location.getLatitude (),
				location.getLongitude ());
		Log.d ("GPS", "move camera to " + latLng.latitude + " " + latLng.longitude);
		map.moveCamera (CameraUpdateFactory.newLatLngZoom (latLng, zoom));
	}
}
