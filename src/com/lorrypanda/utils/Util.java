package com.lorrypanda.utils;

import android.content.Context;
import android.widget.Toast;

public class Util {

	
	
	public static void showMessage(Context context,String msg){
	
		
		Toast.makeText(context, msg, 5000).show();
		
	}
}
