package com.needsreal.social.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.android.gms.maps.model.LatLng;


public class CurrentUser extends AbstractUser
{
	// Singleton stuff
	private static CurrentUser sInstance;
	public static CurrentUser getInstance () { return sInstance; }
	public static void init (Context context)
	{
		sInstance = new CurrentUser (context);
	}

	/*
	 * We need to save only a token given by the server to not save the
	 * password in the settings files
	 */
	private String token;
	private boolean hasLogin;

	private LatLng lastPosition;

	private VisibilitiesMgr visibilities;
	private SharedPreferences prefs;

	private static final String PREFS_NAME_USER = "User";
	private static final String PREFS_KEY_TOKEN = "token";
	private static final String PREFS_KEY_HASH = "hash";
	private static final String PREFS_KEY_NICKNAME = "nick";
	private static final String PREFS_KEY_FIRSTNAME = "first";
	private static final String PREFS_KEY_LASTNAME = "last";
	private static final String PREFS_KEY_SHORTDESC = "shortDesc";
	private static final String PREFS_KEY_DESC = "desc";
	private static final String PREFS_KEY_ACTIPRO = "activityPro";
	private static final String PREFS_KEY_POSTCODE = "post";
	private static final String PREFS_KEY_COUNTRY = "country";
	// private static final String PREFS_KEY_INTERESTS = "interests";
	// private static final String PREFS_KEY_NEEDS = "needs";
	// private static final String PREFS_KEY_CONTACTS = "contacts";
	private static final String PREFS_KEY_LATITUDE = "lat";
	private static final String PREFS_KEY_LONGITUDE = "long";

	public CurrentUser (Context context)
	{
		super ();
		prefs = context.getSharedPreferences (PREFS_NAME_USER,
				Context.MODE_PRIVATE);
		hasLogin = prefs.contains (PREFS_KEY_HASH) &&
				prefs.contains (PREFS_KEY_TOKEN);

		visibilities = new VisibilitiesMgr (context);

		// load settings if we have a login
		if (hasLogin)
			loadPrefs ();
	}

	//_________________________________ DATABASE

	/**
	 * @return true if we already have the informations about the user
	 */
	public boolean hasLoginInfo ()
	{
		return hasLogin;
	}

	private void loadPrefs ()
	{
		token = prefs.getString (PREFS_KEY_TOKEN, null);
		hash = prefs.getString (PREFS_KEY_HASH, null);
		nickname = prefs.getString (PREFS_KEY_NICKNAME, null);
		firstname = prefs.getString (PREFS_KEY_FIRSTNAME, null);
		lastname = prefs.getString (PREFS_KEY_LASTNAME, null);
		shortDesc = prefs.getString (PREFS_KEY_SHORTDESC, null);
		description = prefs.getString (PREFS_KEY_DESC, null);
		activityPro = prefs.getString (PREFS_KEY_ACTIPRO, null);
		postcode = prefs.getInt (PREFS_KEY_POSTCODE, 0);
		country = prefs.getInt (PREFS_KEY_COUNTRY, 0);
		// interests = prefs.getStringSet (PREFS_KEY_INTERESTS, null); // TODO
		// needs = prefs.getStringSet (PREFS_KEY_NEEDS, null); // TODO
		// contacts = prefs.getStringSet (PREFS_KEY_CONTACTS, null); // TODO
		float latitude = prefs.getFloat (PREFS_KEY_LATITUDE, 0);
		float longitude = prefs.getFloat (PREFS_KEY_LONGITUDE, 0);
		lastPosition = new LatLng (latitude, longitude);

		visibilities.init ();
	}

	public void savePrefs ()
	{
		Editor editor = prefs.edit ();
		editor.putString (PREFS_KEY_TOKEN, token);
		editor.putString (PREFS_KEY_HASH, hash);
		editor.putString (PREFS_KEY_NICKNAME, nickname);
		editor.putString (PREFS_KEY_FIRSTNAME, firstname);
		editor.putString (PREFS_KEY_LASTNAME, lastname);
		editor.putString (PREFS_KEY_SHORTDESC, shortDesc);
		editor.putString (PREFS_KEY_DESC, description);
		editor.putString (PREFS_KEY_ACTIPRO, activityPro);
		editor.putInt (PREFS_KEY_POSTCODE, postcode);
		editor.putInt (PREFS_KEY_COUNTRY, country);
		// editor.putStringSet (PREFS_KEY_INTERESTS, null); // TODO
		// editor.putStringSet (PREFS_KEY_NEEDS, null); // TODO
		// editor.putStringSet (PREFS_KEY_CONTACTS, null); // TODO
		editor.putFloat (PREFS_KEY_LATITUDE, (float) lastPosition.latitude);
		editor.putFloat (PREFS_KEY_LONGITUDE, (float) lastPosition.longitude);
		editor.apply ();
	}

	//____________________________ SERVER

	/**
	 * Login to the server
	 * @param nickname nickname of the user
	 * @param password password of the user
	 * @return true if it's successfully logged.
	 */
	public boolean login (String nickname, String password)
	{
		// TODO: server

		token = "azertyuiop123456789";
		hash = "azertyuiop";
		this.nickname = nickname;

		Editor editor = prefs.edit ();
		editor.putString (PREFS_KEY_TOKEN, token);
		editor.putString (PREFS_KEY_HASH, hash);
		editor.putString (PREFS_KEY_NICKNAME, nickname);
		editor.apply ();

		// TODO: load settings from the server

		return true;
	}

	public void logout ()
	{
		Editor editor = prefs.edit ();
		editor.clear ();
		editor.apply ();

		visibilities.clear ();

		token = null;
		hash = null;
		nickname = null;
		firstname = null;
		lastname = null;
		shortDesc = null;
		description = null;
		activityPro = null;
		postcode = 0;
		country = 0;
		interests = null;
		needs = null;
		contacts = null;
		lastPosition = null;
		hasLogin = false;
	}

	//_____________________________ GETTER/SETTERS

	@Override
	public LatLng getPosition ()
	{
		return lastPosition;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition (LatLng position)
	{
		this.lastPosition = position;
	}

	public VisibilitiesMgr getVisibilitiesMgr ()
	{
		return visibilities;
	}

}
