package com.needsreal.social.profile;

import com.google.android.gms.maps.model.Marker;
import com.needsreal.social.search.SearchItem;


public class User extends AbstractUser
{
	private Marker marker;

	public User (String hash, String nickname)
	{
		super (hash, nickname);
		// TODO Auto-generated constructor stub
	}

	public User (SearchItem searchItem)
	{
		super (searchItem.getHash (), searchItem.getName ());
		this.marker = searchItem.getMarker ();
		this.shortDescription = searchItem.getShortDescription ();
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
}
