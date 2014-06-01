package service;

import entity.CalculationOutput;
import org.geotools.feature.SchemaException;

import java.io.FileNotFoundException;

/**
 * Created by Maria Pozhidaeva on 01.06.14.
 */
public interface IVisualizationService {

    void visualize(CalculationOutput arg) throws SchemaException, FileNotFoundException;

}
