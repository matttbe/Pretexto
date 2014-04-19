package com.needsreal.social.ui_utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;

import com.needsreal.social.R;
import com.needsreal.social.profile.Visibility.VisibilityObject;


public class SettingsAdapter extends ArrayAdapter<VisibilityObject>
{
	
	public SettingsAdapter (Context context, int visibilityStatusListItem,
			VisibilityObject[] settings)
	{
		super(context, visibilityStatusListItem, settings);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View v = convertView;

	    if (v == null) {

	        LayoutInflater vi;
	        vi = LayoutInflater.from(getContext());
	        v = vi.inflate(R.layout.visibility_status_list_item, null);

	    }
	    
	    Switch s = (Switch) v.findViewById (R.id.switch1);
	    s.setText (this.getItem (position).field.getName ());
	    s.setChecked (this.getItem (position).visible);
	    
	    return v;
	}

}
