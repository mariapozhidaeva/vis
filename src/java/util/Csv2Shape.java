package util;

import com.vividsolutions.jump.feature.FeatureCollection;
import dao.ISignalDAO;
import dao.impl.FileSignalDAO;
import entity.CalculationOutput;
import service.IConverterService;
import service.impl.ConverterService;

public class Csv2Shape {


    public static void convert(CalculationOutput arg) throws Exception {

        System.out.println(System.getProperty("app.grails.version")); // на вход метода?

        IConverterService converterService = new ConverterService();
        FeatureCollection fc = converterService.convert(arg.getSignals()); // getSignals

        ISignalDAO signalsDAO = new FileSignalDAO();
        signalsDAO.writeShapeFile(fc);  // отсюда пути
        signalsDAO.writeZipFile();

    }


}