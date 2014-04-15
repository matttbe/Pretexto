package com.needsreal.social.ui_utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.needsreal.social.R;
import com.needsreal.social.profile.Visibility;

public class ExpandableVisibilityListAdapter extends ArrayAdapter<Visibility>
{
	int selectedPosition = 0;

	public ExpandableVisibilityListAdapter (Context context, int resource,
			int textViewResourceId, List<Visibility> objects)
	{
		super (context, resource, textViewResourceId, objects);

	}
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View v = convertView;
        
	    if (v == null) {

	        LayoutInflater vi;
	        vi = LayoutInflater.from(getContext());
	        v = vi.inflate(R.layout.visibility_item, null);

	    }
	    
	    TextView t = (TextView) v.findViewById (R.id.text_vis_item);
	    t.setText (this.getItem (position).getName ());
	    RadioButton r = (RadioButton) v.findViewById (R.id.radioButton1);
	    r.setChecked (position==selectedPosition);
	    r.setTag (position);
	    r.setOnClickListener (new OnClickListener()
		{
			
			@Override
			public void onClick (View v)
			{
				selectedPosition = (Integer)v.getTag();
                notifyDataSetInvalidated();
			}
		});
	    
	    
	    ListView lv = (ListView) v.findViewById (R.id.specific_status_visibility_list);
	    lv.setAdapter (new SettingsAdapter (this.getContext (), R.layout.visibility_status_list_item, this.getItem (position).getSettings ()));
	    
	    
		return v;
		
	}
	

}
