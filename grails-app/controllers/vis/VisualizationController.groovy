package vis

import entity.CalculationOutput
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher
import it.geosolutions.geoserver.rest.encoder.datastore.GSShapefileDatastoreEncoder
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager
import service.IVisualizationService
import service.impl.VisualizationService

class VisualizationController {

    // как инъектить?
// как получить параметры?

    def visualize(CalculationOutput co) {

        try {
            IVisualizationService visualizationService = new VisualizationService();
            visualizationService.visualize(co);

        } catch (Exception e) {
              e.printStackTrace();
          };

        // todo:: имя сгенерированн файла - уникальное
        // todo:: logger
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

        render(view: "index", model: [name: "TEST MESSAGE"])
    }
}

