package entity;

import java.io.Serializable;

public class Coordinates implements Serializable {

    private float longitude;
    private float latitute;

    public Coordinates() {
    }

    public Coordinates(float longitude, float latitute) {
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
