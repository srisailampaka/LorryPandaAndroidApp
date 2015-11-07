package com.lorrypanda.fragments;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.lorrypanda.activitys.HomeActivity;
import com.lorrypanda.activitys.LoginActivity;
import com.lorrypanda.activitys.R;
import com.lorrypanda.connections.ResultListener;
import com.lorrypanda.connections.SingletonClass;
import com.lorrypanda.models.CommonResponse;
import com.lorrypanda.models.UserProfileData;
import com.lorrypanda.utils.Util;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ProfileFragment extends Fragment implements OnClickListener,ResultListener<String> {

	
	private EditText etName, etEmail, etPhoneNumber;
	private Button update;
	private SingletonClass singleton;
	private ProgressDialog progressDialog;
	private UserProfileData userprofileData;
	private int status;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		View view=inflater.inflate(R.layout.fragment_myprofile, container, false);
		
		etName = (EditText) view.findViewById(R.id.name_edit);
		etEmail = (EditText) view.findViewById(R.id.email_edit);
		etPhoneNumber = (EditText)view.findViewById(R.id.phone_number_edit);
		
		update=(Button)view.findViewById(R.id.update_btn);
		singleton = new SingletonClass(ProfileFragment.this);
		
		
		update.setOnClickListener(this);
		
		getuserData();
		
		return view;
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.update_btn:
			
			
			
			break;

		default:
			break;
		}
		
		
	}
	
	
	public void getuserData(){
		
		progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_LIGHT);
		progressDialog.setMessage("Please wait for a moment...");
		progressDialog.setCancelable(false);
		progressDialog.show();
		status=0;
		RequestParams params = new RequestParams();
	     params.add("mobile", "");
	
		singleton.getProfileData(params);

		
	}

	@Override
	public void onSuccess(String result) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();	
		
		Gson googleJson = new Gson();
		System.out.println("----response-------------"+result);
		
		userprofileData = googleJson.fromJson(result, UserProfileData.class);
		
		if(status==0){


			

			if(userprofileData.getCode()==0){
				
				
				Util.showMessage(getActivity(),userprofileData.getMessage());	
				
				
			}else {
				Util.showMessage(getActivity(),userprofileData.getMessage());
			}
				
				
				

				
		}else if (status==1) {
			
			
			
			
			
			
		}
		
	}

	@Override
	public void onFailure(Throwable e) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		
		
		
	}
	
	
}
