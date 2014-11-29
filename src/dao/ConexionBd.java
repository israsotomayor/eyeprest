package dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexionBd {

    private Connection conexion = null;

    public Connection conectate() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            setConexion(DriverManager.getConnection("jdbc:mysql://localhost/eyeprest", "root", "root"));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException cnf) {
            JOptionPane.showMessageDialog(null, "No se establecio la conexion");
            setConexion(null);
            //return getConexion();
        }
        return getConexion();
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
