package service.impl;

import com.vividsolutions.jump.feature.*;
import entity.Signal;
import org.geotools.data.DataUtilities;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.SchemaException;
import org.opengis.feature.simple.SimpleFeatureType;
import service.IConverterService;
import util.mapper.SignalMapper;

import java.util.List;

/**
 * Created by Maria on 01.06.14.
 */
// todo:: maybe grails service?
public class ConverterService implements IConverterService {

    public ConverterService() {
    }

    @Override
    public FeatureCollection convert(List<Signal> signals) throws SchemaException {

        final SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "location:Point:srid=4326," + //  the geometry attribute: Point type
                        "value_:Double" // a number attribute
        );

        DefaultFeatureCollection defaultFeatureCollection =
                new DefaultFeatureCollection(null, TYPE);
        // todo:: think about exception
        // todo:: check paramrtrs and result
        FeatureSchema fcc = new FeatureSchema();
        fcc.addAttribute("geom", AttributeType.GEOMETRY); // todo: from property
        fcc.addAttribute("value_", AttributeType.DOUBLE);
        //    fcc.setCoordinateSystem(new CoordinateSystem("e",))

        FeatureCollection fc = new FeatureDataset(defaultFeatureCollection, fcc);
        SignalMapper signalMapper = new SignalMapper();
        for (entity.Signal signal : signals) { // refactor -> lambda&

            Feature f = new BasicFeature(fcc);
            f.setGeometry(signalMapper.transformFromPoint(signal));
            f.setAttribute("value_", signal.getValue().doubleValue());
            fc.add(f);
        }
        return fc;
    }
}
