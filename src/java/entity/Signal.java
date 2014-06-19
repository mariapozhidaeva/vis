package entity;

import java.io.Serializable;

public class Signal implements Serializable {
    private Coordinates coordinates; // заменить на существ координаты
    private Float value;

    public Signal() {
    }

    public Signal(Coordinates coordinates, Float value) {
        this.coordinates = coordinates;
        this.value = value;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Signal{" +
                "coordinates=" + coordinates.getLatitute() + " " + coordinates.getLongitude() +
                ", value=" + value +
                '}';
    }
}
