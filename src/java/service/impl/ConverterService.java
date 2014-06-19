package service.impl;

import com.vividsolutions.jump.feature.*;
import dao.ISignalDAO;
import dao.impl.FileSignalDAO;
import entity.Signal;
import org.geotools.data.DataUtilities;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.SchemaException;
import org.opengis.feature.simple.SimpleFeatureType;
import service.IConverterService;
import util.NameGenerator;
import util.mapper.SignalMapper;

import java.io.FileNotFoundException;
import java.util.List;

// todo::  grails service is better
public class ConverterService implements IConverterService {

    public ConverterService() {
    }

    @Override
    public String convert(String path, List<Signal> signals) throws SchemaException {  // file return&

        FeatureCollection fc = convertToFeatureCollection(signals);

        System.out.println("fc");
        System.out.println(fc.size());
        String file = NameGenerator.getUniqName();
        ISignalDAO signalsDAO = new FileSignalDAO(path, file);
        signalsDAO.writeShapeFile(fc);
        try {
            signalsDAO.writeZipFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    private FeatureCollection convertToFeatureCollection(List<Signal> signals) throws SchemaException {

        final SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "location:Point:srid=4326," + //  the geometry attribute: Point type
                        "value_:Double" // a number attribute
        );

        DefaultFeatureCollection defaultFeatureCollection =
                new DefaultFeatureCollection(null, TYPE);

        FeatureSchema fcc = new FeatureSchema();
        fcc.addAttribute("geom", AttributeType.GEOMETRY);
        fcc.addAttribute("value_", AttributeType.DOUBLE);

        FeatureCollection fc = new FeatureDataset(defaultFeatureCollection, fcc);
        SignalMapper signalMapper = new SignalMapper();
        for (entity.Signal signal : signals) {

            Feature f = new BasicFeature(fcc);
            f.setGeometry(signalMapper.transformToPoint(signal));
            f.setAttribute("value_", signal.getValue().doubleValue());
            fc.add(f);
        }
        return fc;
    }

}
