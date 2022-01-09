/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author ADRIAN_COSTA
 */
public class UsersJRDataSource implements JRDataSource{
    
    
    private int index; 
    List<Users> users;
    public UsersJRDataSource( List users ){
        index = -1;
        this.users = users;
    }
    
    @Override
    public boolean next() throws JRException {
        index++;
        return (index < users.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        String name = jrf.getName();
        String value="";
        // System.out.println("getName: " + jrf.getName());
        switch(name){
            case "nombre":
                value = users.get(index).getNombre();
                break;
            case "identificacion":
                value = users.get(index).getIdentificacion();
                break;
        }
        return value;
    }
    
    public static JRDataSource getDataSource(List users){
        return new UsersJRDataSource(users);
    }
    
}
