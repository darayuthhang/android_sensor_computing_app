package com.darayuth.sensorimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.darayuth.sensorimage.R;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private SensorManager sensorManager ;
    private Sensor accelerometer;
    private TextView displayTexts;
    private Button magnitudeBtn, xBtn, yBtn, zBtn ;
    private String xStr, yStr, zStr, magnitudeStr;
    private double x_axis, y_axis, z_axis;

    public static final String TAG = "MAINACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        //detect data when the mobile phone is moving.
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener( MainActivity.this, accelerometer,sensorManager.SENSOR_DELAY_NORMAL);


        inits();
    }

    public void inits(){
        magnitudeBtn = (Button) findViewById(R.id.button_magnitude);
        xBtn = (Button) findViewById(R.id.button_x);
        yBtn = (Button) findViewById(R.id.button_y);
        zBtn = (Button) findViewById(R.id.button_z);
        displayTexts = (TextView)findViewById(R.id.text_view);

        //register on click listener
        xBtn.setOnClickListener(this);
        yBtn.setOnClickListener(this);
        zBtn.setOnClickListener(this);
        magnitudeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.button_x:
                Log.e(TAG, "onClick: Button_x clicked");
                displayTexts.setText("x is triggered " + xStr);
                break;
            case R.id.button_y:
                Log.e(TAG, "onClick: Button_y clicked");
                displayTexts.setText("y is triggered " + yStr);
                break;
            case R.id.button_z:
                Log.e(TAG, "onClick: Button_z clicked");
                displayTexts.setText("z is triggered" + zStr);
                break;
            case R.id.button_magnitude:
                displayTexts.setText("magnitude is triggered " + calculateMagnitude());
                break;
            default:
        }
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x_axis = sensorEvent.values[0];
        y_axis = sensorEvent.values[1];
        z_axis = sensorEvent.values[2];

        xStr = Double.toString(x_axis);
        yStr = Double.toString(y_axis);
        zStr = Double.toString(z_axis);

    }

    public String calculateMagnitude(){
        double square_result = Math.sqrt(x_axis * x_axis * y_axis * y_axis * z_axis * z_axis );

        String squarestr = Double.toString(square_result);
        return squarestr;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
