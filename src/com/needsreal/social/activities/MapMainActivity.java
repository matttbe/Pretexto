package com.needsreal.social.activities;

import android.app.FragmentManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.needsreal.social.R;
import com.needsreal.social.search.SearchItem;


public class MapMainActivity extends FragmentActivity implements LocationListener
{
	private LocationManager locationManager;
	private GoogleMap map;

	private boolean isUsingGPS = false;
	private boolean isUsingNetwork = false;

	private static final int GPS_MIN_TIME_UPDATE = 2 * 60 * 1000; // 2min
	private static final int NETWORK_MIN_TIME_UPDATE = 5 * 60 * 1000; // 5min
	private static final int MIN_DISTANCE_UPDATE = 10; // 10 meters
	private static final float ZOOM_INIT = 13;
	private static final float ZOOM_GPS = 14;

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

		FragmentManager fm = getFragmentManager ();
		fm.beginTransaction ()
				.hide (getFragmentManager ()
						.findFragmentById (R.id.searchblock)).commit ();

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
			moveCamera (lastKnowLocation, ZOOM_INIT);
			updateMarkers (lastKnowLocation, true);
		}
	}

	@Override
	public void onPause ()
	{
		super.onPause ();
		unregisterLocation ();
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
		locationManager.removeUpdates (this);
	}

	//________________ Utils

	/**
	 * Move camera to a location with a custom zoom
	 * @param location the center position
	 * @param zoom between 2 and 21, see CameraUpdateFactory.newLatLngZoom
	 */
	private void moveCamera (final Location location, float zoom)
	{
		final LatLng latLng = new LatLng (location.getLatitude (),
				location.getLongitude ());
		Log.d ("GPS", "move camera to " + latLng.latitude + " " + latLng.longitude);
		map.moveCamera (CameraUpdateFactory.newLatLngZoom (latLng, zoom));
	}

	/**
	 * Update the list of markers in the list
	 * @param bForceUpdate
	 */
	private void updateMarkers (Location location, boolean bForceUpdate)
	{
		// TODO
		SearchItem testSearchItem = new SearchItem (0, 50.6684991, 4.621698,
				"Beau gosse", "Viens me rencontrer, moi, Julien le beau gosse");

		testSearchItem.addMarkerToMap (map);
		// .icon (BitmapDescriptorFactory.defaultMarker
		// (BitmapDescriptorFactory.HUE_AZURE))
	}

	//________ LISTENER MAP

	private OnCameraChangeListener onCameraChangeListener =
			new OnCameraChangeListener()
	{
		
		@Override
		public void onCameraChange (CameraPosition position)
		{
			// TODO Auto-generated method stub
			
		}
	};

	/**
	 * 
	 */
	@Override
	public void onLocationChanged (final Location location)
	{
		moveCamera (location, ZOOM_GPS);
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
