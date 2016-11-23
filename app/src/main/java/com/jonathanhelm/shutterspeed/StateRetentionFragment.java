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

        editor.putInt("aperture", myShutterSpeedCalculator.getAperture().toInt() );
        editor.putInt("film_speed", myShutterSpeedCalculator.getFilmSpeed().toInt() );

        editor.commit();



    }

    public ShutterSpeedCalculator getData() {

        if(isAdded()){

            Context myActivity = getActivity();
            SharedPreferences preferences = myActivity.getSharedPreferences(PREFS_NAME, 0);

            myShutterSpeedCalculator.setAperture(
                    Aperture.toAperture(preferences.getInt("aperture", 0)));
            myShutterSpeedCalculator.setFilmSpeed(
                    FilmSpeed.toFilmSpeed(preferences.getInt("film_speed", 0)));
        }


        return myShutterSpeedCalculator;
    }

}
