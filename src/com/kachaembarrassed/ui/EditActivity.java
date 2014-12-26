package com.kachaembarrassed.ui;

import java.io.File;

import com.kachaembarrassed.R;
import com.kachaembarrassed.util.commonutils;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_pic);
		ImageView img=(ImageView)findViewById(R.id.image);
		BitmapFactory.Options options=new BitmapFactory.Options();
		  Intent intent =getIntent();
		String u_path = intent.getStringExtra(commonutils.PIC_EDIT);
		String tag = null;
		Log.e(tag, u_path);
	Bitmap bitmap=	BitmapFactory.decodeFile(u_path,options);
	img.setImageBitmap(bitmap);
		
		
	}
	
}