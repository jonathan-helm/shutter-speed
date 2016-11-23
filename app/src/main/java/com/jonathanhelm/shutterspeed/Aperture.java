package com.jonathanhelm.shutterspeed;

/**
 * Created by jonathanhelm on 11/22/16.
 */

public enum Aperture{
    F1_4("F/1.4", 0),
    F2_0("F/2.0", 1),
    F2_8("F/2.8", 2),
    F4_0("F/4.0", 3),
    F5_6("F/5.6", 4),
    F8_0("F/8.0", 5),
    F11("F/11.0", 6),
    F16("F/16.0", 7),
    F22("F/22.0", 8),
    F32("F/32.0", 9);

    private String stringValue;
    private int intValue;

    Aperture(String toString, int value) {
        this.stringValue = toString;
        this.intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public int toInt() { return intValue;}

    public static Aperture toAperture(int index){

        switch(index){
            case 0: return Aperture.F1_4;
            case 1: return Aperture.F2_0;
            case 2: return Aperture.F2_8;
            case 3: return Aperture.F4_0;
            case 4: return Aperture.F5_6;
            case 5: return Aperture.F8_0;
            case 6: return Aperture.F11;
            case 7: return Aperture.F16;
            case 8: return Aperture.F22;
            default: return Aperture.F32;
        }
    }
}

