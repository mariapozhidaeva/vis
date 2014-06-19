package service;

import entity.CalculationOutput;
import org.geotools.feature.SchemaException;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Map;

public interface IVisualizationService {

    String visualize(Map<String, String> properties, CalculationOutput result) throws SchemaException, FileNotFoundException, MalformedURLException;

}
