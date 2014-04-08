package com.needsreal.social.activities;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.needsreal.social.R;
import com.needsreal.social.search.SearchItem;


public class MapMainActivity extends FragmentActivity implements LocationListener
{
	private LocationManager locationManager;
	private GoogleMap map;



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

		map.setBuildingsEnabled (true);
		map.setMyLocationEnabled (true);

		SearchItem testSearchItem = new SearchItem (0, 50.6684991, 4.621698,
				"Beau gosse", "Viens me rencontrer, moi, Julien le beau gosse");
		testSearchItem.addMarkerToMap(map);
		//.icon (BitmapDescriptorFactory.defaultMarker (BitmapDescriptorFactory.HUE_AZURE))
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
		else
			Toast.makeText (this, "GPS not available", Toast.LENGTH_LONG).show ();
	}

	@Override
	public void onPause ()
	{
		super.onPause ();

		unregisterGPS ();
	}

	private void registerGPS ()
	{
		locationManager.requestLocationUpdates (LocationManager.GPS_PROVIDER,
				5000, 10, this);
	}

	private void unregisterGPS ()
	{
		locationManager.removeUpdates (this);
	}

	@Override
	public void onLocationChanged (final Location location)
	{
		//Mise à jour des coordonnées
		final LatLng latLng = new LatLng (location.getLatitude (),
				location.getLongitude ());
		map.moveCamera (CameraUpdateFactory.newLatLngZoom (latLng, 15));

	}

	@Override
	public void onProviderDisabled (final String provider)
	{
		// GPS no longer available
		if (provider.equals ("gps"))
			unregisterGPS ();
	}

	@Override
	public void onProviderEnabled (final String provider)
	{
		// yeah, GPS is back
		if (provider.equals ("gps"))
			registerGPS ();
	}

	@Override
	public void onStatusChanged (final String provider, final int status,
			final Bundle extras)
	{}

}
