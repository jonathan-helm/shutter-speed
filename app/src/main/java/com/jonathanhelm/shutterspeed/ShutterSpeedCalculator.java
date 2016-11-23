package com.jonathanhelm.shutterspeed;

/**
 * Created by jonathanhelm on 11/22/16.
 */

public class ShutterSpeedCalculator {

    private FilmSpeed myFilmSpeed;
    private Aperture myAperture;
    private int myMeasuredLightIntensity;
    private int myMeasuredEV;
    private int myExposureCompensation;
    private String myShutterSpeed;

    private final float lumenCutoffLevels[] = {
            1.75f, 3.5f, 7, 14, 28, 56, 112, 225, 450, 900, 1800, 3600, 7200,
            14400, 28900, 57800, 116000, 232000, 464000 };

    private final String shutterSpeeds[] = {
            "1/1000000 s",  "1/500000 s",   "1/2500000 s",  "1/125000 s",   "1/60000 s",
            "1/30000 s",    "1/15000 s",    "1/8000 s",     "1/4000 s",     "1/2000 s",
            "1/1000 s",     "1/500 s",      "1/250 s",      "1/125 s",      "1/60 s",
            "1/30 s",       "1/15 s",       "1/8 s",        "1/4 s",        "1/2 s",
            "1.0 s",        "2.0 s",        "4.0 s",        "8.0 s",        "15.0 s",
            "30.0 s",       "1 min",        "2 min",        "4 min",        "8 min",
            "16 min",       "32 min"
    };

    private final int sunny16ISO400ShutterSpeedIdx = 11;
    private final int sunny16ApertureIdx = 7;
    private final int sunny16ISO400Idx = 4;
    private final int sunny16EVIdx = 15;

    public ShutterSpeedCalculator(){

        myFilmSpeed = FilmSpeed.ISO400;
        myAperture = Aperture.F16;
        myMeasuredLightIntensity = 0;
        myExposureCompensation = 0;
        myMeasuredEV = lightToEV(myMeasuredLightIntensity);
        myShutterSpeed = shutterSpeeds[0];
    }


    public void updateMeasuredLight( float newMeasuredLight){

        myMeasuredEV = lightToEV(newMeasuredLight);
        updateShutterSpeed();

    }

    public void updateShutterSpeed(){

        final int apertureOffset = sunny16ApertureIdx - myAperture.toInt();
        final int filmspeedOffset = sunny16ISO400Idx - myFilmSpeed.toInt();
        final int exposureValueOffset = sunny16EVIdx - myMeasuredEV;

        // Increase Aperture index -> Decrease Aperture Size ->  Increase shutter speed -> Decrease shutter speed idx
        // Increase ISO index -> Increase ISO -> Decrease shutter speed -> Increase shutter speed idx
        // Increase EV -> Increase EV index -> Decrease shutter speed -> Increase shutter speed idx
        final int shutterSpeedIdx = sunny16ISO400ShutterSpeedIdx
                                        - apertureOffset
                                        + filmspeedOffset
                                        + exposureValueOffset;

        myShutterSpeed = shutterSpeeds[shutterSpeedIdx];
        return;
    }

    private int lightToEV(float light){
        int i;

        for(i = 0; i < lumenCutoffLevels.length; i++){
            if(light < lumenCutoffLevels[i] )
                return i - 1;
        }

        return lumenCutoffLevels.length;

    }

    public String getShutterSpeedStr(){

        return myShutterSpeed;
    }

    public String getMeasuredEVStr(){
        if( myMeasuredEV >= 0 )
            return "EV +" + Double.toString(myMeasuredEV);
        else
            return "EV " + Double.toString(myMeasuredEV);
    }

    public int getMeasuredEV(){
        return myMeasuredEV;
    }

    public FilmSpeed getFilmSpeed(){
        return myFilmSpeed;
    }

    public void setFilmSpeed(FilmSpeed newFilmSpeed){
        myFilmSpeed = newFilmSpeed;
        updateShutterSpeed();
    }

    public Aperture getAperture(){
        return myAperture;
    }

    public void setAperture(Aperture newAperture){
        myAperture = newAperture;
        updateShutterSpeed();
    }

    public int getExposureCompensation(){
        return myExposureCompensation;
    }

    public void setExposureCompensation(int newExposureCompensation){
        myExposureCompensation = newExposureCompensation;
    }

}
