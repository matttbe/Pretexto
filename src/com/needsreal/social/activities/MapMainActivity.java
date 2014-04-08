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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.needsreal.social.R;
import com.needsreal.social.fragments.SearchBlockFragment;
import com.needsreal.social.search.SearchItem;


public class MapMainActivity extends FragmentActivity implements
		LocationListener
{
	private LocationManager locationManager;
	private GoogleMap map;
	private SearchBlockFragment sblock;
	private Marker meMarker;

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
		map.addMarker (testSearchItem.getMarkerOptions ());

		meMarker = map.addMarker (new MarkerOptions ()
				.title ("Vous êtes ici")
				.position (new LatLng (0, 0))
				.visible (false)
				.icon (BitmapDescriptorFactory
						.defaultMarker (BitmapDescriptorFactory.HUE_AZURE)));

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

		// Mise à jour des coordonnées
		final LatLng latLng = new LatLng (location.getLatitude (),
				location.getLongitude ());
		map.moveCamera (CameraUpdateFactory.newLatLngZoom (latLng, 15));
		meMarker.setPosition (latLng);
		meMarker.setVisible (true);

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
