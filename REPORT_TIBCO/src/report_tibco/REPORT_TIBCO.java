/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


14

I manage to solve problem and I want to share the answer in order to help the community. The jar dependencies can be gathered via the pom.xml. However, the easy way relies on the iReport (jrxml generated version) installed folder. There is a folder in the location Jaspersoft\iReport-x.x.x\ireport\modules\ext (depends on OS). You can get the following jar files from there;

    jasperreportsXX.jar
    commons-loggingXX.jar
    commons-beanutilsXX.jar
    commons.digesterXX.jar
    commons-collectionsXX.jar
    iTextXX.jar
    jtd-compilerXX.jar
    groovy-allXX.jar

Then all problems are gone. Hope this helps to other user who meets the problem.

Regards.


 */
package report_tibco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ADRIAN_COSTA
 */
public class REPORT_TIBCO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new REPORT_TIBCO().exportTo();
    }
    
    
    void exportTo(){
        try {
            
            
            // Load the jrxml file
            JasperDesign jasperDesign = JRXmlLoader.load("REPORTE.jrxml");

            // Compile the jrxml file
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Produce the report (fill the report with data)
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, connection);




            
            List<User> lst = new ArrayList<>();
            User user = new User();
            user.setNombre( "Adrian" );
            lst.add( user );
            
            user = new User();
            user.setNombre( "Ana" );
            lst.add( user );
            
            JRBeanCollectionDataSource studentDataSource = new JRBeanCollectionDataSource(lst, true);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, studentDataSource ); //  new UsersJRDataSource()     new JRBeanCollectionDataSource(this.call())
            // JasperPrint jasperPrint = JasperFillManager.fillReport("REPORTE.jasper", null, new JRBeanCollectionDataSource(this.call()));
            // File file = new File("F.pdf");
            
    
            // JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(file) );
            
            JasperViewer jasview = new JasperViewer( jasperPrint, false );
            jasview.show();
            
            
            
        } catch (JRException ex) {
            Logger.getLogger(REPORT_TIBCO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    
}
