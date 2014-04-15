package com.needsreal.social;

import android.content.Context;

import com.needsreal.social.profile.VisibilitiesMgr;
import com.needsreal.social.search.StaticCategories;


public class Needsreal
{
	private static StaticCategories categories;
	private static VisibilitiesMgr visibilities;
	private static boolean bInit = false;

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
		categories = new StaticCategories (context);
		visibilities = new VisibilitiesMgr (context);
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
