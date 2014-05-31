package util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jump.feature.*;
import com.vividsolutions.jump.io.DriverProperties;
import com.vividsolutions.jump.io.ShapefileWriter;
import entity.CalculationOutput;
import org.geotools.data.DataUtilities;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Csv2Shape {

// todo: transactional?
// get properties
// put to gsp

 // logging

 // testing&

    public void convert(CalculationOutput arg) throws Exception {

        long l = System.currentTimeMillis();

        final SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "location:Point:srid=4326," + //  the geometry attribute: Point type
                        "value_:Double" // a number attribute
        );

        GeometryFactory factory1 = JTSFactoryFinder.getGeometryFactory(null);

        DefaultFeatureCollection defaultFeatureCollection =
                new DefaultFeatureCollection(null, TYPE);

        FeatureSchema fcc= new FeatureSchema();
        fcc.addAttribute("geom", AttributeType.GEOMETRY);
        fcc.addAttribute("value_", AttributeType.DOUBLE);
        //    fcc.setCoordinateSystem(new CoordinateSystem("e",))

       FeatureCollection fc = new FeatureDataset(defaultFeatureCollection, fcc);

        for (int i = 0; i < arg.getCoordinates().size(); i++) {

            entity.Signal signal = arg.getCoordinates().get(i);
            double longitude = signal.getCoordinate().getLongitude();
            double latitude = signal.getCoordinate().getLatitute();

            Feature f = new BasicFeature(fcc);
            f.setGeometry(factory1.createPoint(new Coordinate(longitude, latitude)));
            f.setAttribute("value_",  signal.getValue().doubleValue());
            fc.add(f);

        }

        ShapefileWriter sw = new ShapefileWriter();
        DriverProperties dp =   new DriverProperties();
       // dp.set("DefaultValue", "\\output2.shp");
        dp.set("DefaultValue", "D:\\opt\\GeoServer 2.5\\data_dir\\data\\signals.shp");

        dp.set("ShapeType", "xy");
        sw.write(fc, dp);

        long l1 = System.currentTimeMillis();

        // если несколько? безопаснее?
        // input file
        FileInputStream in = new FileInputStream("D:\\opt\\GeoServer 2.5\\data_dir\\data\\signals.shp");

        // out put file
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("D:\\opt\\GeoServer 2.5\\data_dir\\data\\signals.zip"));

        // name the file inside the zip  file
        out.putNextEntry(new ZipEntry("signals.shp"));

        // buffer size
        byte[] b = new byte[1024];
        int count;

        while ((count = in.read(b)) > 0) {
            System.out.println();
            out.write(b, 0, count);
        }
        out.close();
        in.close();
        System.out.println("olol:");
       System.out.println(System.currentTimeMillis()-l);
       System.out.println(System.currentTimeMillis()-l1);
    }


}