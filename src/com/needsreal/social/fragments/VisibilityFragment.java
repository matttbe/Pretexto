package com.needsreal.social.fragments;

import java.util.ArrayList;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;

import com.needsreal.social.Needsreal;
import com.needsreal.social.R;
import com.needsreal.social.profile.Visibility;
import com.needsreal.social.ui_utils.ExpandableVisibilityListAdapter;
import com.tjerkw.slideexpandable.library.ActionSlideExpandableListView;
import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;


public class VisibilityFragment extends Fragment
{

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View V = inflater.inflate (R.layout.visibility_expandable_list,
				container, false);
		ActionSlideExpandableListView status_list = (ActionSlideExpandableListView) V
				.findViewById (R.id.visibility_expandable_list);
		
		
		status_list.setAdapter(
	            new SlideExpandableListAdapter(
	                buildVisibilityView (),
	                R.id.expandable_toggle_button,
	                R.id.expandable
	            )
	        );
		
		Button b= (Button) V.findViewById (R.id.add_vis_button);
		b.setOnClickListener (new OnClickListener()
		{
			
			@Override
			public void onClick (View v)
			{
				NewVisibilityObjectFragmentDialog newFragment = NewVisibilityObjectFragmentDialog.newInstance();
			    newFragment.show (getFragmentManager (), "LOL");

				
			}
		});

		return V;
	}

	private ListAdapter buildVisibilityView ()
	{
		ArrayList<Visibility> visibilities = Needsreal.getCurrentUser ()
				.getVisibilitiesMgr ().getAll ();
		
		String[] objects = {"T1", "T2"};
		//return new ArrayAdapter<String>(getActivity (), R.layout.visibility_item, R.id.text_vis_item, objects);
		return new ExpandableVisibilityListAdapter (getActivity(), R.layout.visibility_item, R.id.text_vis_item , visibilities);

	}

}
