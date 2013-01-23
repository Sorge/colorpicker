package com.sorgedev.colorpicker;

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
	 static final int photoData = 0;
	
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
    	bPhoto = (Button) findViewById(R.id.buttonPhoto);
    	bPhoto.setOnClickListener(this);
    	
    	bPic = (Button) findViewById(R.id.buttonPicture);
    	bPic.setOnClickListener(this);
    	
    	bSaved = (Button) findViewById(R.id.buttonSaved);
    	bSaved.setOnClickListener(this);
    	
    	bInfo = (Button) findViewById(R.id.buttonInfo);
    	bInfo.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonPhoto :
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			Log.i("info", "button photo clicked");
			startActivityForResult(i, photoData);
			break;
		case R.id.buttonPicture :
			break;
		case R.id.buttonSaved :
			break;
		case R.id.buttonInfo :
			i = new Intent(this, InformationBox.class);
			startActivity(i);
			break;
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("info", "result came");
		if(resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap image = (Bitmap) extras.get("data");
			i = new Intent(this, PhotoAdjust.class);
			i.putExtra("photo", image);
			startActivity(i);
		}
	}
	
	
}
