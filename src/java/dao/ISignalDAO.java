package dao;

import com.vividsolutions.jump.feature.FeatureCollection;

import java.io.FileNotFoundException;

/**
 * Created by Maria on 01.06.14.
 */
public interface ISignalDAO {

    public static final String SHAPE = ".shp";
    public static final String ZIP = ".zip";

    void writeShapeFile(FeatureCollection fc);

    void writeZipFile(/*path - name*/) throws FileNotFoundException;


}
