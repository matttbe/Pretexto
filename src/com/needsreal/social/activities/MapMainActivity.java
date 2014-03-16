package com.needsreal.social.activities;

import android.app.FragmentTransaction;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.needsreal.social.R;


public class MapMainActivity extends FragmentActivity implements LocationListener
{
	private LocationManager locationManager;
	private GoogleMap map;
	private MapFragment mMapFragment;


	
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);

		// Windows properties
		requestWindowFeature (Window.FEATURE_NO_TITLE);
		getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView (R.layout.activity_map_main);


		 mMapFragment = MapFragment.newInstance();
		 FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		 fragmentTransaction.add(R.id.map, mMapFragment);
		 fragmentTransaction.commit();
		 map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		 
		 
		
		if(map != null){
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		}



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
		{
			abonnementGPS ();
		}
	}

	@Override
	public void onPause ()
	{
		super.onPause ();


		desabonnementGPS ();
	}


	public void abonnementGPS ()
	{
		// On s'abonne
		locationManager.requestLocationUpdates (LocationManager.GPS_PROVIDER,
				5000, 10, this);
	}

	/**
	 * Méthode permettant de se désabonner de la localisation par GPS.
	 */
	public void desabonnementGPS ()
	{
		// Si le GPS est disponible, on s'y abonne
		locationManager.removeUpdates (this);
	}

	@Override
	public void onLocationChanged (final Location location)
	{
		// On affiche dans un Toat la nouvelle Localisation
		final StringBuilder msg = new StringBuilder ("lat : ");
		msg.append (location.getLatitude ());
		msg.append ("; lng : ");
		msg.append (location.getLongitude ());

		Toast.makeText (this, msg.toString (), Toast.LENGTH_SHORT).show ();
	}

	@Override
	public void onProviderDisabled (final String provider)
	{
		// Si le GPS est désactivé on se désabonne
		if ("gps".equals (provider))
		{
			desabonnementGPS ();
		}
	}

	@Override
	public void onProviderEnabled (final String provider)
	{
		// Si le GPS est activé on s'abonne
		if ("gps".equals (provider))
		{
			abonnementGPS ();
		}
	}

	@Override
	public void onStatusChanged (final String provider, final int status,
			final Bundle extras)
	{}

}
