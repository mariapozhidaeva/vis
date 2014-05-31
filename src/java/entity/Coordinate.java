package entity;

import java.io.Serializable;

/**
 * Created by Maria on 15.04.14.
 */
public class Coordinate implements Serializable {

    private float longitude;
    private float latitute;

    public Coordinate() {
    }

    public Coordinate(float longitude, float latitute) {
        this.longitude = longitude;
        this.latitute = latitute;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitute() {
        return latitute;
    }

    public void setLatitute(float latitute) {
        this.latitute = latitute;
    }
}
