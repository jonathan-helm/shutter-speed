
package com.jonathanhelm.shutterspeed;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import static com.jonathanhelm.shutterspeed.R.id.shutter_speed;

public class ShutterSpeed extends AppCompatActivity implements SensorEventListener {

    private ShutterSpeedCalculator myShutterSpeedCalculator;
    private SensorManager mySensorManager;
    private Sensor myLightSensor;
    private StateRetentionFragment myShutterSpeedRetentionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shutter_speed);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(myToolbar);

        // Instantiate ShutterSpeedCalculator
        myShutterSpeedCalculator = new ShutterSpeedCalculator();

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myLightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // find the retained fragment on activity restarts
        FragmentManager fm = getSupportFragmentManager();
        myShutterSpeedRetentionFragment = (StateRetentionFragment) fm.findFragmentByTag("shutter_speed");

        // create the fragment and data the first time
        if (myShutterSpeedRetentionFragment == null) {

            // Create new fragment and add set the model data
            myShutterSpeedRetentionFragment = new StateRetentionFragment();

            fm.beginTransaction().add(myShutterSpeedRetentionFragment, "shutter_speed").commit();
        }



    }

    @Override
    protected void onStart(){
        super.onStart();

        myShutterSpeedCalculator = myShutterSpeedRetentionFragment.getData();

        updateLightMeasurement();
        updateFilmSpeed();
        updateAperture();

    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mySensorManager.registerListener(this, myLightSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mySensorManager.unregisterListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        // store the data in the fragment
        myShutterSpeedRetentionFragment.setData(myShutterSpeedCalculator);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        return;
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {

        updateLightMeasurement(event.values[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    public boolean onFilmSpeedSettingClick(MenuItem item){

        DialogFragment newFragment = new FilmSpeedFragment();
        newFragment.show(getSupportFragmentManager(), "film_speed");
        return true;
    }

    public boolean onApertureSettingClick(MenuItem item){
        DialogFragment newFragment = new ApertureFragment();
        newFragment.show(getSupportFragmentManager(), "aperture");
        return true;
    }

    public void updateFilmSpeed( FilmSpeed newFilmSpeed){

        myShutterSpeedCalculator.setFilmSpeed(newFilmSpeed);
        updateFilmSpeed();

        TextView textView = (TextView)findViewById(shutter_speed);
        textView.setText(myShutterSpeedCalculator.getShutterSpeedStr());
    }

    public void updateFilmSpeed( ){

        TextView textView = (TextView)findViewById(R.id.film_speed_setting);
        textView.setText(myShutterSpeedCalculator.getFilmSpeed().toString());

    }

    public void updateAperture( Aperture newAperture){

        myShutterSpeedCalculator.setAperture(newAperture);
        updateAperture();

        TextView textView = (TextView)findViewById(shutter_speed);
        textView.setText(myShutterSpeedCalculator.getShutterSpeedStr());
        return;
    }

    public void updateAperture( ){

        TextView textView = (TextView) findViewById(R.id.aperture_setting);
        textView.setText(myShutterSpeedCalculator.getAperture().toString());

        return;
    }

    public void updateLightMeasurement( float newLightMeasurement){

        myShutterSpeedCalculator.updateMeasuredLight(newLightMeasurement);
        updateLightMeasurement();

        TextView textView = (TextView)findViewById(shutter_speed);
        textView.setText(myShutterSpeedCalculator.getShutterSpeedStr());

    }

    public void updateLightMeasurement(){
        TextView textView = (TextView)findViewById(R.id.exposure_value);
        textView.setText(myShutterSpeedCalculator.getMeasuredEVStr());
    }

}
