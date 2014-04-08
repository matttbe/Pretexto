package com.needsreal.social.search;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchItem
{
	private int id;
	// private String hash; // maybe better for a security view point
	private LatLng location;
	private String name;
	private String shortDescription;
	private String description;
	private Marker marker = null;

	public SearchItem (int id, double latitude, double longitude, String name, String shortDescription)
	{
		this.id = id;
		this.location = new LatLng (latitude, longitude);
		this.name = name;
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the id
	 */
	public int getId ()
	{
		return id;
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

	/**
	 * @return the description
	 */
	public String getDescription ()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription (String description)
	{
		this.description = description;
	}

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
	public Marker addMarkerToMap (GoogleMap map)
	{
		if (marker == null)
			marker = map.addMarker (getMarkerOptions());
		return marker;
	}
}
