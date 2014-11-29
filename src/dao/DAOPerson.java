package dao;

import dominio.Customer;
import dominio.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utils.Operaciones;

public class DAOPerson {
    
    ConexionBd cone = new ConexionBd();
    Connection conex = null;

    //Se conecta con la base de datos y permite guardar un nuevo cliente
    public void save(Person customer) {
        conex = cone.conectate();
        //System.out.print("LA FECHA DE NACIMIENTO ES: " + customer.getPn_born_date()+ " "+ customer.getPn_email());

        try {
            Statement st = conex.createStatement();
            String cliente = "INSERT INTO person VALUES (" + customer.getPerson_id() + ",'" + customer.getPn_first_name() + "','" + customer.getPn_last_name() + "'," + customer.getPn_type() + ",'" + Operaciones.formatearDate(Operaciones.dateFormat(customer.getPn_born_date())) + "','" + customer.getPn_identification_type() + "','" + customer.getPn_identification()+ "','" + customer.getPn_email() + "','" + customer.getPn_profession() + "')";

            if (st.executeUpdate(cliente) == 1) {
                JOptionPane.showMessageDialog(null, "CLIENTE GUARDADO CON ÉXITO", "EYETIVE", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("ERROR AL GUARDAR CLIENTE");
            }

//            }
            st.close();
            conex.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public boolean update(Person customer){
        conex = cone.conectate();
        boolean b = false;
        
        try{
            Statement st = conex.createStatement();
            String customerQuery ="UPDATE person SET person.pn_first_name='" +customer.getPn_first_name()+"',person.pn_last_name='"+customer.getPn_last_name()+"',person.pn_person_type="+customer.getPn_type()+",person.pn_born_date='"+Operaciones.formatearDate(Operaciones.dateFormat(customer.getPn_born_date()))+"',person.pn_identification_type='"+customer.getPn_identification_type()+"',person.pn_identification='"+customer.getPn_identification()+"',person.pn_email='"+customer.getPn_email()+"',person.pn_profession='"+customer.getPn_profession()+"' WHERE person.person_id="+customer.getPerson_id();
            
            int customerResultQuery = st.executeUpdate(customerQuery);
            
            if(customerResultQuery == 1){
                b = true;
                JOptionPane.showMessageDialog(null, "Cliente Actualizado");
            }
            st.close();
            conex.close();
        }catch(SQLException sq){
            System.out.println("ERROR AL ACTUALIZAR EL CLIENTE" + sq);
            b = false;
        }
        return b;
    }
    
    public long getLastIdPerson() {
        long lastId = -1;
        conex = cone.conectate();
        try {
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(person.person_id) FROM person");

            if (rs.next()) {
                if (rs.getLong(1) == 0) {

                    lastId = 0;
                    return lastId;

                } else {
                    lastId = rs.getLong(1);
                    return lastId;
                }
            }

            rs.close();
            st.close();
            conex.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
            return lastId;
        }
        return lastId;
    }
    
     public List<Person> getPersonPerCriteria(String text) {
        List<Person> listPerson = new ArrayList<>();
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();
            String query = "SELECT * FROM person WHERE person.pn_identification LIKE '%" + text + "%' OR person.pn_first_name LIKE '%" + text + "%' OR person.pn_last_name LIKE '%" + text +"%'";
            ResultSet rs = st.executeQuery(query); // or client.first_name like '" + text + "%' or client.last_name like '" + text + "%'");

            while (rs.next()) {
                //System.out.println("PARA VER SI ENTRA");
                Person p = new Person();
                p.setPerson_id(rs.getLong("person.person_id"));
                p.setPn_first_name(rs.getString("person.pn_first_name"));
                p.setPn_last_name(rs.getString("person.pn_last_name"));
                p.setPn_type(rs.getInt("person.pn_person_type"));
                p.setPn_born_date(rs.getDate("person.pn_born_date"));
                p.setPn_identification(rs.getString("person.pn_identification"));
                p.setPn_identification_type(rs.getString("person.pn_identification_type"));
                p.setPn_email(rs.getString("person.pn_email"));
                p.setPn_profession(rs.getString("person.pn_profession"));
                
                listPerson.add(p);
            }
            //System.out.println("Aqui esta el texto " + text);
            //System.out.println("Aqui esta la lista " + listPerson);
            st.close();
            rs.close();
            conex.close();

        } catch (SQLException sq) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes, por error en: " + sq);
        }
        return listPerson;
    }
     
}
