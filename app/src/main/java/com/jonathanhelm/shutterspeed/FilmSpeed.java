package com.jonathanhelm.shutterspeed;

/**
 * Created by jonathanhelm on 11/22/16.
 */

public enum FilmSpeed{
    ISO25("ISO 25", 0),
    ISO50("ISO 50", 1),
    ISO100("ISO 100", 2),
    ISO200("ISO 200", 3),
    ISO400("ISO 400", 4),
    ISO800("ISO 800", 5),
    ISO1600("ISO 1600", 6),
    ISO3200("ISO 3200", 7);

    private String stringValue;
    private int intValue;

    FilmSpeed(String toString, int value) {
        this.stringValue = toString;
        this.intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public int toInt() { return intValue;}

    public static FilmSpeed toFilmSpeed(int index){
        switch(index){
            case 0: return FilmSpeed.ISO25;
            case 1: return FilmSpeed.ISO50;
            case 2: return FilmSpeed.ISO100;
            case 3: return FilmSpeed.ISO200;
            case 4: return FilmSpeed.ISO400;
            case 5: return FilmSpeed.ISO800;
            case 6: return FilmSpeed.ISO1600;
            default: return FilmSpeed.ISO3200;
        }
    }

}