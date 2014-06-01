package service.impl;

import com.vividsolutions.jump.feature.FeatureCollection;
import dao.ISignalDAO;
import dao.impl.FileSignalDAO;
import entity.CalculationOutput;
import org.apache.log4j.Logger;
import org.geotools.feature.SchemaException;
import service.IConverterService;
import service.IVisualizationService;

import java.io.FileNotFoundException;

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
    public void visualize(CalculationOutput arg) throws SchemaException, FileNotFoundException {  // todo:: exception?//new inherit

        //System.out.println(System.getProperty("app.grails.version")); // на вход метода?
        Logger logger = Logger.getLogger(VisualizationService.class);
        logger.info("LOGGER");
        IConverterService converterService = new ConverterService();
        FeatureCollection fc = converterService.convert(arg.getSignals());

        ISignalDAO signalsDAO = new FileSignalDAO();
        signalsDAO.writeShapeFile(fc);  //todo:: отсюда пути
        signalsDAO.writeZipFile();
    }
}
