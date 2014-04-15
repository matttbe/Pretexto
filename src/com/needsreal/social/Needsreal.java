package com.needsreal.social;

import android.content.Context;
import android.content.SharedPreferences;

import com.needsreal.social.profile.VisibilitiesMgr;
import com.needsreal.social.search.StaticCategories;


public class Needsreal
{
	private static StaticCategories categories;
	private static VisibilitiesMgr visibilities;
	private static SharedPreferences settings;

	private static boolean bInit = false;

	public static final String PREFS_NAME_GLOBAL = "GLOBAL";
	public static final String PREFS_KEY_VISIBILITIES = "Visibilities";
	public static final String PREFS_KEY_CURRENT_GLOBAL_VIS = "CurrGlobVis";

	// can't be instantiate
	private Needsreal () {}

	/**
	 * Init some objects where a context is needed for the translations, etc.
	 * @param context of the application
	 */
	public static void init (Context context)
	{
		if (bInit)
			return;

		bInit = true;
		settings = context.getSharedPreferences (PREFS_NAME_GLOBAL,
				Context.MODE_PRIVATE);
		categories = new StaticCategories (context);
		visibilities = new VisibilitiesMgr (context, settings);
	}

	public static StaticCategories getCategories ()
	{
		return categories;
	}

	public static VisibilitiesMgr getVisibilitiesMgr ()
	{
		return visibilities;
	}
}
