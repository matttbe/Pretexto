package com.needsreal.social.profile;

import com.google.android.gms.maps.model.LatLng;


public class CurrentUser extends AbstractUser
{
	private UserSettings settings;
	private LatLng lastPosition;

	public CurrentUser (String hash, String nickname)
	{
		super (hash, nickname);
		// TODO settings
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the settings
	 */
	public UserSettings getSettings ()
	{
		return settings;
	}

	/**
	 * @return the position
	 */
	public LatLng getLastPosition ()
	{
		return lastPosition;
	}

	/**
	 * @param position the position to set
	 */
	public void setLastPosition (LatLng position)
	{
		this.lastPosition = position;
	}

	@Override
	public LatLng getPosition ()
	{
		return lastPosition;
	}

}
