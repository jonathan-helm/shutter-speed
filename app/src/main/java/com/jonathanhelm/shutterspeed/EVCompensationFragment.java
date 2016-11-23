package com.jonathanhelm.shutterspeed;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import static android.text.InputType.TYPE_NUMBER_FLAG_SIGNED;


public class EVCompensationFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Create the Aperture Spinner
        final Context myContext = getContext();
        final EditText editText = new EditText(myContext);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);


        // Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.film_speed)
                .setView(editText)
                .setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        int  exposureCompensation = Integer.parseInt(editText.getText().toString());

                        ((ShutterSpeed) myContext).updateExposureCompensation(exposureCompensation);
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
