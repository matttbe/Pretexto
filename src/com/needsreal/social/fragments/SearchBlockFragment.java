package com.needsreal.social.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.needsreal.social.R;


public class SearchBlockFragment extends Fragment
{

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View V = inflater.inflate (R.layout.search_block_fragment, container,
				false);

		ListView LV = (ListView) V.findViewById (
				R.id.previousSearchList);
		ListView LV2= (ListView) V.findViewById (
				R.id.nearestList);
		// TODO Link to DB
		String List[] = new String[9];
		List[0] = "Ju bogoss";
		for (int i = 1; i < List.length; i++)
			List[i] = "Matth - with a long line because we need a long sentence";
		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List);
		
		

		LV.setAdapter (aa);
		LV2.setAdapter (aa);
		return V;

	}

}
