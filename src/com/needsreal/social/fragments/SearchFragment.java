package com.needsreal.social.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.needsreal.social.R;

public class SearchFragment extends Fragment
{
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	final View V = inflater.inflate(R.layout.search_fragment, container, false);
    	SearchView SV = (SearchView) V.findViewById (R.id.search_view_1);
    	SV.setOnFocusChangeListener (new View.OnFocusChangeListener()
		{
			
			@Override
			public void onFocusChange (View v, boolean hasFocus)
			{
				
			}
		});
        // Inflate the layout for this fragment
        return V;
    }
    
    



}
