package com.lorrypanda.activitys;

import java.util.ArrayList;

import com.lorrypanda.adapter.NavDrawerListAdapter;
import com.lorrypanda.fragments.PickupFragment;
import com.lorrypanda.models.NavDrawerItem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends Activity {

            private DrawerLayout mDrawerLayout;
        	private ListView mDrawerList;
        	private ActionBarDrawerToggle mDrawerToggle;

        	private CharSequence mDrawerTitle;

        	private CharSequence mTitle;

        	private String[] navMenuTitles;
        	private TypedArray navMenuIcons;
        	private String navMenusubTitle = "Home";
        	private ArrayList<NavDrawerItem> navDrawerItems;
        	private NavDrawerListAdapter adapter;
        	
        	TextView mTitleTextView;
        	ActionBar actionBar;
        	@Override
        	protected void onCreate(Bundle savedInstanceState) {
        		super.onCreate(savedInstanceState);
        		setContentView(R.layout.activity_main);
        		  actionBar = getActionBar();
        		
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
        				.permitAll().build();

        		StrictMode.setThreadPolicy(policy);
        		
     
        		mTitle = mDrawerTitle = getTitle();
        		
        		// load slide menu items
        		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        		// nav drawer icons from resources
        		navMenuIcons = getResources()
        				.obtainTypedArray(R.array.nav_drawer_icons);

        		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        		navDrawerItems = new ArrayList<NavDrawerItem>();

        		// adding nav drawer items to array
        		
        		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
        				.getResourceId(0, -1), navMenusubTitle));
        		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
        				.getResourceId(1, -1)));
        		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
        				.getResourceId(2, -1)));
        		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
        				.getResourceId(3, -1)));
        		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
        				.getResourceId(4, -1)));
        		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
        				.getResourceId(5, -1)));
        		

        		
        		navMenuIcons.recycle();

        		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        		
        		adapter = new NavDrawerListAdapter(getApplicationContext(),
        				navDrawerItems);
        		mDrawerList.setAdapter(adapter);

        		
        		getActionBar().setDisplayHomeAsUpEnabled(true);
        		getActionBar().setHomeButtonEnabled(true);

        		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
        				R.drawable.ic_menu, // nav menu toggle icon
        				R.string.app_name, // nav drawer open - description for
        									// accessibility
        				R.string.app_name // nav drawer close - description for
        									// accessibility
        		) {
        			public void onDrawerClosed(View view) {
        				
        				
        				getActionBar().setTitle(mTitle);
        				//actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>DOOTHA</font>"));
        				// calling onPrepareOptionsMenu() to show action bar icons
        				invalidateOptionsMenu();
        			}

        			public void onDrawerOpened(View drawerView) {
        				
        			
        		getActionBar().setTitle(mDrawerTitle);
        				//actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>DOOTHA</font>"));
        				// calling onPrepareOptionsMenu() to hide action bar icons
        				invalidateOptionsMenu();
        			}
        		};
        		mDrawerLayout.setDrawerListener(mDrawerToggle);

        		if (savedInstanceState == null) {
        			// on first time display view for first nav item
        			displayView(1);
        		}
        	}

        	/**
        	 * Slide menu item click listener
        	 * */
        	private class SlideMenuClickListener implements
        			ListView.OnItemClickListener {
        		@Override
        		public void onItemClick(AdapterView<?> parent, View view, int position,
        				long id) {
        			// display view for selected nav drawer item
        			displayView(position);
        		}
        	}

        	@Override
        	public boolean onCreateOptionsMenu(Menu menu) {
        		// getMenuInflater().inflate(R.menu.items, menu);
        		/* MenuInflater inflater = getMenuInflater();
                 inflater.inflate(R.menu.activity_main_action_bar, menu);
        		
                 if (true)
                 {
                     for (int i = 0; i < menu.size(); i++)
                         menu.getItem(i).setVisible(false);
                 }*/
             
        		
        		return true;
        	}

        	@Override
        	public boolean onOptionsItemSelected(MenuItem item) {
        		// toggle nav drawer on selecting action bar app icon/title
        		if (mDrawerToggle.onOptionsItemSelected(item)) {
        			return true;
        		}
        		// Handle action bar actions click
        		switch (item.getItemId()) {

        		
        		//
        		default:
        			return super.onOptionsItemSelected(item);
        		}

        		//return true;
        	}

        	/* *
        	 * Called when invalidateOptionsMenu() is triggered
        	 */
        	@Override
        	public boolean onPrepareOptionsMenu(Menu menu) {
        		// if nav drawer is opened, hide the action items
        		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        		// menu.findItem(R.id.search).setVisible(!drawerOpen);
        		return super.onPrepareOptionsMenu(menu);
        	}

        	/**
        	 * Diplaying fragment view for selected nav drawer list item
        	 * */
        	private void displayView(int position) {
        		// update the main content by replacing fragments
        		Fragment fragment = null;
        		switch (position) {
        		case 0:
        		//	fragment = new PickupFragment();
        			break;
        		case 1:
        			fragment = new PickupFragment();

        			break;
        		
        		case 2:
        			
        			break;
        		case 3:
        			
        			break;
        		case 4:
        			
        			break;
        		case 5:
        			
        			
        			break;

        		default:
        			break;
        		}

        		if (fragment != null) {
        			FragmentManager fragmentManager = getFragmentManager();
        			fragmentManager.beginTransaction()
        					.replace(R.id.frame_container, fragment).commit();

        			// update selected item and title, then close the drawer
        			mDrawerList.setItemChecked(position, true);
        			mDrawerList.setSelection(position);

        			 //setTitle(navMenuTitles[position]);
        			 actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>PANDA</font>"));
        			mDrawerLayout.closeDrawer(mDrawerList);
        		} else {
        			// error in creating fragment
        			Log.e("MainActivity", "Error in creating fragment");
        		}
        	}

        	@Override
        	public void setTitle(CharSequence title) {		
        		mTitle = title;
        		//mTitleTextView.setText(mTitle);
        		//getActionBar().setTitle(mTitle);
        	}

        	

        	@Override
        	protected void onPostCreate(Bundle savedInstanceState) {
        		super.onPostCreate(savedInstanceState);
        		// Sync the toggle state after onRestoreInstanceState has occurred.
        		mDrawerToggle.syncState();
        	}

        	@Override
        	public void onConfigurationChanged(Configuration newConfig) {
        		super.onConfigurationChanged(newConfig);
        		// Pass any configuration change to the drawer toggls
        		mDrawerToggle.onConfigurationChanged(newConfig);
        	}

        	@Override
        	public void onBackPressed() {
        		// TODO Auto-generated method stub
        		super.onBackPressed();
        		this.finish();
        		android.os.Process.killProcess(android.os.Process.myPid());
        		System.exit(0);
        	}
        }


