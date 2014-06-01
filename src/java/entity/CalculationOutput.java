package entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Maria on 15.04.14.
 */
public class CalculationOutput implements Serializable {

    private List<Signal> signals;

    private String name;
    private String description;
    private int idResult;

    public List<Signal> getSignals() {
        return signals;
    }

    public void setSignals(List<Signal> signals) {
        this.signals = signals;
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
