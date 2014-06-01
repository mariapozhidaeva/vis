package service;

import com.vividsolutions.jump.feature.FeatureCollection;
import entity.Signal;
import org.geotools.feature.SchemaException;

import java.util.List;

/**
 * Created by Maria Pozhidaeva on 01.06.14.
 */
public interface IConverterService {

    // TODO::clean exception
    FeatureCollection convert(List<Signal> signals) throws SchemaException;
}
