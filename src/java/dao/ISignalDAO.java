package dao;

import com.vividsolutions.jump.feature.FeatureCollection;

import java.io.FileNotFoundException;


public interface ISignalDAO {

    public static final String SHAPE = ".shp";
    public static final String ZIP = ".zip";

    void writeShapeFile(FeatureCollection fc);

    void writeZipFile() throws FileNotFoundException;


}
