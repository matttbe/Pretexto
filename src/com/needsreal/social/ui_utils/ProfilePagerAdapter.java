package com.needsreal.social.ui_utils;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.needsreal.social.R;


public class ProfilePagerAdapter extends FragmentPagerAdapter
{

	private final List<Fragment> fragments;
	private final Context context;

	// On fournit à l'adapter la liste des fragments à afficher
	public ProfilePagerAdapter (FragmentManager fm, Context nContext,
			List<Fragment> fragments)
	{
		super (fm);
		this.fragments = fragments;
		this.context = nContext;
	}

	@Override
	public Fragment getItem (int position)
	{
		return this.fragments.get (position);
	}

	@Override
	public int getCount ()
	{
		return this.fragments.size ();
	}

	@Override
	public CharSequence getPageTitle (int position)
	{
		switch (position)
		{
			case 0:
				return context.getString (R.string.profile_tab1);
			case 1:
				return context.getString (R.string.profile_tab2);
			case 2:
				return context.getString (R.string.profile_tab3);
			default:
				return "" + position;
		}
	}
}
