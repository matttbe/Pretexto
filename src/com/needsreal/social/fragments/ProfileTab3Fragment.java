package com.needsreal.social.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.needsreal.social.R;


public class ProfileTab3Fragment extends Fragment
{


	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate (R.layout.profile_tab3, container, false);
	}
	
	@Override
	public String toString(){
		return getString (R.string.profile_tab3);
	}
}
