package com.jonathanhelm.shutterspeed;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static com.jonathanhelm.shutterspeed.FilmSpeed.toFilmSpeed;


public class FilmSpeedFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Create the Aperture Spinner
        final Context myContext = getContext();
        final Spinner spinner = new Spinner(myContext);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(myContext, R.array.film_speeds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.film_speed)
                .setView(spinner)
                .setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int selectedFilmSpeedPosition = spinner.getSelectedItemPosition();
                        FilmSpeed selectedFilmSpeed = FilmSpeed.toFilmSpeed(selectedFilmSpeedPosition);
                        ((ShutterSpeed) myContext).updateFilmSpeed(selectedFilmSpeed);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        // Get the AlertDialog from create()
        return builder.create();
    }

    private FilmSpeed stringToFilmSpeed( String myFilmSpeedStr){

        switch (myFilmSpeedStr){
            case "ISO 25": return FilmSpeed.ISO25;
            case "ISO 50": return FilmSpeed.ISO50;
            case "ISO 100": return FilmSpeed.ISO100;
            case "ISO 200": return FilmSpeed.ISO200;
            case "ISO 400": return FilmSpeed.ISO400;
            case "ISO 800": return FilmSpeed.ISO800;
            case "ISO 1600": return FilmSpeed.ISO1600;
            case "ISO 3200": return FilmSpeed.ISO3200;
            default: return FilmSpeed.ISO25;
        }
    }

}
