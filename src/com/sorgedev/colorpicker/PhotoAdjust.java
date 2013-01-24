package com.sorgedev.colorpicker;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.support.v4.app.NavUtils;

public class PhotoAdjust extends Activity implements OnClickListener {

	ImageView photo;
	SeekBar bright, contr;
	Bitmap extraPhoto;
	Button create;
	Bundle extr;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_adjust);
		setupVars();

		if (getIntent().hasExtra("photo")) {
			extr = getIntent().getBundleExtra("photo");
			extraPhoto = (Bitmap) extr.get("data");
			photo.setImageBitmap(extraPhoto);
		} else if (getIntent().hasExtra("picture")) {
			try {
				//leave comments to check on speed
				
				//extr = getIntent().getBundleExtra("picture");
				//Uri imageUri = (Uri) extr.get("data");
				Uri imageUri = getIntent().getData();
				/*BitmapFactory.Options bmFO = new BitmapFactory.Options();
				bmFO.inJustDecodeBounds = true;
				extraPhoto = BitmapFactory.decodeStream(getContentResolver()
						.openInputStream(imageUri), null, bmFO);*/
				extraPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
				photo.setImageBitmap(extraPhoto);
			} catch (Exception e) {
				Log.e("IMAGE ERROR", e.getMessage());
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_photo_adjust, menu);
		return true;
	}

	void setupVars() {
		photo = (ImageView) findViewById(R.id.ivPhoto);
		bright = (SeekBar) findViewById(R.id.sbBrightness);
		contr = (SeekBar) findViewById(R.id.sbContrast);
		create = (Button) findViewById(R.id.bCreatePhoto);
		create.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
