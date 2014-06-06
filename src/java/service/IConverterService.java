package service;

import entity.Signal;
import org.geotools.feature.SchemaException;

import java.util.List;

/**
 * Created by Maria Pozhidaeva on 01.06.14.
 */
public interface IConverterService {

    String convert(String path, List<Signal> signals) throws SchemaException;
}
