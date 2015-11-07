package com.lorrypanda.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lorrypanda.activitys.R;
import com.lorrypanda.models.NavDrawerItem;

public class NavDrawerListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private LayoutInflater mInflater;
	private ImageView imgIcon_large;
	private ImageView imgIcon;
	private TextView userName,phoneNumber;

	private TextView txtTitle;
	private LinearLayout layout_header,layout_child;
	
	public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
             mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
           
        }
         
		convertView = mInflater.inflate(R.layout.drawerlist, null);
		
		layout_header=(LinearLayout)convertView.findViewById(R.id.layout_header);
		layout_child=(LinearLayout)convertView.findViewById(R.id.layout_child);
		
		
		 imgIcon_large = (ImageView) convertView.findViewById(R.id.image1);
		 userName = (TextView) convertView.findViewById(R.id.userName);
		 phoneNumber = (TextView) convertView.findViewById(R.id.phoneNumber);
		 
         imgIcon = (ImageView) convertView.findViewById(R.id.icon);
         txtTitle = (TextView) convertView.findViewById(R.id.title);
         
        

		if(position==0)
		{
			layout_header.setVisibility(View.VISIBLE);
			layout_child.setVisibility(View.GONE);
			
	        
			imgIcon_large.setImageResource(R.drawable.ic_launcher);
			userName.setText("Colorpix");
			phoneNumber.setText("99924892348");
			
				
		}else{
        
			layout_header.setVisibility(View.GONE);
			layout_child.setVisibility(View.VISIBLE);
			
	  		imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
	  		txtTitle.setText(navDrawerItems.get(position).getTitle());
        
		}
        
        
        return convertView;
	}

}
