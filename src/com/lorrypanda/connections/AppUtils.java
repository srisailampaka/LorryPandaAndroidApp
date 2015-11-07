package com.lorrypanda.connections;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.lorrypanda.activitys.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

public class AppUtils {

	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static boolean isLogging = true;
	public static String TAG = "Adrobe";
	public static int TIME_OUT_CONNECTION = 10000;
	public static int TIME_OUT_SOCKET = 30000;
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";




	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING
					.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static String getServiceResponse(String urlPath) {
		// Create a new HttpClient and Post Header
		BufferedReader in = null;
		InputStreamReader isr = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();

			HttpGet request = new HttpGet();
			URI website = new URI(URLEncoder.encode(urlPath));
			request.setURI(website);
			HttpResponse response = httpclient.execute(request);
			isr = new InputStreamReader(response.getEntity().getContent());
			in = new BufferedReader(isr);

			String line = in.readLine();
			// line = line.replaceAll("\\\\", "");

			return line;// .substring(1, line.length()-1);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static ProgressDialog showProgressDialog(Context mContext,
			String text, boolean cancelable) {
		ProgressDialog progressDialog = new ProgressDialog(mContext);
		progressDialog = new ProgressDialog(mContext);
		progressDialog.setMessage(text);
		progressDialog.setCancelable(false);
		progressDialog.show();

		return progressDialog;
	}

	public static Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xffff0000;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setFilterBitmap(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawOval(rectF, paint);
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth((float) 4);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static String getServerResponse(String URL) {

		StringBuilder stringBuilder = null;
		try {

			HttpPost httppost = new HttpPost(URL);

			HttpClient client = new DefaultHttpClient();
			HttpResponse response;
			stringBuilder = new StringBuilder();

			response = client.execute(httppost);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			int b;
			while ((b = stream.read()) != -1) {
				stringBuilder.append((char) b);
			}
		} catch (ClientProtocolException e) {
			Log.i("getLocationInfo ClientProtocolException", e.toString());
		} catch (IOException e) {

			Log.i("getLocationInfo IOException", e.toString());
		}

		return stringBuilder.toString();
	}

	public  static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	public static String convertStreamToString1(InputStream is)
			throws IOException {
		Writer writer = new StringWriter();
		char[] buffer = new char[2048];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} finally {
			is.close();
		}
		String text = writer.toString();
		return text;
	}

	public static void debugOut(String msg) {
		StackTraceElement element = new Throwable().getStackTrace()[1];
		final String logMsg = element.getMethodName() + " -- "
				+ element.getFileName() + " (" + element.getLineNumber()
				+ ")+ " + msg;
		if (isLogging == false) {
			return;
		}
		Log.d(TAG, logMsg);
	}

	public static String ConvertBase64(Bitmap bmp) {
		bmp = Bitmap.createScaledBitmap(bmp, 160, 160, true);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 50, stream);
		byte[] image = stream.toByteArray();
		String img_str = Base64.encodeToString(image, 0);
		return img_str;
	}

	 public static String convertToBase64(Bitmap bitmap) {
	      //  System.gc();
	        if (bitmap == null) return null;
	        // Resize the image
	        double width = bitmap.getWidth();
	        double height = bitmap.getHeight();
	        double ratio = 400 / width;
	        int newheight = (int) (ratio * height);

	        Bitmap imageBitmap = Bitmap.createScaledBitmap(bitmap ,400 , newheight ,true);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();

	        imageBitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
	        byte[] bytes = baos.toByteArray();
	        String imageEncoded = Base64.encodeToString(bytes, Base64.DEFAULT);
	        return imageEncoded;
	    }
	public static Bitmap getThumbnail(ContentResolver cr, String path)
			throws Exception {

		Cursor ca = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.MediaColumns._ID },
				MediaStore.MediaColumns.DATA + "=?", new String[] { path },
				null);
		if (ca != null && ca.moveToFirst()) {
			int id = ca.getInt(ca.getColumnIndex(MediaStore.MediaColumns._ID));
			ca.close();
			return MediaStore.Images.Thumbnails.getThumbnail(cr, id,
					MediaStore.Images.Thumbnails.MINI_KIND, null);
		}

		ca.close();
		return null;
	}

	public static boolean haveNetworkConnection(Context context) {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	public static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		debugOut("Result " + result);
		return result;

	}

	public static void displayNoInternetAlert(Context context) {
		AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
				// set message, title, and icon
				.setTitle(
						context.getResources().getString(
								R.string.app_name))
				.setMessage(
						context.getResources().getString(R.string.nointernet))
				// .setIcon(R.drawable.alert)

				.setPositiveButton(
						context.getResources().getString(R.string.ok),
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {

								dialog.dismiss();
							}

						}).create();
		myQuittingDialogBox.show();
	}

	public static void displayToast(Context context, String msg) {
		if (msg != null)
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	public static SharedPreferences getSharedPrefernce(Context context) {
		
		SharedPreferences shared = context.getSharedPreferences("Adrobe",
				context.MODE_PRIVATE);
		return shared;

	}
	

	
	
public static void showMessage(Context context,String message){
		
		
		Toast.makeText(context, ""+message, 5000).show();
		
		
	}
	
	
}
