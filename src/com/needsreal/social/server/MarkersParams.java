package com.needsreal.social.server;

import android.location.Location;


public class MarkersParams
{
	private Location location;
	private float radius;
	private String request;

	public MarkersParams (Location location, float radius, String request)
	{
		this.location = location;
		this.radius = radius;
		this.request = request;
	}

	/**
	 * @return the location
	 */
	public Location getLocation ()
	{
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation (Location location)
	{
		this.location = location;
	}

	/**
	 * @return the radius
	 */
	public float getRadius ()
	{
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius (float radius)
	{
		this.radius = radius;
	}

	/**
	 * @return the request
	 */
	public String getRequest ()
	{
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest (String request)
	{
		this.request = request;
	}
}
