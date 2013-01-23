package com.sorgedev.colorpicker;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.support.v4.app.NavUtils;

public class PhotoAdjust extends Activity {

	ImageView photo;
	SeekBar bright, contr;
	Bitmap extraPhoto;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_adjust);
        setupVars();
        extraPhoto = (Bitmap) savedInstanceState.get("photo");
        photo.setImageBitmap(extraPhoto); 
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
    }

    
}
