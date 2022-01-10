/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
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
public class REPORT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new REPORT().exportTo();
    }
    
    
    
    void exportTo(){
        try {
            // TODO code application logic here
            //
            // Load the jrxml file
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResource("/JasperReports/REPORTE.jrxml").getFile());
            // Compile the jrxml file
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            JRBeanCollectionDataSource studentDataSource = new JRBeanCollectionDataSource(call(), true);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, studentDataSource ); //  new UsersJRDataSource()     new JRBeanCollectionDataSource(this.call()) 
            
            // File file = new File("F.pdf");
            
            // JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(file) );
            
            
            JasperViewer jasview = new JasperViewer( jasperPrint, false );
            jasview.setAlwaysOnTop(true);
            jasview.setExtendedState(JasperViewer.MAXIMIZED_BOTH | jasview.getExtendedState());
            jasview.show();
            
            
            
        } catch (JRException ex) {
            Logger.getLogger(REPORT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    List<Users> call(){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=(Connection) DriverManager.getConnection( "jdbc:mysql://localhost:3306","root","root" );  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            stmt.execute("use springbootdb;");
            ResultSet rs=stmt.executeQuery("select user_id as 'identificacion', firstName as 'nombre' from users;"); 
            List<Users> Nombres;
            Nombres = new ArrayList<>();
            
            String strtmp;
            String strtmp2;
            while(rs.next())  {
                //
                Users us = new Users();
                //
                //
                strtmp = rs.getString(1);
                us.setIdentificacion( strtmp );
                //
                strtmp2 = rs.getString(2);
                us.setNombre( strtmp2 );
                //
                //
                Nombres.add( us );
            }
            con.close();  
            return Nombres;
        }catch(ClassNotFoundException | SQLException e){System.out.println(e);} 
        return null;
    }




}
