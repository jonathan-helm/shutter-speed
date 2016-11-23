package com.jonathanhelm.shutterspeed;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class ApertureFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Create the Aperture Spinner
        final Context myContext = getContext();
        final Spinner spinner = new Spinner(myContext);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(myContext, R.array.apertures, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.film_speed)
                .setView(spinner)
                .setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        int selectedPosition = spinner.getSelectedItemPosition();
                        Aperture selectedAperture = Aperture.toAperture(selectedPosition);
                        ((ShutterSpeed) myContext).updateAperture(selectedAperture);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        // Get the AlertDialog from create()
        return builder.create();
    }

}
