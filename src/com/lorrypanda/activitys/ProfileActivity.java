package com.lorrypanda.activitys;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends Activity implements OnClickListener{

	
	private EditText name,email,phoneNumber;
	private Button updateProfile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		 View header=findViewById(R.id.header_lauout);
			
			TextView headerText = (TextView) header.findViewById(R.id.screen_title);
			headerText.setText("PROFILE");
	
			name=(EditText)findViewById(R.id.name_edit);
			email=(EditText)findViewById(R.id.email_edit);
			phoneNumber=(EditText)findViewById(R.id.phone_number_edit);
			updateProfile=(Button)findViewById(R.id.update_btn);
			updateProfile.setOnClickListener(this);
	
	
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(TextUtils.isEmpty(name.getText().toString())){
			
			name.setError("Please Enter Name");
			name.requestFocus();
			
		}else if (TextUtils.isEmpty(email.getText().toString())) {
			
			
			
			
			email.setError("Please Enter Email Id");
			email.requestFocus();
			
		}else if (!isValidEmail(email.getText().toString())) {
			
			email.setError("Please Enter Valid Email Id");
			email.requestFocus();
			
		}else if (TextUtils.isEmpty(phoneNumber.getText().toString())||phoneNumber.getText().toString().length()<10 ) {
			
			phoneNumber.setError("Please Enter Valid Phone Number");
			phoneNumber.requestFocus();
			
		}else {
			
			
			
			
			
			
		}
		
	}
	private boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}	
	
	
	
}
