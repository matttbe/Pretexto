package com.needsreal.social.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.needsreal.social.R;
import com.needsreal.social.profile.CurrentUser;
import com.needsreal.social.profile.Visibility.VisibilityObject;


public class ProfileTab1Fragment extends Fragment
{


	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate (R.layout.profile_tab1, container, false);
		CurrentUser user = CurrentUser.getInstance ();
		TextView t = (TextView) v.findViewById (R.id.profiletab_nickname_value);
		t.setText (user.getNickname ());
		t = (TextView) v.findViewById (R.id.profiletab_name_value);
		t.setText (user.getFirstname () + " " + user.getLastname ());
		t = (TextView) v.findViewById (R.id.profiletab_description_value);
		t.setText (user.getDescription ());
	
		return v;
	}
	
	@Override
	public String toString(){
		return getString (R.string.profile_tab1);
	}
}
