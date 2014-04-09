package com.needsreal.social.search;

import com.needsreal.social.R;

import android.content.Context;

public class StaticCategories
{
	private static Category[] categories = new Category[] {
			new Category (0, "Other", "No category"),
			new Category (1, "Sport", "All things about sport") };

	// TODO: init
	public StaticCategories (Context context)
	{
		int i = 0;
		categories = new Category[10];

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
