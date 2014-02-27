package com.needsreal.social.activities;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.needsreal.social.R;


public class MapMainActivity extends Activity implements LocationListener
{

	private LocationManager locationManager;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);

		// Windows properties
		requestWindowFeature (Window.FEATURE_NO_TITLE);
		getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView (R.layout.activity_map_main);

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

		// On appelle la méthode pour se désabonner
		desabonnementGPS ();
	}

	/**
	 * Méthode permettant de s'abonner à la localisation par GPS.
	 */
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
