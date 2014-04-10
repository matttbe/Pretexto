package com.needsreal.social.search;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.needsreal.social.map.MapMarkers;

public class SearchItem
{
	// private int id;
	private String hash; // maybe better for a security view point
	private LatLng location;
	private String name;
	private String shortDescription;

	private Marker marker = null;

	public SearchItem (String hash, double latitude, double longitude, String name, String shortDescription)
	{
		this.hash = hash;
		this.location = new LatLng (latitude, longitude);
		this.name = name;
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the hash (id)
	 */
	public String getHash ()
	{
		return hash;
	}

	/**
	 * @return the location
	 */
	public LatLng getLocation ()
	{
		return location;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude ()
	{
		return location.latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude ()
	{
		return location.longitude;
	}

	/**
	 * @return the name
	 */
	public String getName ()
	{
		return name;
	}

	/**
	 * @return the description
	 */
	public String getShortDescription ()
	{
		return shortDescription;
	}

	//_______________ Marker

	/**
	 * @return a MarkerOptions needed for creating a marker
	 */
	private MarkerOptions getMarkerOptions ()
	{
		MarkerOptions markerOptions = new MarkerOptions ();
		markerOptions.title(getName ());
		markerOptions.position(getLocation ());
		if (shortDescription != null)
			markerOptions.snippet (getShortDescription ());
		return markerOptions;
	}

	/**
	 * Add a marker with the info of this item if it doesn't exist yet
	 * @param map where the marker will be added
	 * @return the marker added to the map
	 */
	public void addMarkerToMap (GoogleMap map)
	{
		if (marker == null)
			marker = map.addMarker (getMarkerOptions());
	}

	/**
	 * @return the marker linked to this item
	 */
	public Marker getMarker ()
	{
		return marker;
	}

	/**
	 * Set the new location and update the marker (if available)
	 */
	public void updateLocation (final LatLng newLocation)
	{
		this.location = newLocation;
		if (marker != null)
			MapMarkers.moveWithAnimations (marker, newLocation);
	}

	/**
	 * Check if the item has moved
	 * @param newLocation the new location
	 * @return true if the positions are exactly the same
	 */
	public boolean hasMoved (LatLng newLocation)
	{
		return ! this.location.equals (newLocation);
	}

	/**
	 * Two search items should have the same ID/Hash
	 * @param item2 the other SearchItem
	 * @return true if the ID is the same
	 */
	public boolean equals (SearchItem item2)
	{
		return this.getHash ().equals (item2.getHash ());
	}

	/**
	 * The hash is just the id (hash)
	 */
	public int hashCode ()
	{
		return this.getHash ().hashCode ();
	}
}
