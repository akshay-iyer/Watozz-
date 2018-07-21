package com.example.m;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;  
import android.content.pm.PackageManager;
import android.widget.RatingBar;  
import android.widget.Toast;  

// sensor libraries
import android.hardware.Sensor;  
import android.hardware.SensorEvent;  
import android.hardware.SensorEventListener;  
import android.hardware.SensorManager;  


public class MainActivity extends Activity implements SensorEventListener{
    Button button;
    Button button1;
    Button button2;
    EditText editPhoneNum;
    EditText editSMS;
    private Spinner spinner1;
    private Spinner spinner2;
    RatingBar ratingbar1;  
    Button rbtn;  
//sensor
    private SensorManager sensorManager;  
    private boolean isColor = false;  
    private View view;  
    private long lastUpdate;  
    
    
    
    
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
     addListenerOnButton1();
      addListenerOnButton2();
      addListenerOnrbtn();
     // sensor 
      sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  
      lastUpdate = System.currentTimeMillis(); 
    }

   // sensor
  //overriding two methods of SensorEventListener  
    @Override  
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}  
    @Override  tivity.java
    public void onSensorChanged(SensorEvent event) {  
      if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {  
        getAccelerometer(event);  
      }  

    }  

    private void getAccelerometer(SensorEvent event) {  
      float[] values = event.values;  
      // Movement  
      float x = values[0];  
      float y = values[1];  
      float z = values[2];  

      float accelationSquareRoot = (x * x + y * y + z * z)  
          / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);  
        
      long actualTime = System.currentTimeMillis();  
      //Toast.makeText(getApplicationContext(),String.valueOf(accelationSquareRoot)+" "+  
                //  SensorManager.GRAVITY_EARTH,Toast.LENGTH_SHORT).show();  
        
      if (accelationSquareRoot >= 2) //it will be executed if you shuffle  
      {  
          String smsText = " Help Me !! My Location is :- KV Pendharkar College, Opp MIDC OFFICE, Dombivli (E)-Latitude:19.209400 Longitude: 73.093948.";
              Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + "9821528022"));
              intent.putExtra("sms_body", smsText);
              MainActivity.this.startActivity(intent);
      }



       
    }  

    @Override  
    protected void onResume() {  
      super.onResume();  
      // register this class as a listener for the orientation and  
      // accelerometer sensors  
      sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),  
                                  SensorManager.SENSOR_DELAY_NORMAL);  
    }  

    @Override  
    protected void onPause() {  
      // unregister listener  
      super.onPause();  
      sensorManager.unregisterListener(this);  
    }  
        
    
    
    private void addListenerOnrbtn() {
    	 ratingbar1=(RatingBar)findViewById(R.id.ratingBar1);  
        rbtn=(Button)findViewById(R.id.rbtn);  
         //Performing action on Button Click  
         rbtn.setOnClickListener(new OnClickListener(){  
   
             @Override  
             public void onClick(View arg0) {  
                 //Getting the rating and displaying it on the toast  
                 String rating=String.valueOf(ratingbar1.getRating());  
                 Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();  
             }  
               
         });  
		
	}

	public void addListenerOnButton1() {
       final Context context = this;
       this.button1 = (Button) findViewById(R.id.button1);
       this.button1.setOnClickListener(new OnClickListener() {
          public void onClick(View arg0) {
              
        	  Intent i;
          	PackageManager manager = getPackageManager();
          	try {
          	   i = manager.getLaunchIntentForPackage("com.google.android.apps.maps");
          	if (i == null)
          	    throw new PackageManager.NameNotFoundException();
          	i.addCategory(Intent.CATEGORY_LAUNCHER);
          	startActivity(i);
          	} catch (PackageManager.NameNotFoundException e) {

          	}
        	  
           }
       });
  }

    public void addListenerOnButton2() {
       final Context context = this;
        this.button2 = (Button) findViewById(R.id.button2);
        this.button2.setOnClickListener(new OnClickListener() {
           public void onClick(View arg0) {
                
        	   Intent myIntent = new Intent(MainActivity.this,
						Speak.class);
				startActivity(myIntent);
        	   
        	   
        	   
           }
        });
    }

    public void addListenerOnSpinnerItemSelection() {
        this.spinner1 = (Spinner) findViewById(R.id.spinner1);
    }

    public void addListenerOnSpinnerItemSelections() {
        this.spinner2 = (Spinner) findViewById(R.id.spinner2);
    }

    public void addListenerOnButton() {
        this.spinner1 = (Spinner) findViewById(R.id.spinner1);
        this.spinner2 = (Spinner) findViewById(R.id.spinner2);
        this.button = (Button) findViewById(R.id.button);
        this.button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                String smsText = new StringBuilder(String.valueOf(MainActivity.this.spinner1.getSelectedItem().toString())).append(System.getProperty("line.separator")).append(MainActivity.this.spinner2.getSelectedItem().toString()).toString();
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + "9821528022"));
                intent.putExtra("sms_body", smsText);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
