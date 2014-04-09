package com.needsreal.social.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.needsreal.social.R;


public class ProfileTab1Fragment extends Fragment
{


	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate (R.layout.profile_tab1, container, false);
	}
	
	@Override
	public String toString(){
		Log.w ("pret", "coucou :) ");
		Log.w ("pret", ""+getResources ());
		return getString (R.string.profile_tab1);
	}
}
