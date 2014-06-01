package util.mapper;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import entity.Signal;
import org.geotools.geometry.jts.JTSFactoryFinder;

/**
 * Created by Maria on 01.06.14.
 */
public class SignalMapper {

    public Point transformFromPoint(Signal signal) {
        GeometryFactory factory1 = JTSFactoryFinder.getGeometryFactory(null);
        double longitude = signal.getCoordinate().getLongitude();
        double latitude = signal.getCoordinate().getLatitute();
        return factory1.createPoint(new Coordinate(longitude, latitude));
    }
}
