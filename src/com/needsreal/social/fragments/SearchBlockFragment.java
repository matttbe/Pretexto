package com.needsreal.social.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
		String List[] = new String[2];
		List[0] = "Ju bogoss";
		List[1] = "Mathieu Baerts - Lololol";
		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List);

		LV.setAdapter (aa);
		return V;

	}

}
