package entity;
import java.util.List;

/**
 * Created by Maria on 15.04.14.
 */
public class CalculationOutput {

    private List<Signal> coordinates;

    private String name;
    private String description;
    private int idResult;

    public List<Signal> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Signal> coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }
}
