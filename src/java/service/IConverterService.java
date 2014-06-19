package service;

import entity.Signal;
import org.geotools.feature.SchemaException;

import java.util.List;

public interface IConverterService {

    String convert(String path, List<Signal> signals) throws SchemaException;
}
