package controlador;

import dao.DAOSetting;
import dominio.Setting;

public class UCCSetting {
    
    public Setting getSetting(){
        DAOSetting ds = new DAOSetting();  
        return ds.getSetting();
    }   
}