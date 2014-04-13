package com.needsreal.social.profile;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.needsreal.social.search.SearchItem;


public class User extends AbstractUser
{
	private Marker marker;
	private int precision; // of the location

	/*
	public User (String hash, String nickname)
	{
		super (hash, nickname);
		// TODO Auto-generated constructor stub
	}*/

	public User (SearchItem searchItem)
	{
		super (searchItem.getHash (), searchItem.getName ());
		this.marker = searchItem.getMarker ();
		this.shortDesc = searchItem.getShortDescription ();
	}

	/**
	 * @return the marker
	 */
	public Marker getMarker ()
	{
		return marker;
	}

	/**
	 * @param marker the marker to set
	 */
	public void setMarker (Marker marker)
	{
		this.marker = marker;
	}

	@Override
	public LatLng getPosition ()
	{
		if (marker == null)
			return null;
		return marker.getPosition ();
	}
}
