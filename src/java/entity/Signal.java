package entity;

import java.io.Serializable;

/**
 * Created by Maria on 17.04.14.
 */
public class Signal implements Serializable {
    private Coordinate coordinate; // заменить на существ координаты
    private Float value;

    public Signal() {
    }

    public Signal(Coordinate coordinate, Float value) {
        this.coordinate = coordinate;
        this.value = value;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
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
                "coordinate=" + coordinate.getLatitute()+" "+coordinate.getLongitude()+
                ", value=" + value +
                '}';
    }
}
