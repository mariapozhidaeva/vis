package service.impl;

import dao.ISignalDAO;
import entity.CalculationOutput;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.encoder.datastore.GSShapefileDatastoreEncoder;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager;
import org.apache.log4j.Logger;
import org.geotools.feature.SchemaException;
import service.IConverterService;
import service.IVisualizationService;
import util.NameGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

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
    static Logger logger = Logger.getLogger(VisualizationService.class);
    public static String STORE = "pgups_store";

    public VisualizationService() {

    }

    @Override
    public void visualize(Map<String, String> properties, CalculationOutput result) throws SchemaException, FileNotFoundException, MalformedURLException {  // todo:: exception?//new inherit

        String path = properties.get("geoserver.temp.storage");
        IConverterService converterService = new ConverterService();
        String nameFile = converterService.convert(path, result.getSignals()); // file
        // private static final log = LogFactory.getLog(this);
        //static Logger logger = Logger.getLogger(MyApp.class);

        // todo:: имя сгенерированн файла - уникальное
        // todo:: logger
        // todo:: в проперти http://grails.org/doc/2.1.0/guide/conf.html#configExternalized
        // todo::защищенность соединения?
        // todo::geoserver prmission user

        String RESTURL = properties.get("url");
        String RESTUSER = properties.get("user");
        String RESTPW = properties.get("password");
        String ws = properties.get("ws");
        String pathTemp = properties.get("geoserver.temp.storage");

        GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
        //boolean createdWS = publisher.createWorkspace("pgups_ws_3"); // ok
        GSShapefileDatastoreEncoder datastoreEncoder = new GSShapefileDatastoreEncoder(nameFile, new URL("file:\\data\\" + nameFile + ".shp"));// + "output" + ISignalDAO.SHAPE));
        // System.out.println(datastoreEncoder.getUrl());
        try {
            GeoServerRESTStoreManager man = new GeoServerRESTStoreManager(new URL(RESTURL), RESTUSER, RESTURL);
            // todo:: a если уже существует?
            System.out.println("man");
            boolean createdStore = man.create(ws, datastoreEncoder);
            System.out.println(createdStore);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            File zipFile2 = new File("D:\\output.zip");
            String nameZipFile = NameGenerator.buildFileName(new String[]{pathTemp, "\\data\\", nameFile, ISignalDAO.ZIP});
            File zipFile = new File(nameZipFile);

            boolean published = publisher.publishShp("pgups_ws_3", nameFile, nameFile, zipFile, "EPSG:4326", "ssss");
            System.out.println(published);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
