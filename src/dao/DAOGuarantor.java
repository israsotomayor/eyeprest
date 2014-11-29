package dao;

import controlador.UCCAddress;
import controlador.UCCLoan;
import dominio.Guarantor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOGuarantor {
    
    ConexionBd cone = new ConexionBd();
    Connection conex = null;
    
    public List<Guarantor> getGuarantorPerCriteria(String text) {
        List<Guarantor> listGuarantor = new ArrayList<>();
        UCCAddress uccAddress = new UCCAddress();
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();
            String query = "SELECT * FROM person WHERE person.pn_identification LIKE '%" + text + "%' OR person.pn_first_name LIKE '%" + text + "%' OR person.pn_last_name LIKE '%" + text +"%'";
            ResultSet rs = st.executeQuery(query); // or client.first_name like '" + text + "%' or client.last_name like '" + text + "%'");

            while (rs.next()) {
                //System.out.println("PARA VER SI ENTRA");
                Guarantor g = new Guarantor();
                g.setPerson_id(rs.getLong("person.person_id"));
                g.setPn_first_name(rs.getString("person.pn_first_name"));
                g.setPn_last_name(rs.getString("person.pn_last_name"));
                g.setPn_type(rs.getInt("person.pn_person_type"));
                g.setPn_born_date(rs.getDate("person.pn_born_date"));
                g.setPn_identification(rs.getString("person.pn_identification"));
                g.setPn_identification_type(rs.getString("person.pn_identification_type"));
                g.setPn_email(rs.getString("person.pn_email"));
                //g.setCt_number(rs.getString("person.pn_ct_number"));
                //g.setCt_warranty_number(rs.getString("person.pn_ct_warranty_number"));
                g.setListAddress(uccAddress.getAddressPerPerson(g));
                              
                listGuarantor.add(g);
            }
            //System.out.println("Aqui esta el texto " + text);
            //System.out.println("Aqui esta la lista " + listGuarantor);
            st.close();
            rs.close();
            conex.close();

        } catch (SQLException sq) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes, por error en: " + sq);
        }
        return listGuarantor;
    }
    
    public int getGuarantorCount(String guarantorName){
        conex = cone.conectate();
        int gCount = 0;
        
        try{
            Statement st = conex.createStatement();
            String query = "SELECT COUNT(ln_guarantor_name) FROM loan WHERE ln_guarantor_name='" + guarantorName+"' AND loan.ln_state='Pendiente'";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                gCount = rs.getInt(1);
                System.out.println("EL CONTADOR ESTA EN " + gCount);
            }
        }catch (SQLException sq){
            System.out.println(sq);
        }
        
        return gCount;
    }
    
    public Guarantor getGuarantorPerLoanGuarantorId(Long loanGuarantorId) {
        //List<Person> listPerson = new ArrayList<>();
        Guarantor guarantor = new Guarantor();
        UCCAddress uccAddress = new UCCAddress();
        UCCLoan uccLoan = new UCCLoan();
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();
            String query = "SELECT * FROM person WHERE person.person_id =" + loanGuarantorId;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //System.out.println("PARA VER SI ENTRA");
                //c = new Customer();
                guarantor.setPerson_id(rs.getLong("person.person_id"));
                guarantor.setPn_first_name(rs.getString("person.pn_first_name"));
                guarantor.setPn_last_name(rs.getString("person.pn_last_name"));
                guarantor.setPn_type(rs.getInt("person.pn_person_type"));
                guarantor.setPn_born_date(rs.getDate("person.pn_born_date"));
                guarantor.setPn_identification(rs.getString("person.pn_identification"));
                guarantor.setPn_identification_type(rs.getString("person.pn_identification_type"));
                guarantor.setPn_email(rs.getString("person.pn_email"));
                //guarantor.setCt_number(rs.getString("person.pn_ct_number"));
                //guarantor.setCt_warranty_number(rs.getString("person.pn_ct_warranty_number"));
                guarantor.setListAddress(uccAddress.getAddressPerPerson(guarantor));
                guarantor.setLoan(uccLoan.getLoanPerPerson(guarantor));
                
                //listPerson.add(guarantor);
            }
            //System.out.println("Aqui esta el texto " + text);
            //System.out.println("Aqui esta la lista " + listPerson);
            st.close();
            rs.close();
            conex.close();

        } catch (SQLException sq) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes, por error en: " + sq);
        }
        return guarantor;
    }
}