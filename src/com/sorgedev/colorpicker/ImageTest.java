package com.sorgedev.colorpicker;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.support.v4.app.NavUtils;

public class ImageTest extends Activity implements OnClickListener,
		OnSeekBarChangeListener {

	ImageView imageView;
	SeekBar bright, contr;
	Button lighter, light, dark, darker;
	Bitmap image;
	ColorMatrix newMatrix = new ColorMatrix();
	ColorMatrixColorFilter filtr = new ColorMatrixColorFilter(newMatrix);
	int progressB, progressC;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_test);
		setupVars();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_image_test, menu);
		return true;
	}

	void setupVars() {
		imageView = (ImageView) findViewById(R.id.ivTest);
		image = imageView.getDrawingCache();
		bright = (SeekBar) findViewById(R.id.sbTestBright);
		bright.setOnSeekBarChangeListener(this);
		contr = (SeekBar) findViewById(R.id.sbTestContr);
		contr.setOnSeekBarChangeListener(this);
		light = (Button) findViewById(R.id.bTestBLight);
		light.setOnClickListener(this);
		lighter = (Button) findViewById(R.id.bTestBLighter);
		lighter.setOnClickListener(this);
		dark = (Button) findViewById(R.id.bTestBDark);
		dark.setOnClickListener(this);
		darker = (Button) findViewById(R.id.bTestDarker);
		darker.setOnClickListener(this);
	}

	public void onClick(View v) {

		int src;
		PorterDuff.Mode mode;
		PorterDuffColorFilter pdcf;

		switch (v.getId()) {
		case R.id.bTestBLight:
			src = Color.argb(60, 255, 255, 255);
			mode = PorterDuff.Mode.SRC_OVER;
			pdcf = new PorterDuffColorFilter(src, mode);
			imageView.setColorFilter(pdcf);
			break;
		case R.id.bTestBLighter:
			src = Color.argb(180, 255, 255, 255);
			mode = PorterDuff.Mode.SRC_OVER;
			pdcf = new PorterDuffColorFilter(src, mode);
			imageView.setColorFilter(pdcf);
			break;
		case R.id.bTestBDark:
			src = Color.argb(40, 0, 0, 0);
			mode = PorterDuff.Mode.SRC_ATOP;
			pdcf = new PorterDuffColorFilter(src, mode);
			imageView.setColorFilter(pdcf);
			break;
		case R.id.bTestDarker:
			src = Color.argb(180, 0, 0, 0);
			mode = PorterDuff.Mode.SRC_ATOP;
			pdcf = new PorterDuffColorFilter(src, mode);
			imageView.setColorFilter(pdcf);
			break;
		}
	}

	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		switch (seekBar.getId()) {
		case R.id.sbTestBright:
			progress -= 255;
			//setColorFilter(progress, true);
			progressB = progress;
			setFilter();
			break;
		case R.id.sbTestContr:
			progress -= 255;
			progressC = progress;
			setFilter();
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
		imageView.setColorFilter(filtr);
		
	}

	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}

}
