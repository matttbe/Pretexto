package com.needsreal.social.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.needsreal.social.R;


public class SearchFragment extends Fragment
{

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		final View V = inflater.inflate (R.layout.search_fragment, container,
				false);
		SearchView SV = (SearchView) V.findViewById (R.id.search_view_1);

		SV.setOnQueryTextFocusChangeListener (new View.OnFocusChangeListener ()
		{

			@Override
			public void onFocusChange (View v, boolean hasFocus)
			{
				if (hasFocus)
				{
					FragmentManager fm = getFragmentManager ();
					fm.beginTransaction ()
							.setCustomAnimations (android.R.animator.fade_in,
									android.R.animator.fade_out)
							.show (getFragmentManager ().findFragmentById (
									R.id.searchblock)).commit ();
				}
				else
				{
					FragmentManager fm = getFragmentManager ();
					fm.beginTransaction ()
							.setCustomAnimations (android.R.animator.fade_in,
									android.R.animator.fade_out)
							.hide (getFragmentManager ().findFragmentById (
									R.id.searchblock)).commit ();
				}
			}

		});
		// Inflate the layout for this fragment
		return V;
	}

}
