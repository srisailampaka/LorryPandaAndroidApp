package com.lorrypanda.fragments;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.RequestParams;
import com.lorrypanda.activitys.HomeActivity;
import com.lorrypanda.activitys.LoginActivity;
import com.lorrypanda.activitys.R;
import com.lorrypanda.connections.AppUrls;
import com.lorrypanda.connections.ResultListener;
import com.lorrypanda.connections.SingletonClass;
import com.lorrypanda.connections.UrlConnection;
import com.lorrypanda.utils.GPSTracker;

public class PickupFragment extends Fragment implements ResultListener<String>{

	private GoogleMap googlemap;
   private GPSTracker gps;
	private  double latitude,longitude;
	private String response;
	private ProgressDialog progressDialog;
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
	 String[] language ={"C","C++","Java",".NET","iPhone","Android","ASP.NET","PHP"};  
	
	LayoutInflater li = LayoutInflater.from(getActivity());
	View promptsView = li.inflate(R.layout.prompt, null);

	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			getActivity());

	
	alertDialogBuilder.setView(promptsView);

	final AutoCompleteTextView userInput = (AutoCompleteTextView) promptsView
			.findViewById(R.id.editTextDialogUserInput);
	
	  userInput.setThreshold(1);
	  userInput.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
			
			
				

			String value="hyde";
	 JSONObject params=new JSONObject();
				
				progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_LIGHT);
				progressDialog.setMessage("Please wait for a moment...");
				progressDialog.setCancelable(false);
				progressDialog.show();
				
				 String url=AppUrls.GOOGLEADDRESSES_URL+"?input="+value+"&types=geocode&sensor=false&key="+AppUrls.API_KEY;
				 
					new UrlConnection(PickupFragment.this, 2, url, getActivity(), params).execute();
				
				
			
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
			
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	});
	
	  ArrayAdapter<String> adapter = new ArrayAdapter<String>  
      (getActivity(),android.R.layout.select_dialog_item,language); 
	  userInput.setAdapter(adapter);
	
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

@Override
public void onSuccess(String result) {
	// TODO Auto-generated method stub
	progressDialog.dismiss();
	 response=result;
	System.out.println("------response-------------"+result);
	
	getActivity().runOnUiThread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			Toast.makeText(getActivity(), ""+response, 5000).show();
			
		}
	});
}

@Override
public void onFailure(Throwable e) {
	// TODO Auto-generated method stub
	progressDialog.dismiss();
}


}
