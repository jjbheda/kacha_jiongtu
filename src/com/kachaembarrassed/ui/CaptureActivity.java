package com.kachaembarrassed.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;





import com.kachaembarrassed.KaChaEmarrassedApplication;
import com.kachaembarrassed.R;
import com.kachaembarrassed.util.commonutils;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class CaptureActivity extends Activity {
	private static final String TAG = "CaptureActivity";
protected String u_path="CameraTest";
private Bitmap mBitmap=null;
private static final int CAMERA_REQUEST = 1001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		initEnvironment();
		initWindow();
		initLayoutsAndViews();
		
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		KaChaEmarrassedApplication.getInstance().removeActivity(this);
		
		super.onDestroy();
	}
	
	private void initEnvironment() {
		KaChaEmarrassedApplication.getInstance().addActivity(this);
	}


	private void initWindow(){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.capture_activity);
		
	}
	private void initLayoutsAndViews(){
		u_path=getpath(u_path);
    	File file = new File( u_path );
    	Uri outputFileUri = Uri.fromFile( file );	
    	Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
    	intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
    	startActivityForResult( intent,CAMERA_REQUEST );

	}

	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {	
    	
    	if(CAMERA_REQUEST == requestCode)
    	{
    		if(RESULT_OK == resultCode)
    		{
    			onPhotoTaken();
    		}
    	}
    	else
    	{
    		finish();
    	}
//    	switch( resultCode ) {
//    		case 100:
//    			onPhotoTaken();
//    			break;
////    		case -1:
////    			onPhotoTaken();
////    			break;
//    			default:
//    				break;
//    	}
    }
    /*��ʾ�������Ƭ*/
    protected void onPhotoTaken() {
    	
    	zipPicture();
        Intent i = new Intent(this, EditActivity.class);
        i.putExtra(commonutils.PIC_EDIT, u_path);
        this.startActivity(i);
        
        finish();
    }
	
	
	
	
	
	
	
    public String getpath(String path){
   	 String time = callTime();
   	 File f = new File(Environment.getExternalStorageDirectory(),path);
        if (!f.exists()) {
                f.mkdir();
        }
        /* ����ͼƬ�ļ� */
        File n = null;
        n = new File(f,  time + ".jpg");
   	return n.toString();
   	
   }
   
   public static String callTime() {
       long backTime = new Date().getTime();
       Calendar cal = Calendar.getInstance();
       cal.setTime(new Date(backTime));
       int year = cal.get(Calendar.YEAR);
       int month = cal.get(Calendar.MONTH) + 1;
       int date = cal.get(Calendar.DAY_OF_MONTH);
       int hour = cal.get(Calendar.HOUR_OF_DAY);
       int minute = cal.get(Calendar.MINUTE);
       int second = cal.get(Calendar.SECOND);
       String time = "" + year + month + date + hour + minute + second;
       Log.i("CurrentTime", "^^^^^^^^^^^^^" + time + "^^^^^^^^^^^^^");
       return time;
 }
	
	public void zipPicture(){
		mBitmap = getBitmapFromPathNewSize(u_path, 480, 800);
		saveBitmap(mBitmap,u_path);
	}
	
public static Bitmap getBitmapFromPathNewSize(String path,int maxnewwidth, int maxnewhight) {
		
		Bitmap tempBitmap = null;
		BitmapFactory.Options op = new BitmapFactory.Options();
		op.inJustDecodeBounds = true;
		try {
    		tempBitmap = BitmapFactory.decodeFile(path, op); 
    		int wRatio = (int) Math.ceil(op.outWidth / (float)maxnewwidth);
    		int hRatio = (int) Math.ceil(op.outHeight / (float)maxnewhight);
    
    		if (wRatio > 1 && hRatio > 1) {
    			if (wRatio > hRatio) {
    				op.inSampleSize = wRatio;
    			} else {
    				op.inSampleSize = hRatio;
    			}
    		}
    		op.inJustDecodeBounds = false;
    		tempBitmap = BitmapFactory.decodeFile(path, op);
		} catch (OutOfMemoryError e) {
		    e.printStackTrace();
		}

		return tempBitmap;
	}

public static boolean saveBitmap(Bitmap bitmap, String path) {
	boolean result = false;
	FileOutputStream fos = null;
	try {
		File file = new File(path);
		if (file.exists()) {
			
		}
		fos = new FileOutputStream(file);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//		bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
		fos.flush();
		result = true;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (fos != null)
				fos.close();
		} catch (Exception e) {
		}
	}

	return result;
}

	
}
