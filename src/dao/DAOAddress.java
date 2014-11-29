package dao;

import dominio.Address;
import dominio.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOAddress {

    ConexionBd cone = new ConexionBd();
    Connection conex = null;

    public void save(Address address) {
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();
            String cliente = "INSERT INTO address VALUES (" + address.getAddress_id() + ",'" + address.getAdr_street() + "','" + address.getAdr_locality() + "','" + address.getAdr_city() + "','" + address.getAdr_country() + "','" + address.getAdr_reference() + "'," + address.getAdr_person_id().getPerson_id() + ")";

            if (st.executeUpdate(cliente) == 1) {
                JOptionPane.showMessageDialog(null, "CLIENTE GUARDADO CON ÉXITO", "EYETIVE", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("ERROR AL GUARDAR CLIENTE");
            }

            st.close();
            conex.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public long getLastAddressId() {
        long lastId = -1;
        conex = cone.conectate();
        try {
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(address.address_id) FROM address");

            if (rs.next()) {
                if (rs.getLong(1) == 0) {

                    lastId = 0;
                    //return lastId;

                } else {
                    lastId = rs.getLong(1);
                    //return lastId;
                }
            }

            rs.close();
            st.close();
            conex.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
            //return lastId;
        }
        //System.out.println("EL ID ESTA BIEN DE LA DIRECCIÓN" + lastId);
        return lastId;
    }

    public List<Address> getAddressPerCustomer(Person ct) {
        conex = cone.conectate();
        List<Address> listAddress = new ArrayList<>();

        try {
            Statement st = conex.createStatement();
            String query = "SELECT * FROM address WHERE address.adr_person_id = " + ct.getPerson_id();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                //System.out.println("PARA VER SI ENTRA");
                Address a = new Address();
                a.setAddress_id(rs.getLong("address.address_id"));
                a.setAdr_street(rs.getString("address.adr_street"));
                a.setAdr_locality(rs.getString("address.adr_locality"));
                a.setAdr_city(rs.getString("address.adr_city"));
                a.setAdr_country(rs.getString("address.adr_country"));
                a.setAdr_reference(rs.getString("address.adr_reference"));
                a.setAdr_person_id(ct);

                listAddress.add(a);
            }
            //System.out.println("Aqui esta el texto " + ct.getPerson_id());
//            System.out.println("Aqui esta la lista " + listAddress.get(0).getAdr_street());

            rs.close();
            st.close();
            conex.close();

        } catch (SQLException sq) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes, por error en: " + sq);
        }
        return listAddress;
    }
}
