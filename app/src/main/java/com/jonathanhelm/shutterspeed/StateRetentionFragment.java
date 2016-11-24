package com.jonathanhelm.shutterspeed;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class StateRetentionFragment extends Fragment {

    private ShutterSpeedCalculator myShutterSpeedCalculator;
    private final String PREFS_NAME = "ShutterSpeedPreferences";
    private final String SETTING_FILM_SPEED = "film_speed";
    private final String SETTING_APERTURE = "aperture";
    private final String SETTING_EXP_COMP = "exposure_compensation";

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);

        myShutterSpeedCalculator = new ShutterSpeedCalculator();
    }


    public void setData(ShutterSpeedCalculator data) {
        myShutterSpeedCalculator = data;


        Context myActivity = getActivity();
        SharedPreferences preferences = myActivity.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(SETTING_APERTURE, myShutterSpeedCalculator.getAperture().toInt() );
        editor.putInt(SETTING_FILM_SPEED, myShutterSpeedCalculator.getFilmSpeed().toInt() );
        editor.putInt(SETTING_EXP_COMP, myShutterSpeedCalculator.getExposureCompensation());

        editor.commit();



    }

    public ShutterSpeedCalculator getData() {

        if(isAdded()){

            Context myActivity = getActivity();
            SharedPreferences preferences = myActivity.getSharedPreferences(PREFS_NAME, 0);

            myShutterSpeedCalculator.setAperture(
                    Aperture.toAperture(preferences.getInt(SETTING_APERTURE, 0)));
            myShutterSpeedCalculator.setFilmSpeed(
                    FilmSpeed.toFilmSpeed(preferences.getInt(SETTING_FILM_SPEED, 0)));
            myShutterSpeedCalculator.setExposureCompensation(
                    preferences.getInt(SETTING_EXP_COMP, 0));
        }


        return myShutterSpeedCalculator;
    }

}
