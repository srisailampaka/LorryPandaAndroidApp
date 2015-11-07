package com.lorrypanda.connections;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;

public class SingletonClass {

	ResultListener<String> resultListener;

	public SingletonClass(ResultListener<String> resultListener) {
		this.resultListener = resultListener;
	}

	public void doLogin(RequestParams params) {
		LoopJHttpClient.post(AppUrls.LOGIN, params,
				new JsonHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,
							JSONObject response) {
						if(statusCode == 200)
						{
							resultListener.onSuccess(response.toString());
							AppUtils.debugOut(response.toString());
						}

					}

					public void onFailure(int statusCode, Header[] headers,
							Throwable throwable,
							JSONObject errorResponse) {
						resultListener.onFailure(throwable);
					}
				});
	}
	
	
	public void doRegistration(RequestParams params) {
		LoopJHttpClient.get(AppUrls.REGISTRATION, params,
				new JsonHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,
							JSONObject response) {
						if(statusCode == 200)
						{
							resultListener.onSuccess(response.toString());
							AppUtils.debugOut(response.toString());
						}

					}

					public void onFailure(int statusCode, Header[] headers,
							Throwable throwable,
							JSONObject errorResponse) {
						resultListener.onFailure(throwable);
					}
				});
	}
	
	
	public void doForgotpassword(RequestParams params){
	
		LoopJHttpClient.post(AppUrls.FORGOT_PASSWORD, params,
				new JsonHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,
							JSONObject response) {
						if(statusCode == 200)
						{
							resultListener.onSuccess(response.toString());
							AppUtils.debugOut(response.toString());
						}

					}

					public void onFailure(int statusCode, Header[] headers,
							Throwable throwable,
							JSONObject errorResponse) {
						resultListener.onFailure(throwable);
					}
				});
		
		
		
	}
	
	
	
	
	
	public void doChangePassword(Context context, RequestParams params)
	{
		LoopJHttpClient.get(AppUrls.CHANGE_PASSWORD, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				if(statusCode == 200)
				{
					resultListener.onSuccess(response.toString());
					AppUtils.debugOut(response.toString());
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONArray errorResponse) {
				// TODO Auto-generated method stub
				resultListener.onFailure(throwable);
			}
		});

	}
	public void deviceRegistration(Context context, RequestParams params)
	{
		LoopJHttpClient.get(AppUrls.DEVICE_REGISTRATION, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				if(statusCode == 200)
				{
					resultListener.onSuccess(response.toString());
					AppUtils.debugOut(response.toString());
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONArray errorResponse) {
				// TODO Auto-generated method stub
				resultListener.onFailure(throwable);
			}
		});

	}
	
	public void getProfileData(RequestParams params) {
		LoopJHttpClient.post(AppUrls.PROFILE_DATA, params,
				new JsonHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,
							JSONObject response) {
						if(statusCode == 200)
						{
							resultListener.onSuccess(response.toString());
							AppUtils.debugOut(response.toString());
						}

					}

					public void onFailure(int statusCode, Header[] headers,
							Throwable throwable,
							JSONObject errorResponse) {
						resultListener.onFailure(throwable);
					}
				});
	}
	
	
	
}
