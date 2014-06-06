package dao.impl;

import com.vividsolutions.jump.feature.FeatureCollection;
import com.vividsolutions.jump.io.DriverProperties;
import com.vividsolutions.jump.io.ShapefileWriter;
import dao.ISignalDAO;
import util.NameGenerator;

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
    private String tempStoragePath = "noname";

    public FileSignalDAO() {
    }

    public FileSignalDAO(String tempStoragePath, String nameFile) {
        this.nameFile = nameFile;
        this.tempStoragePath = tempStoragePath;
    }

    @Override
    public void writeShapeFile(FeatureCollection fc) {

        ShapefileWriter sw = new ShapefileWriter();
        DriverProperties dp = new DriverProperties();
        buildDriverProperties(dp);
        try {
            sw.write(fc, dp);
            System.out.println("shape");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeZipFile() throws FileNotFoundException {  // todo:: куда иксепшены лучше?

        // если несколько? безопаснее?
        // input file

        String nameShapeFile = NameGenerator.buildFileName(new String[]{tempStoragePath, "\\data\\", nameFile, ISignalDAO.SHAPE});
        FileInputStream in = new FileInputStream(nameShapeFile);

        System.out.println("shape -" + nameShapeFile);
        String nameZipFile = NameGenerator.buildFileName(new String[]{tempStoragePath, "\\data\\", nameFile, ISignalDAO.ZIP});

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(nameZipFile));
        System.out.println("shape - " + nameZipFile);

        try {
            out.putNextEntry(new ZipEntry(nameFile + ISignalDAO.SHAPE));
            byte[] b = new byte[1024];// buffer size
            int count;

            while ((count = in.read(b)) > 0) {
                out.write(b, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                out.flush();
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void buildDriverProperties(DriverProperties driverProperties) {

        String nameShapeFile = NameGenerator.buildFileName(new String[]{tempStoragePath, "\\data\\", nameFile, ISignalDAO.SHAPE});
        driverProperties.set("DefaultValue", nameShapeFile);
        driverProperties.set("ShapeType", "xy");
    }
}
