package util.mapper;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import entity.Signal;
import org.geotools.geometry.jts.JTSFactoryFinder;


public class SignalMapper {

    public Point transformToPoint(Signal signal) {
        GeometryFactory factory1 = JTSFactoryFinder.getGeometryFactory(null);
        double latitude = signal.getCoordinates().getLongitude();
        double longitude = signal.getCoordinates().getLatitute();
        return factory1.createPoint(new Coordinate(longitude, latitude));
    }
}
