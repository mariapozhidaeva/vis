package service.impl;

import entity.CalculationOutput;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.encoder.datastore.GSShapefileDatastoreEncoder;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager;
import org.geotools.feature.SchemaException;
import service.IConverterService;
import service.IVisualizationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Maria on 01.06.14.
 */
public class VisualizationService implements IVisualizationService {

// todo: transactional?
// todo:: get properties
// todo:: put to gsp
// todo:: logging
// todo:: testing - ?
// todo::инъектить?

    public VisualizationService() {
    }

    @Override
    public void visualize(CalculationOutput result) throws SchemaException, FileNotFoundException, MalformedURLException {  // todo:: exception?//new inherit

        IConverterService converterService = new ConverterService();
        converterService.convert(result.getSignals()); // file

        // todo:: имя сгенерированн файла - уникальное
        // todo:: logger
        // todo:: в проперти http://grails.org/doc/2.1.0/guide/conf.html#configExternalized
        // защищенность соединения?
        // geoserver prmission user

        String RESTURL = "http://localhost:9999/geoserver"; //System.getProperty("geoserver.url");
        String RESTUSER = "admin"; //System.getProperty("geoserver.user");
        String RESTPW = "geoserver";//System.getProperty("geoserver.password");
        System.out.println("rest" + RESTURL);
        GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
        // boolean createdWS = publisher.createWorkspace("lol_ws_5"); // ok
        //  todo:: можно shape хранить на сервере
        GSShapefileDatastoreEncoder datastoreEncoder = new GSShapefileDatastoreEncoder("lol_store_17", new URL("file:\\data\\output.shp"));
        // GSShapefileDatastoreEncoder datastoreEncoder = new GSShapefileDatastoreEncoder("lol_store_6", new URL("file:data/signals4.shp"));
        // datastoreEncoder.setUrl(new URL("file:data/signals4.shp"));

        System.out.println(datastoreEncoder.toString());
        try {
            GeoServerRESTStoreManager man = new GeoServerRESTStoreManager(new URL(RESTURL), "admin", "geoserver");
            // todo:: a если уже существует?
            System.out.println("man");
            boolean createdStore = man.create("lol_ws_5", datastoreEncoder);
            System.out.println(createdStore);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        File zipFile = new File("D:\\opt\\GeoServer 2.5\\data_dir\\data\\signals4.zip");
        // test insert
        //    boolean published = publisher.publishShp("lol_ws_3", "ws_store_2", "signals", zipFile, "EPSG:2000", "point_style");
        //    boolean published = publisher.publishShp("lol_ws_5", "lol_store_10", "signals4", zipFile);//, "EPSG:4326", "point_style");


        try {
            // File zipfile = new ClassPathResource("D:\\opt\\GeoServer 2.5\\data_dir\\data\\output2.zip").getFile();

            File zipFile2 = new File("D:\\output.zip");
            File zipFile3 = new File("D:\\output.shp");

            boolean published = publisher.publishShp("lol_ws_5", "lol_store_17", "output", zipFile2, "EPSG:4326", "point_style");
            //  boolean published2 = publisher.publishShp("lol_ws_5", "lol_store_12", "outpu2");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
