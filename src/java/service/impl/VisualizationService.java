package service.impl;

import dao.ISignalDAO;
import entity.CalculationOutput;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.encoder.datastore.GSShapefileDatastoreEncoder;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager;
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

// todo:: logging
public class VisualizationService implements IVisualizationService {

    public static String STORE = "pgups_store";
    public VisualizationService() {

    }

    @Override
    public String visualize(Map<String, String> properties, CalculationOutput result) throws SchemaException, FileNotFoundException, MalformedURLException {  // todo:: exception?//new inherit

        String path = properties.get("geoserver.temp.storage");
        IConverterService converterService = new ConverterService();
        String nameFile = converterService.convert(path, result.getSignals()); // file

        String url = properties.get("url");
        String user = properties.get("user");
        String password = properties.get("password");
        String ws = properties.get("ws");
        String pathTemp = properties.get("geoserver.temp.storage");

        GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(url, user, password);
        GSShapefileDatastoreEncoder datastoreEncoder = new GSShapefileDatastoreEncoder(nameFile, new URL("file:\\data\\" + nameFile + ISignalDAO.SHAPE));// + "output" + ISignalDAO.SHAPE));
        try {
            GeoServerRESTStoreManager man = new GeoServerRESTStoreManager(new URL(url), user, password);
            boolean createdStore = man.create(ws, datastoreEncoder);
            System.out.println(createdStore);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            String nameZipFile = NameGenerator.buildFileName(new String[]{pathTemp, "\\data\\", nameFile, ISignalDAO.ZIP});
            File zipFile = new File(nameZipFile);

            boolean published = publisher.publishShp("pgups_ws_3", nameFile, nameFile, zipFile, "EPSG:4326", "signals_style_pgups");
            System.out.println(published);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameFile;

    }
}
