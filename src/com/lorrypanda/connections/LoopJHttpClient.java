package com.lorrypanda.connections;

import org.apache.http.HttpVersion;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.params.CoreProtocolPNames;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;

public class LoopJHttpClient {
	
	 private static final String BASE_URL = Urls.COMMON;
	 private static AsyncHttpClient client = new AsyncHttpClient();
	
	static {
		 client.setTimeout(AppUtils.TIME_OUT_SOCKET);
		 client.setConnectTimeout(AppUtils.TIME_OUT_CONNECTION);
		 client.getHttpClient().getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
		 client.getHttpClient().getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
		 client.getHttpClient().getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	 }

	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.get(getAbsoluteUrl(url), params, responseHandler);
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(getAbsoluteUrl(url), params, responseHandler);
	       
	  }
	  public static void put(Context context,String url,RequestParams params, AsyncHttpResponseHandler responseHandler) {
		  client.put(context, getAbsoluteUrl(url), null, "application/json", responseHandler);
	    }

	  private static String getAbsoluteUrl(String relativeUrl) {
	      return BASE_URL + relativeUrl;
	  }
}
