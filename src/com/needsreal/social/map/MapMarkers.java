package com.needsreal.social.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.location.Location;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.needsreal.social.search.SearchItem;
import com.needsreal.social.server.MarkersParams;
import com.needsreal.social.server.Server;

public class MapMarkers
{
	private GoogleMap map;
	private Map<String, SearchItem> hashMap;

	private static final int MAXSIZE = 50;
	private static final int ANIMATION_SPEED = 750;

	public MapMarkers (GoogleMap map)
	{
		this.map = map;
		this.hashMap = Collections
				.synchronizedMap (new LinkedHashMap<String, SearchItem> ());
	}

	private ArrayList<SearchItem> getMarkersForLocation (Location location,
			float radius)
	{
		Log.d ("Markers", "== getMarkers: " + location + " " + radius);
		ArrayList<SearchItem> markers = Server
				.getServerMgr ()
				.getServerRequests ()
				.getMarkersForLocation (location.getLatitude (),
						location.getLongitude (), radius);
		return markers;
	}

	private ArrayList<SearchItem> getMarkersForLocation ()
	{
		MarkersParams locationInfo = MapCamera.radiusDistanceCovered (map);
		return getMarkersForLocation (locationInfo.getLocation (),
				locationInfo.getRadius ());
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
			String hash = item.getHash ();
			SearchItem inHashItem = hashMap.remove (hash);
			// if the item already exists, update the position
			if (inHashItem != null)
			{
				Log.d ("Markers", "An old item");
				hashMap.put (hash, inHashItem); // put it at the end

				if (inHashItem.hasMoved (item.getLocation ()))
				{
					Log.d ("Markers",
							"An old item: the position has changed ; from "
									+ inHashItem.getLocation () + " to "
									+ item.getLocation ());
					inHashItem.getMarker ().setIcon (BitmapDescriptorFactory
							.defaultMarker (BitmapDescriptorFactory.HUE_AZURE));
					inHashItem.updateLocation (item.getLocation ());
				}
			}
			else
			{
				Log.d ("Markers", "A new item");
				item.addMarkerToMap (map);
				hashMap.put (hash, item);
			}
		}

		// remove extras
		removeOldMarkers ();
	}

	private void removeOldMarkers ()
	{
		// Remove extras
		int nbToRm = hashMap.size () - MAXSIZE;
		if (nbToRm > 0)
		{
			String keysToRm[] = new String[nbToRm];
			for (Entry<String, SearchItem> entry : hashMap.entrySet ())
			{
				nbToRm--;
				keysToRm[nbToRm] = entry.getKey ();
				entry.getValue ().getMarker ().remove ();
				if (nbToRm == 0)
					break;
			}
			for (String key : keysToRm)
				hashMap.remove (key);
		}
	}

	public static void moveWithAnimations (final Marker marker,
			final LatLng newLocation)
	{
		final LatLng oldPosition = marker.getPosition ();
		final double oldLatitude = oldPosition.latitude;
		final double oldLongitude = oldPosition.longitude;

		final long start = SystemClock.uptimeMillis ();
		final Interpolator interpolator = new LinearInterpolator ();
		final Handler handler = new Handler ();
		handler.post (new Runnable ()
		{
			@Override
			public void run ()
			{
			// http://ddewaele.github.io/GoogleMapsV2WithActionBarSherlock/part3
				long elapsed = SystemClock.uptimeMillis () - start;
				double t = interpolator.getInterpolation ((float) elapsed
						/ ANIMATION_SPEED);
				double lat = t * newLocation.latitude + (1 - t) * oldLatitude;
				double lng = t * newLocation.longitude + (1 - t) * oldLongitude;
				marker.setPosition (new LatLng (lat, lng));
				if (t < 1.0)
					handler.postDelayed (this, 16);
				else
					marker.setPosition (newLocation);
			}
		});
	}
}
