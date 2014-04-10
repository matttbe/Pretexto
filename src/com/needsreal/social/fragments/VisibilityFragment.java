package com.needsreal.social.fragments;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.needsreal.social.R;
import com.needsreal.social.ui_utils.ExpandableVisibilityListAdapter;
import com.tjerkw.slideexpandable.library.ActionSlideExpandableListView;


// Instances of this class are fragments representing a single
// object in our collection.
public class VisibilityFragment extends Fragment
{


	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View V =inflater.inflate (R.layout.visibility_expandable_list, container, false);
		ActionSlideExpandableListView status_list = (ActionSlideExpandableListView)V.findViewById(R.id.visibility_expandable_list);

		// fill the list with data
		/*status_list.setAdapter(buildDummyData());*/
		
		/*status_list.setItemActionListener(new ActionSlideExpandableListView.OnActionClickListener()
		{
			
			@Override
			public void onClick (View itemView, View clickedView, int position)
			{
				Toast.makeText (getActivity(), "Coucou", Toast.LENGTH_SHORT).show ();
				
			}
		}, R.id.specific_status_visibility_list);*/


		return V;
	}
	
	@Override
	public String toString(){
		return getString (R.string.profile_tab2);
	}
	
	

	
	public ListAdapter buildDummyData() {
		final int SIZE = 20;
		Map<String,Map<String,Boolean>> values = new HashMap<String,Map<String,Boolean>>();
		for(int i=0;i<SIZE;i++) {
			Map<String,Boolean> value = new HashMap<String,Boolean>();
			for(int j=0; j<4; j++){
				value.put ("Item "+j, true);
			}
			values.put ("Item" +1, value);
		}
		return new ExpandableVisibilityListAdapter(
				getActivity (),
				R.layout.visibility_status_list_item,
				R.id.checkedTextView1,
				values
		);
	}
}
