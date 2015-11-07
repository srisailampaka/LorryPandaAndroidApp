package com.lorrypanda.activitys;

import com.lorrypanda.database.DataManagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		(new Handler()).postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				if(DataManagement.getregistrationStatus(getApplicationContext())){
					
					Intent intent = new Intent(SplashActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
					
				}else {
					
					Intent intent = new Intent(SplashActivity.this,
							RegistrationActivity.class);
					startActivity(intent);
					
					finish();
				}
				
				
			}
		}, 3000);

	}
}
