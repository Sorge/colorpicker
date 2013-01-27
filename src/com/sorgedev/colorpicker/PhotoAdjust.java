package com.sorgedev.colorpicker;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class PhotoAdjust extends Activity implements OnClickListener, OnSeekBarChangeListener {

	ImageView photo;
	SeekBar bright, contr;
	Bitmap extraPhoto;
	Button create;	
	ColorMatrix newMatrix;
	ColorMatrixColorFilter filtr;
	int progressB, progressC;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_adjust);
		setupVars();

		Bundle extr;
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
		bright.setOnSeekBarChangeListener(this);
		contr = (SeekBar) findViewById(R.id.sbContrast);
		contr.setOnSeekBarChangeListener(this);
		create = (Button) findViewById(R.id.bCreatePhoto);
		create.setOnClickListener(this);
		
		newMatrix = new ColorMatrix();
		filtr = new ColorMatrixColorFilter(newMatrix);
	}

	public void onClick(View arg0) {
		// TODO przycisk tworzenia palety

	}

	public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
		switch(sb.getId()) {
		case R.id.sbBrightness :
			progress -= 255;
			progressB = progress;
			setFilter();			
			break;
		case R.id.sbContrast :
			progress -= 255;
			progressC = progress;
			setFilter();
			break;
		}
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		switch(seekBar.getId()) {
		case R.id.sbBrightness :
			break;
		case R.id.sbContrast :
			break;
		}
		
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		switch(seekBar.getId()) {
		case R.id.sbBrightness :
			break;
		case R.id.sbContrast :
			break;
		}
		
	}
	
	void setFilter() {
		//TODO sprawdziæ, czy zmniejszenie iloœci wartoœci nie by³oby lepsze i szybsze
		//dzia³a³o ok u mnie i S³awka, przyda³oby siê coœ jeszcze
		
		float f = (float) (259 * (progressC + 255)) / (255 * (259 - progressC));
		float src[] = {f, 0, 0, 0, (-128 * f + 128) + progressB,
					   0, f, 0, 0, (-128 * f + 128) + progressB, 
					   0, 0, f, 0, (-128 * f + 128) + progressB, 
					   0, 0, 0, 1,	0 };
		newMatrix.set(src);
		filtr = new ColorMatrixColorFilter(newMatrix);
		photo.setColorFilter(filtr);
		
	}

}
