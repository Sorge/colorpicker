package com.sorgedev.colorpicker;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class OtherTest extends Activity {
 
 ImageView imageView;

 SeekBar alphaBar, redBar, greenBar, blueBar;
 Spinner modeSpinner;
 TextView colorInfo;
 
 PorterDuff.Mode[] optMode = PorterDuff.Mode.values();
 
 String[] optModeName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_test);
        
        imageView = (ImageView)findViewById(R.id.iv);
        
        alphaBar = (SeekBar)findViewById(R.id.bar_a);
        redBar = (SeekBar)findViewById(R.id.bar_r);
        greenBar = (SeekBar)findViewById(R.id.bar_g);
        blueBar = (SeekBar)findViewById(R.id.bar_b);
        
        colorInfo = (TextView)findViewById(R.id.colorinfo);

        alphaBar.setOnSeekBarChangeListener(colorBarChangeListener);
        redBar.setOnSeekBarChangeListener(colorBarChangeListener);
        greenBar.setOnSeekBarChangeListener(colorBarChangeListener);
        blueBar.setOnSeekBarChangeListener(colorBarChangeListener);
        
        modeSpinner = (Spinner)findViewById(R.id.mode);
        prepareOptModeName();
        ArrayAdapter<String> modeArrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_spinner_item, 
                optModeName);
        modeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
        modeSpinner.setAdapter(modeArrayAdapter);
        modeSpinner.setSelection(0);
        modeSpinner.setOnItemSelectedListener(onModeItemSelectedListener);

        setPorterDuffColorFilter(imageView);
        
        
    }
    
    private void prepareOptModeName(){
     optModeName = new String[optMode.length];
     
     for(int i = 0; i < optMode.length; i++){
      optModeName[i] = optMode[i].toString();
     }
    }
    
    OnSeekBarChangeListener colorBarChangeListener
    = new OnSeekBarChangeListener(){

  public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
   setPorterDuffColorFilter(imageView);
  }

  public void onStartTrackingTouch(SeekBar seekBar) {
   // TODO Auto-generated method stub
   
  }

  public void onStopTrackingTouch(SeekBar seekBar) {
   // TODO Auto-generated method stub
   
  } 
    };
    
    OnItemSelectedListener onModeItemSelectedListener
    = new OnItemSelectedListener(){

  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
    long arg3) {
   setPorterDuffColorFilter(imageView);
   
  }

  public void onNothingSelected(AdapterView<?> arg0) {
   // TODO Auto-generated method stub
   
  }
     
    };
    
    private void setPorterDuffColorFilter(ImageView iv){
     
     int srcColor = Color.argb(
       alphaBar.getProgress(), 
       redBar.getProgress(), 
       greenBar.getProgress(), 
       blueBar.getProgress());
     
     PorterDuff.Mode mode = optMode[modeSpinner.getSelectedItemPosition()];
     
     PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(srcColor, mode);
     iv.setColorFilter(porterDuffColorFilter);
     
     colorInfo.setText(
       "srcColor = #" + Integer.toHexString(srcColor) +"\n" +
       "mode = " + String.valueOf(mode.toString()) + " of total " + String.valueOf(optMode.length) + " modes.");
  
    }

}