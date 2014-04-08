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
		// TODO Link to DB
		String List[] = new String[9];
		List[0] = "Ju bogoss";
		List[1] = "Mathieu Baerts - Lololol";
		List[2] = "Mathieu Baerts - Lololol";
		List[3] = "Mathieu Baerts - Lololol";
		List[4] = "Mathieu Baerts - Lololol";
		List[5] = "Mathieu Baerts - Lololol";
		List[6] = "Mathieu Baerts - Lololol";
		List[7] = "Mathieu Baerts - Lololol";
		List[8] = "Mathieu Baerts - Lololol";
		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List);

		LV.setAdapter (aa);
		return V;

	}

}
