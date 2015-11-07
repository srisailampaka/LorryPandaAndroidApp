package com.lorrypanda.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataManagement {

	SharedPreferences pref;
	 Editor editor;
	
public 	DataManagement(Context context){
	 pref = context.getSharedPreferences("lorryapp",context.MODE_PRIVATE); 
	  
	 editor = pref.edit();
}

public void saveregistrationStatus(boolean status){
	
	editor.putBoolean("register",status);          
	 
	
    editor.commit();
	
}
public static boolean getregistrationStatus(Context context){
	return context.getSharedPreferences("lorryapp",context.MODE_PRIVATE).getBoolean("register",false);
	
	
}

public void savemobileNumber(String number){
	
	editor.putString("phoneNumber",number);          
	 
	
    editor.commit();
	
}

public static String getmobileNumber(Context context){
	return context.getSharedPreferences("lorryapp",context.MODE_PRIVATE).getString("phoneNumber","");
	
	
}

}
