package com.example.dionysus.acc;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
   // private Sensor mAccelerometerUnc;
    //private Sensor mGravity;
    //private Sensor mLAcc;
    private float[] gravity = new float[3];
    private float[] linear_acceleration = new float[3];

    double aX,aY,aZ;   // these are the acceleration in x,y and z axis
    //double aucX,aucY,aucZ,aucX1,aucY1,aucZ1;   // these are the acceleration in x,y and z axis
    //double gX,gY,gZ;
    TextView tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8,tx9,tx10,tx11,tx12;
    Button btn;
    double x= 0,y=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tx1 = findViewById(R.id.textView1);
        tx2 = findViewById(R.id.textView2);
        tx3 = findViewById(R.id.textView3);

        tx4 = findViewById(R.id.textView6);
        tx5 = findViewById(R.id.textView7);
        tx6 = findViewById(R.id.textView8);
        tx7 = findViewById(R.id.textView9);
        tx8 = findViewById(R.id.textView10);
        tx9 = findViewById(R.id.textView11);

        tx10 = findViewById(R.id.textView13);
        tx11 = findViewById(R.id.textView14);
        tx12 = findViewById(R.id.textView15);

        btn = findViewById(R.id.button);

        tx4.setText("zdvfv");
        tx9.setText("dsczsdc");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //mAccelerometerUnc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tx4.setText("");
                tx5.setText("");
                tx6.setText("");
                tx7.setText("");
                x=0;
                y=0;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("onCreateOptionsMenu(Menu menu)");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("onOptionsItemSelected(MenuItem item)");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume()");
       // mSensorManager.registerListener(this, mAccelerometerUnc, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);

    //    mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause()");
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        System.out.println("onAccuracyChanged(Sensor sensor, int accuracy)");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //System.out.println("onSensorChanged(SensorEvent event)");


        //System.out.println("************event : **" +event);

      /*  if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER_UNCALIBRATED){
            System.out.println("event.sensor.getType()==Sensor.TYPE_ACCELEROMETER_UNCALIBRATED");
            aucX=event.values[0];
            aucY=event.values[1];
            aucZ=event.values[2];
            aucX1=event.values[3];
            aucY1=event.values[4];
            aucZ1=event.values[5];

            tx4.setText(String.valueOf(aucX));
            tx5.setText(String.valueOf(aucY));
            tx6.setText(String.valueOf(aucZ));
            tx7.setText(String.valueOf(aucX1));
            tx8.setText(String.valueOf(aucY1));
            tx9.setText(String.valueOf(aucZ1));
        }
/*
        if (event.sensor.getType()==Sensor.TYPE_GRAVITY){
            System.out.println("event.sensor.getType()==Sensor.TYPE_GRAVITY");
            gX=event.values[0];
            gY=event.values[1];
            gZ=event.values[2];

            tx10.setText(String.valueOf(gX));
            tx11.setText(String.valueOf(gY));
            tx12.setText(String.valueOf(gZ));
        }
*/
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            //System.out.println("event.sensor.getType()==Sensor.TYPE_ACCELEROMETER");
            aX=event.values[0];
            aY=event.values[1];
            aZ=event.values[2];



            final float alpha = 0.8f;

            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            linear_acceleration[0] = event.values[0] - gravity[0];
            linear_acceleration[1] = event.values[1] - gravity[1];
            linear_acceleration[2] = event.values[2] - gravity[2];

           /* System.out.println("******gravity[0]  :  "+gravity[0]);
            System.out.println("******gravity[1]  :  "+gravity[1]);
            System.out.println("******gravity[2]  :  "+gravity[2]);

            System.out.println("******linear_acceleration[0]  :  "+linear_acceleration[0]);
            System.out.println("******linear_acceleration[1]  :  "+linear_acceleration[1]);*/
            System.out.println("******linear_acceleration[2]  :  "+linear_acceleration[2]);

            tx1.setText(String.valueOf(linear_acceleration[0]));
            tx2.setText(String.valueOf(linear_acceleration[1]));
            tx3.setText(String.valueOf(linear_acceleration[2]));

            tx10.setText(String.valueOf(gravity[0]));
            tx11.setText(String.valueOf(gravity[1]));
            tx12.setText(String.valueOf(gravity[2]));

            if(linear_acceleration[2]>5){
                tx4.setText(String.valueOf(linear_acceleration[2]));
            }

            if(linear_acceleration[2]<-5){
                tx5.setText(String.valueOf(linear_acceleration[2]));
            }

            if(gravity[2]>x){
                x = gravity[2];
                tx6.setText(String.valueOf(gravity[2]));
            }

            if(gravity[2]<y){
                y = gravity[2];
                tx7.setText(String.valueOf(gravity[2]));
            }

        }

    }
}

