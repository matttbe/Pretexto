package com.needsreal.social.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.needsreal.social.R;
import com.needsreal.social.map.MapCamera;
import com.needsreal.social.map.MapMarkers;


public class MapMainActivity extends FragmentActivity implements LocationListener
{
	private LocationManager locationManager;
	private GoogleMap map;
	private MapMarkers markers;

	private boolean isUsingGPS = false;
	private boolean isUsingNetwork = false;
	private boolean isFirstPosition = true;

	private static final int GPS_MIN_TIME_UPDATE = 2 * 60 * 1000; // 2min
	private static final int NETWORK_MIN_TIME_UPDATE = 5 * 60 * 1000; // 5min
	private static final int MIN_DISTANCE_UPDATE = 10; // 10 meters
	private static final float ZOOM_INIT = 11;
	private static final float ZOOM_GPS = 12;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		
		// Windows properties
		requestWindowFeature (Window.FEATURE_NO_TITLE);
		getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView (R.layout.activity_map_main);
		
		map = ((MapFragment) getFragmentManager ().findFragmentById (R.id.map))
				.getMap ();
		map.setIndoorEnabled (true);
		map.setBuildingsEnabled (true);
		map.setMyLocationEnabled (true);
		map.setOnCameraChangeListener (onCameraChangeListener);
		map.getUiSettings ().setZoomControlsEnabled (false);
		map.getUiSettings ().setMyLocationButtonEnabled (true);
		// TODO: move it, move it: https://stackoverflow.com/questions/14489880/

		markers = new MapMarkers (map);

		FragmentManager fm = getFragmentManager ();
		fm.beginTransaction ()
				.hide (getFragmentManager ()
						.findFragmentById (R.id.searchblock)).commit ();
		
		final ImageButton settingsButton= (ImageButton) findViewById (R.id.settingsButton);
		settingsButton.setOnClickListener (new View.OnClickListener ()
		
		{
			public void onClick (View v)
			{
				Intent intent = new Intent (MapMainActivity.this,
						ProfileActivity.class); 
				startActivity (intent);
			}
		});

	}

	@Override
	public void onResume ()
	{
		super.onResume ();

		// Obtention de la référence du service
		locationManager = (LocationManager) this
				.getSystemService (LOCATION_SERVICE);

		// Si le GPS est disponible, on s'y abonne
		if (locationManager.isProviderEnabled (LocationManager.GPS_PROVIDER))
			registerGPS ();
		else if (locationManager.isProviderEnabled (LocationManager.NETWORK_PROVIDER))
			registerNetwork ();
		else
			Toast.makeText (this, "GPS not available", Toast.LENGTH_LONG)
					.show ();

		// center around the last know location
		Location lastKnowLocation = null;
		lastKnowLocation = locationManager
				.getLastKnownLocation (LocationManager.GPS_PROVIDER);
		if (lastKnowLocation == null)
		{
			lastKnowLocation = locationManager
					.getLastKnownLocation (LocationManager.NETWORK_PROVIDER);
			// TODO: if NULL => getLastLocation from settings
		}

		if (lastKnowLocation != null)
		{
			MapCamera.moveCamera (map, lastKnowLocation, ZOOM_INIT);
			markers.updateMarkers (true);
		}
	}

	@Override
	public void onPause ()
	{
		super.onPause ();
		unregisterLocation ();
	}

	@Override
	public void onBackPressed ()
	{
		Toast.makeText (getApplicationContext (), "TODO",
				Toast.LENGTH_SHORT).show ();
	}

	//_________________ GPS

	private void registerGPS ()
	{
		Log.d ("GPS", "GPS registered");
		isUsingGPS = true;
		locationManager.requestLocationUpdates (LocationManager.GPS_PROVIDER,
				GPS_MIN_TIME_UPDATE, MIN_DISTANCE_UPDATE, this);
	}

	private void registerNetwork ()
	{
		Log.d ("GPS", "Network registered");
		isUsingNetwork = true;
		locationManager.requestLocationUpdates (LocationManager.NETWORK_PROVIDER,
				NETWORK_MIN_TIME_UPDATE, MIN_DISTANCE_UPDATE, this);
	}

	private void unregisterLocation ()
	{
		Log.d ("GPS", "unregistered");
		isUsingGPS = isUsingNetwork = false;
		isFirstPosition = true; // TODO: ok? next update will reposition the camera
		locationManager.removeUpdates (this);
	}

	//________ LISTENER MAP

	private OnCameraChangeListener onCameraChangeListener =
			new OnCameraChangeListener()
	{
		
		@Override
		public void onCameraChange (CameraPosition position)
		{
			Log.d ("GPS", "onCameraChange: " + position.target + " ; zoom: "
					+ position.zoom + " ; dist: "
					+ MapCamera.radiusDistanceCovered (map).getRadius ()
					+ " meters");
			markers.updateMarkers (false);
		}
	};

	/**
	 * 
	 */
	@Override
	public void onLocationChanged (final Location location)
	{
		if (isFirstPosition)
		{
			MapCamera.moveCamera (map, location, ZOOM_GPS);
			isFirstPosition = false;
			markers.updateMarkers (true);
		}
	}

	@Override
	public void onProviderDisabled (final String provider)
	{
		Log.d ("GPS", "provider disabled " + provider);
		// GPS no longer available
		if ((isUsingGPS && provider.equals (LocationManager.GPS_PROVIDER))
				|| (isUsingNetwork
						&& provider.equals (LocationManager.NETWORK_PROVIDER)))
			unregisterLocation ();
	}

	@Override
	public void onProviderEnabled (final String provider)
	{
		Log.d ("GPS", "provider enabled " + provider);
		// yeah, GPS is back
		if (isUsingGPS && provider.equals (LocationManager.GPS_PROVIDER))
			registerGPS ();
		else if (isUsingNetwork
				&& provider.equals (LocationManager.NETWORK_PROVIDER))
			registerNetwork ();
	}

	@Override
	public void onStatusChanged (final String provider, final int status,
			final Bundle extras)
	{
		Log.d ("GPS", "status changed " + provider + ": " + status);
	}

}
