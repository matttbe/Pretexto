package com.needsreal.social.search;

import com.needsreal.social.R;

import android.content.Context;

public class StaticCategories
{
	// Singleton stuff
	private static StaticCategories sInstance;
	public static StaticCategories getInstance () { return sInstance; }
	public static void init (Context context)
	{
		sInstance = new StaticCategories (context);
	}

	private static Category[] categories;

	public StaticCategories (Context context)
	{
		int i = 0;
		categories = new Category[3];

		categories[i] = new Category (i++,
				context.getString (R.string.cat_other),
				context.getString (R.string.cat_other_desc));
		categories[i] = new Category (i++,
				context.getString (R.string.cat_sports),
				context.getString (R.string.cat_sports_desc));
		categories[i] = new Category (i++,
				context.getString (R.string.cat_music),
				context.getString (R.string.cat_music_desc));

		// TODO: all categories
	}

	/**
	 * Get the static list of categories (with the translation)
	 * @return
	 */
	public static Category[] getCategories ()
	{
		return categories;
	}
}
