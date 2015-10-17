package com.needsreal.social.server;

import java.util.ArrayList;
import java.util.Random;

import android.os.AsyncTask;

import com.needsreal.social.search.SearchItem;

public class ServerRequestsMarkers extends AsyncTask<MarkersParams, Integer, ArrayList<SearchItem>>
{
	private Random random = new Random ();

	/*
	protected void onPreExecute() {};
	@Override
	protected void onProgressUpdate (Integer... values)
	{
		// TODO Auto-generated method stub
		super.onProgressUpdate (values);
	}*/

	/**
	 * Get markers around a point
	 * @return all markers corresponding to the position
	 */
	@Override
	protected ArrayList<SearchItem> doInBackground (MarkersParams... params)
	{
		MarkersParams info = params[0];

		try
		{
			Thread.sleep (100 + random.nextInt (900));
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<SearchItem> markers = new ArrayList<SearchItem> ();
		for (int i = 0; i < 15; i++)
		{
			double oneMeter = 0.00000898 * 1.05; // with extras
			String hash = Integer.toString (random.nextInt (200));
			double lat = info.getLocation ().getLatitude ()
					+ (random.nextBoolean () ? 1.0 : -1.0)
					* random.nextFloat () * oneMeter * info.getRadius ();
			double lon = info.getLocation ().getLongitude ()
					+ (random.nextBoolean () ? 1.0 : -1.0)
					* random.nextFloat () * oneMeter * info.getRadius ()
					/ Math.cos (lat);
			String name = "Ju" + i;
			String shortDesc = "Le bogoss " + i;
			SearchItem item = new SearchItem (hash, lat, lon, name, shortDesc);
			if (! markers.contains (item))
				markers.add (item);
		}
		return markers;
	}

	@Override
	protected void onPostExecute (ArrayList<SearchItem> result)
	{
		// TODO Auto-generated method stub
		super.onPostExecute (result);
	}

}
