package com.needsreal.social.ui_utils;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ExpandableVisibilityListAdapter extends ArrayAdapter<Map<String,Map<String,Boolean>>>
{

	public ExpandableVisibilityListAdapter (Context context, int resource,
			int textViewResourceId,
			Map<String, Map<String, Boolean>> values)
	{
		
		super (context, resource, textViewResourceId, (Map<String, Map<String, Boolean>>[]) values.values ().toArray ());
		
	}

}
