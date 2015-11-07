package com.lorrypanda.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lorrypanda.activitys.HomeActivity;
import com.lorrypanda.activitys.R;
import com.lorrypanda.utils.GPSTracker;

public class PickupFragment extends Fragment{

	private GoogleMap googlemap;
   private GPSTracker gps;
	private  double latitude,longitude;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		////return super.onCreateView(inflater, container, savedInstanceState);
		setHasOptionsMenu(true);
		View someview=inflater.inflate(R.layout.fragment_pickup, container, false);
		
		 gps = new GPSTracker(getActivity());
		 
		 if(gps.canGetLocation()){
             
              latitude = gps.getLatitude();
             longitude = gps.getLongitude();
             
             showMap();
              
         }else{
            
             gps.showSettingsAlert();
             
            
         }
		 
		 
		 
		 
		 
		


		
		return someview;
	}
	
@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// TODO Auto-generated method stub
	super.onCreateOptionsMenu(menu, inflater);
	inflater.inflate(R.menu.picup_menu, menu);
	
	
	
	
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	
	
	switch (item.getItemId()) {
	case R.id.action_search:
		
		
		enterSearchText();
		
		
		break;

	default:
		break;
	}
	
	return super.onOptionsItemSelected(item);	
	
	
}




public void showMap(){
	googlemap = ((MapFragment) getChildFragmentManager().findFragmentById(
			R.id.mapfragment)).getMap();

	googlemap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	googlemap.setMyLocationEnabled(true);
	googlemap.getUiSettings().setZoomControlsEnabled(true);
	googlemap.getUiSettings().setMyLocationButtonEnabled(false);
	googlemap.getUiSettings().setCompassEnabled(true);
	
	CameraPosition cameraPosition = new CameraPosition.Builder()
	.target(new LatLng(latitude, longitude)).zoom(15).build();

	googlemap.animateCamera(CameraUpdateFactory
			.newCameraPosition(cameraPosition));

	MarkerOptions marker = new MarkerOptions();
	//marker.title("Naresh i technologies ");
	marker.position(new LatLng(latitude,longitude));
	marker.icon(BitmapDescriptorFactory
			.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
	googlemap.addMarker(marker);
}

public void enterSearchText(){
	
	LayoutInflater li = LayoutInflater.from(getActivity());
	View promptsView = li.inflate(R.layout.prompt, null);

	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			getActivity());

	
	alertDialogBuilder.setView(promptsView);

	final EditText userInput = (EditText) promptsView
			.findViewById(R.id.editTextDialogUserInput);

	
	alertDialogBuilder.setCancelable(false);
	alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
			
			
			
		}
	});
	alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
		}
	});
	
	
	AlertDialog alertDialog = alertDialogBuilder.create();
	alertDialog.show();
	
}


}
