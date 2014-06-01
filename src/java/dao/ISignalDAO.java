package dao;

import com.vividsolutions.jump.feature.FeatureCollection;

import java.io.FileNotFoundException;

/**
 * Created by Maria on 01.06.14.
 */
public interface ISignalDAO {

    void writeShapeFile(FeatureCollection fc);

    void writeZipFile(/*path - name*/) throws FileNotFoundException;


}
