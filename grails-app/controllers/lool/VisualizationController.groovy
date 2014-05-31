package lool

import entity.CalculationOutput
import entity.Coordinate
import entity.Signal
import it.geosolutions.geoserver.rest.encoder.datastore.GSShapefileDatastoreEncoder
import org.springframework.core.io.ClassPathResource
import util.Csv2Shape


import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;

import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager;
import org.apache.commons.httpclient.NameValuePair;

class VisualizationController {

    // как инъектить?
// как получить параметры?

    def index() {

        // стаб

          CalculationOutput co = new CalculationOutput();
          List list = new ArrayList<Signal>(15);
          Random r = new Random(System.currentTimeMillis());
          for (int i =0; i< 500; i++){
              // todo:: mapper
              Signal s = new Signal(new Coordinate((float)(r.nextFloat()*100), (float)(r.nextFloat()*15f)), (float)(r.nextFloat()%1));
              list.add(s);
              System.out.println(s.toString());
          }
          co.setCoordinates(list);

          try {
              Csv2Shape.convert(co);

          } catch (Exception e) {
              e.printStackTrace();
          };

        // todo:: имя сгенерированн файла - уникальное
        // todo:: logger
        long l = System.currentTimeMillis();  // локализация?
        System.getProperty("app.name");
        // todo:: в проперти http://grails.org/doc/2.1.0/guide/conf.html#configExternalized
        String RESTURL = "http://localhost:9999/geoserver";
        String RESTUSER = "admin";
        String RESTPW = "geoserver";


        GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
        boolean createdWS = publisher.createWorkspace("lol_ws_2");
        GSShapefileDatastoreEncoder datastoreEncoder = new GSShapefileDatastoreEncoder("lol_store_2", new URL("file:data"));

        try {
            GeoServerRESTStoreManager man = new GeoServerRESTStoreManager(new URL(RESTURL), "admin", "geoserver");
           // todo:: a если уже существует?
            boolean createdStore = man.create("lol_ws_2", datastoreEncoder);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        File zipFile = new File("D:\\opt\\GeoServer 2.5\\data_dir\\data\\signals.zip");
        // test insert
        boolean published = publisher.publishShp("lol_ws_2", "ws_store_2", "signals", zipFile, "EPSG:2000", "point_style");

        // model.addAttribute("message", "OK");
        long l2 = System.currentTimeMillis() - l;
        System.out.println("qweqweq");
        System.out.println(l2);

        render(view: "index", model: [name: l2])
    }
}

