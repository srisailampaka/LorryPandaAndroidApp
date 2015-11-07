package com.lorrypanda.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by DC on 4/1/2015.
 * <p/>
 * UrlConnection.java
 * This is a generic url connection handler class for all webservice call
 */
public class UrlConnection extends AsyncTask<Object, Void, String> {
    private InputStream inputStream = null;
    private String url;
    private String errorText = "";
    private Context mContext;
    private int type;
    private String resultResponse="{}";
    private int statusCode = -1;
    public final static int GET = 1;
	public final static int POST = 2;
private JSONObject request;
private ResultListener<String> callback;
    /*
    * parameterized to create a Url Connection
    *
    * @param parser
    *            GlobalParser reference
    * @param type
    *            HTTP method (POST, GET)
    * @param url
    *            webservice url of type String

    * @param connectionCallback
    *            ConnectionHandlerCallBack reference
    * @param mContext
    *            activity context
    *
    *
   * */
    public UrlConnection(ResultListener<String> callback,int type, String url,Context mContext,JSONObject request) {
        this.type = type;
        this.url = url;
        this.mContext = mContext;
        this.request = request;
        this.callback=callback;
    }

    /**
     * This method is to convert input stream to a String
     *
     * @param inputStream raw data response received from webservice call
     * @return response string
     * @throws java.io.IOException
     */
    private Object convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        try {
            while ((line = bufferedReader.readLine()) != null)
                result += line;
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    protected String doInBackground(Object... params) {

        try {
            makeServiceCall(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultResponse;
    }
    
    
    @Override
    protected void onPostExecute(String result) {
    	// TODO Auto-generated method stub
    	super.onPostExecute(result);
    	  callback.onSuccess(result);
    	
    }
    

    private void makeServiceCall(JSONObject request2) throws JSONException {
        HttpResponse httpResponse = null;
        DefaultHttpClient httpClient;
        Object result = null;
        Log.v("request Object", request2.toString());

        try {
            // Handle RESTful services
            HttpParams httpParameters = new BasicHttpParams();
            // set timeout for requests

            HttpConnectionParams.setConnectionTimeout(httpParameters, AppUtils.TIME_OUT_CONNECTION);
            HttpConnectionParams.setSoTimeout(httpParameters,AppUtils.TIME_OUT_SOCKET);

            httpClient = new DefaultHttpClient(httpParameters);

            // calling connection according to request type

            if (type == GET) {
                HttpGet httpGet = new HttpGet(url);
                httpResponse = httpClient.execute(httpGet);
                inputStream = httpResponse.getEntity().getContent();
            } else if (type == POST) {
                HttpPost httpPost = new HttpPost(url);
               // httpPost.setHeader("Accept", "application/json");
               // httpPost.setHeader("Content-type", "application/json");
                httpPost.setEntity(new StringEntity(request2.toString()));
                httpResponse = httpClient.execute(httpPost);
                inputStream = httpResponse.getEntity().getContent();
            }


            // check the response is not null

            if (inputStream != null) {
                try {
                    result = convertInputStreamToString(inputStream);
                    Log.v("Response :", result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            StatusLine statusLine = httpResponse.getStatusLine();
            statusCode = statusLine.getStatusCode();
            // status code <400 means successfully called url otherwise some error
            if (statusCode < 400) {
             resultResponse=result.toString();
                AppUtils.debugOut(result.toString());
            }
        } catch (ClientProtocolException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
