package com.needsreal.social.profile;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.needsreal.social.search.SearchItem;


public class User extends AbstractUser
{
	private Marker marker;

	private int precision; // of the location
	protected Mood mood; // a picture

	public User (SearchItem searchItem)
	{
		super ();
		this.hash = searchItem.getHash ();
		this.nickname = searchItem.getName ();
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

	/**
	 * @return the precision
	 */
	public int getPrecision ()
	{
		return precision;
	}

	/**
	 * @param precision the precision to set
	 */
	public void setPrecision (int precision)
	{
		this.precision = precision;
	}

	/**
	 * @return the mood
	 */
	public Mood getMood ()
	{
		return mood;
	}

	/**
	 * @return the mood
	 */
	public int getMoodDrawable ()
	{
		return mood.getDrawable ();
	}

	/**
	 * @param mood the mood to set
	 */
	public void setMood (Mood mood)
	{
		this.mood = mood;
	}
}
