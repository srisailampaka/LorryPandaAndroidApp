package com.lorrypanda.fragments;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.lorrypanda.activitys.HomeActivity;
import com.lorrypanda.activitys.LoginActivity;
import com.lorrypanda.activitys.R;
import com.lorrypanda.connections.ResultListener;
import com.lorrypanda.connections.SingletonClass;
import com.lorrypanda.models.CommonResponse;
import com.lorrypanda.utils.Util;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordFragment extends Fragment implements ResultListener<String>, OnClickListener{

	
	private EditText temp_pass,new_pass,confirm_pass;
	private SingletonClass singleton;
	private Button submit;
	private ProgressDialog progressDialog;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		View someview=inflater.inflate(R.layout.fragment_change_password, container, false);
		
		temp_pass=(EditText)someview.findViewById(R.id.temp_pass);
		new_pass=(EditText)someview.findViewById(R.id.new_pass);
		confirm_pass=(EditText)someview.findViewById(R.id.confirm_pass);
		submit=(Button)someview.findViewById(R.id.submit_btn);
		singleton = new SingletonClass(this);
		submit.setOnClickListener(this);
		
		
	return 	someview;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	
	
	
	
	}
	@Override
	public void onSuccess(String result) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		

       Gson googleJson = new Gson();
		
		//Util.showMessage(getApplicationContext(), result);
		
		Log.v("Today Response:-", result);
		
		System.out.println("----response-------------"+result);
		
		CommonResponse commonResponse = googleJson.fromJson(result, CommonResponse.class);
		
		
			

			if(commonResponse.getCode()==0){
				
				Util.showMessage(getActivity(),commonResponse.getMessage());
			}else {
				Util.showMessage(getActivity(),commonResponse.getMessage());
			}
		
		
	}
	@Override
	public void onFailure(Throwable e) {
		// TODO Auto-generated method stub
		
		progressDialog.dismiss();
		
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.submit_btn:
			
			progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_LIGHT);
			progressDialog.setMessage("Please wait for a moment...");
			progressDialog.setCancelable(false);
			progressDialog.show();
			
			RequestParams params = new RequestParams();
			params.add("mobile", temp_pass.getText().toString().trim());
			params.add("new_pass", new_pass.getText().toString().trim());
			params.add("con_pass", confirm_pass.getText().toString().trim());
			
			singleton.doChangePassword(getActivity(), params);
			break;

		default:
			break;
		}
		
		
		
	}
	
}
