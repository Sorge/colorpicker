package com.sorgedev.colorpicker;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class StartupActivity extends Activity implements OnClickListener {

	Button bPhoto, bPic, bSaved, bInfo;
	Intent i;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);

		setupButtons();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_startup, menu);
		return true;
	}

	public void setupButtons() {
		bPhoto = (Button) findViewById(R.id.bPhoto);
		bPhoto.setOnClickListener(this);

		bPic = (Button) findViewById(R.id.bPicture);
		bPic.setOnClickListener(this);

		bSaved = (Button) findViewById(R.id.bSaved);
		bSaved.setOnClickListener(this);

		bInfo = (Button) findViewById(R.id.bInfo);
		bInfo.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bPhoto:
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			Log.i("info", "button photo clicked");
			startActivityForResult(i, 0); // 0 stands for photo activity
			break;
		case R.id.bPicture:
			i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			Log.i("info", "button picture clicked");
			startActivityForResult(i, 1); // 1 stands for picture pick activity
			break;
		case R.id.bSaved:
			//TODO przycisk od testów, palety póŸniej
			i = new Intent(this, ImageTest.class);
			startActivity(i);
			break;
		case R.id.bInfo:
			i = new Intent(this, InformationBox.class);
			startActivity(i);
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			i = new Intent(this, PhotoAdjust.class);
			switch (requestCode) {
			case 0:				
				Bundle extras = data.getExtras();
				i.putExtra("photo", extras);
				Log.i("info","photo will be used");
				break;
			case 1:
				Uri iUri = data.getData();
				i.setData(iUri);
				i.putExtra("picture", "pic string useless");
				Log.i("info","picture will be used");
				break;
			}
			startActivity(i);
		}
	}

}
