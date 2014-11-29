package dao;

import dominio.Setting;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOSetting {

    ConexionBd cone = new ConexionBd();
    Connection conex = null;

    public Setting getSetting() {
        Setting setting = new Setting();
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();
            String query = "SELECT * FROM setting";
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                setting.setSetting_id(rs.getLong("setting.setting_id"));
                setting.setIVA_VALUE(rs.getBigDecimal("setting.iva_value"));
                setting.setINPUT_VALUE(rs.getBigDecimal("setting.input_value"));
                setting.setMORTUARY_VALUE(rs.getBigDecimal("setting.mortuary_value"));
                setting.setINTEREST_VALUE(rs.getBigDecimal("setting.interest_value"));
            }
            rs.close();
            st.close();
            conex.close();

        } catch (SQLException sq) {
            System.out.println("EL ERROR AL OBTENER LA CONFIGURACIÓN: " + sq);
        }
        return setting;
    }

}
