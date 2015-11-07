package com.lorrypanda.activitys;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.lorrypanda.connections.ResultListener;
import com.lorrypanda.connections.SingletonClass;
import com.lorrypanda.models.CommonResponse;
import com.lorrypanda.utils.Util;

public class LoginActivity extends Activity implements OnClickListener, ResultListener<String>, ConnectionCallbacks, OnConnectionFailedListener {

	private EditText email, password;
	private Button login;
	SignInButton login_with_google;
	private TextView forgotpassword;
	private SingletonClass singleton;
	private int status = 0;
	private ConnectionResult mConnectionResult;
	private ProgressDialog progressDialog;
	private GoogleApiClient mGoogleApiClient;


	private boolean mIntentInProgress;
	private boolean mSignInClicked;
	private static final int RC_SIGN_IN = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		singleton = new SingletonClass(this);
		View header = findViewById(R.id.header_lauout);

		TextView headerText = (TextView) header.findViewById(R.id.screen_title);
		headerText.setText("LOGIN");
		
		mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Plus.API, Plus.PlusOptions.builder().build()).addScope(Plus.SCOPE_PLUS_LOGIN).build();
		  
		email = (EditText) findViewById(R.id.email_edit);

		password = (EditText) findViewById(R.id.password_edit);

		login = (Button) findViewById(R.id.login_btn);
		login_with_google = (SignInButton) findViewById(R.id.login_with_google);
		forgotpassword = (TextView) findViewById(R.id.text_forgot_password);
		login.setOnClickListener(this);
		login_with_google.setOnClickListener(this);
		forgotpassword.setOnClickListener(this);
	}
	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}
	
	private void resolveSignInError() {
		if (mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;
				mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
			} catch (SendIntentException e) {
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.login_btn:

			if (TextUtils.isEmpty(email.getText().toString())) {

				email.setError("Please Enter Email Id");
				email.requestFocus();

			} else if (TextUtils.isEmpty(password.getText().toString())) {

				email.setError("Please Enter Password");
				email.requestFocus();

			} else {
				status = 0;
				
				progressDialog = new ProgressDialog(LoginActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
				progressDialog.setMessage("Please wait for a moment...");
				progressDialog.setCancelable(false);
				progressDialog.show();
				
				RequestParams params = new RequestParams();
				params.add("username", email.getText().toString().trim());
				params.add("password", password.getText().toString().trim());
				singleton.doLogin(params);

			}

			break;
		case R.id.text_forgot_password:
			status = 1;
			
			if (TextUtils.isEmpty(email.getText().toString())) {

				email.setError("Please Enter Email Id");
				email.requestFocus();

			}else {
				
			progressDialog = new ProgressDialog(LoginActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
			progressDialog.setMessage("Please wait for a moment...");
			progressDialog.setCancelable(false);
			progressDialog.show();
			RequestParams params = new RequestParams();
			params.add("email", email.getText().toString().trim());
			singleton.doForgotpassword(params);
			}
			break;
		
			case R.id.login_with_google:
				
				signInWithGplus();
				
				Toast.makeText(getApplicationContext(), "connecting... ", 5000).show();
				
				break;
			
		default:
			break;
		}

	}

	@Override
	public void onSuccess(String result) {
		progressDialog.dismiss();
		Gson googleJson = new Gson();
		
		//Util.showMessage(getApplicationContext(), result);
		
		Log.v("Today Response:-", result);
		
		System.out.println("----response-------------"+result);
		
		CommonResponse commonResponse = googleJson.fromJson(result, CommonResponse.class);
		
		
		if (status == 0) {

			

		if(commonResponse.getCode()==0){
			
			
			
			Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
		}else {
			Util.showMessage(getApplicationContext(),commonResponse.getMessage());
		}
			
			
			

		} else if (status == 1) {

			if(commonResponse.getCode()==0){
				
				Util.showMessage(getApplicationContext(),commonResponse.getMessage());	
				
				
			}else {
				Util.showMessage(getApplicationContext(),commonResponse.getMessage());
			}
			
			
			
		}

	}

	@Override
	public void onFailure(Throwable e) {
		progressDialog.dismiss();
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		mSignInClicked = false;
		Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

		// Get user's information
		getProfileInformation();

		// Update the UI after signin
		
		
	}

	private void getProfileInformation() {
		
		

		try {
			if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
				Person currentPerson = Plus.PeopleApi
						.getCurrentPerson(mGoogleApiClient);
				String personName = currentPerson.getDisplayName();
				String personPhotoUrl = currentPerson.getImage().getUrl();
				String personGooglePlusProfile = currentPerson.getUrl();
				String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

				
				Toast.makeText(getApplicationContext(),
						"Person information is "+personName, Toast.LENGTH_LONG).show();
				
				
				/*Log.e(TAG, "Name: " + personName + ", plusProfile: "
						+ personGooglePlusProfile + ", email: " + email
						+ ", Image: " + personPhotoUrl);
*/
			

				// by default the profile url gives 50x50 px image only
				// we can replace the value with whatever dimension we want by
				// replacing sz=X
			/*	personPhotoUrl = personPhotoUrl.substring(0,
						personPhotoUrl.length() - 2)
						+ PROFILE_PIC_SIZE;

				new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);
*/
			} else {
				Toast.makeText(getApplicationContext(),
						"Person information is null", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
	}
	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		mGoogleApiClient.connect();
		///updateUI(false);
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		if (!result.hasResolution()) {
			GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
					0).show();
			return;
		}

		if (!mIntentInProgress) {
			// Store the ConnectionResult for later usage
			mConnectionResult = result;

			if (mSignInClicked) {
				// The user has already clicked 'sign-in' so we attempt to
				// resolve all
				// errors until the user is signed in, or they cancel.
				///resolveSignInError();
			}
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			if (responseCode != RESULT_OK) {
				mSignInClicked = false;
			}

			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
			}
		}
	}
   
   private void signInWithGplus() {
	   
	   if (!mGoogleApiClient.isConnecting()) {
			mSignInClicked = true;
			resolveSignInError();
		}
	}
}
