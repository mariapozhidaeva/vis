package dao.impl;

import com.vividsolutions.jump.feature.FeatureCollection;
import com.vividsolutions.jump.io.DriverProperties;
import com.vividsolutions.jump.io.ShapefileWriter;
import dao.ISignalDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Maria on 01.06.14.
 */

public class FileSignalDAO implements ISignalDAO {

    private String nameFile = "noname";

    /*todo:: сюда еще проперти*/
    public FileSignalDAO() {
    }

    public FileSignalDAO(String nameFile) {
        this.nameFile = nameFile;
    }

    @Override
    public void writeShapeFile(FeatureCollection fc) {

        ShapefileWriter sw = new ShapefileWriter();
        // configDriverProperties -
        DriverProperties dp = new DriverProperties();
        configureDriverProperties(dp);
        try {
            sw.write(fc, dp);
            System.out.println("shape");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close?
        }

    }

    @Override
    public void writeZipFile() throws FileNotFoundException {  // todo:: куда иксепшены лучше?
        //long l1 = System.currentTimeMillis();
        // если несколько? безопаснее?
        // input file
        // stringbuilder
        FileInputStream in = new FileInputStream("D:\\opt\\GeoServer 2.5\\data_dir\\data\\" + nameFile + ISignalDAO.SHAPE);
        System.out.println("shape - D:\\opt\\GeoServer 2.5\\data_dir\\data\\" + nameFile + ISignalDAO.SHAPE);
        // out put file
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("D:\\opt\\GeoServer 2.5\\data_dir\\data\\" + nameFile + ISignalDAO.ZIP));
        System.out.println("shape - D:\\opt\\GeoServer 2.5\\data_dir\\data\\" + nameFile + ISignalDAO.ZIP);
        // name the file inside the zip  file

        try {
            out.putNextEntry(new ZipEntry(nameFile + ISignalDAO.ZIP));
            byte[] b = new byte[1024];// buffer size
            int count;

            while ((count = in.read(b)) > 0) {
                //System.out.println();
                out.write(b, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                out.flush();
                out.close();//  if not null
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void configureDriverProperties(DriverProperties driverProperties) {
        // dp.set("DefaultValue", "\\output2.shp");
        driverProperties.set("DefaultValue", "D:\\opt\\GeoServer 2.5\\data_dir\\data\\" + nameFile + ISignalDAO.SHAPE);  // todo: from arguments
        driverProperties.set("ShapeType", "xy");
    }
}
