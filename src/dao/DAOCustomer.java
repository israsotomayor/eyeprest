package dao;

import controlador.UCCAddress;
import controlador.UCCFee;
import controlador.UCCInput;
import controlador.UCCLoan;
import dominio.Address;
import dominio.Customer;
import dominio.Guarantor;
import dominio.Loan;
import dominio.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOCustomer {
    
    ConexionBd cone = new ConexionBd();
    Connection conex = null;
    
    public List<Customer> getCustomerPerCriteria(String text) {
        List<Customer> listCustomer = new ArrayList<>();
        UCCAddress uccAddress = new UCCAddress();
        UCCInput uccInput = new UCCInput();
        UCCLoan uccLoan = new UCCLoan();
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();
            String query = "SELECT * FROM person WHERE person.pn_identification LIKE '%" + text + "%' OR person.pn_first_name LIKE '%" + text + "%' OR person.pn_last_name LIKE '%" + text +"%'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                //System.out.println("PARA VER SI ENTRA");
                Customer c = new Customer();
                c.setPerson_id(rs.getLong("person.person_id"));
                c.setPn_first_name(rs.getString("person.pn_first_name"));
                c.setPn_last_name(rs.getString("person.pn_last_name"));
                c.setPn_type(rs.getInt("person.pn_person_type"));
                c.setPn_born_date(rs.getDate("person.pn_born_date"));
                c.setPn_identification(rs.getString("person.pn_identification"));
                c.setPn_identification_type(rs.getString("person.pn_identification_type"));
                c.setPn_email(rs.getString("person.pn_email"));
                c.setCt_number(rs.getString("person.pn_ct_number"));
                c.setCt_warranty_number(rs.getString("person.pn_ct_warranty_number"));
                c.setLoan(uccLoan.getLoanPerPerson(c));
                c.setListAddress(uccAddress.getAddressPerPerson(c));
                c.setListInput(uccInput.getInputListPerCustomer(c));
                listCustomer.add(c);
            }
            //System.out.println("Aqui esta el texto " + text);
            //System.out.println("Aqui esta la lista " + listCustomer);
            st.close();
            rs.close();
            conex.close();

        } catch (SQLException sq) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes, por error en: " + sq);
        }
        return listCustomer;
    }
    ///CHEQUEARSTO PARA ARREGLAR PORQUE SALEERROR CUANDO ESTA DENTRO DEL DAO ADDRESS
    public List<Address> getAddressPerCustomer(Person ct) {
        conex = cone.conectate();
        List<Address> listAddress = new ArrayList<>();

        try {
            Statement st = conex.createStatement();
            String query = "SELECT * FROM address WHERE address.adr_person_id = " + ct.getPerson_id();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                //System.out.println("EL ID PARA RECUPERAR EL ADDRESS ES: " + ct.getPerson_id());
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
    
     public List<Customer> getCustomerPerLoan(String text) {
        conex = cone.conectate();
        List<Customer> listCustomer = new ArrayList<>();       

        try {
            Statement st = conex.createStatement();
            String query = "SELECT * FROM person,loan WHERE person.person_id = loan.ln_customer_id AND (person.pn_identification LIKE '%" + text + "%' OR person.pn_first_name LIKE '%" + text + "%' OR person.pn_last_name LIKE '%" + text +"%')";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                //System.out.println("PARA VER SI ENTRA");
                Customer c = new Customer();
                //List<Address> aList;
                c.setPerson_id(rs.getLong("person.person_id"));
                c.setPn_first_name(rs.getString("person.pn_first_name"));
                c.setPn_last_name(rs.getString("person.pn_last_name"));
                c.setPn_type(rs.getInt("person.pn_person_type"));
                c.setPn_born_date(rs.getDate("person.pn_born_date"));
                c.setPn_identification(rs.getString("person.pn_identification"));
                c.setPn_identification_type(rs.getString("person.pn_identification_type"));
                c.setPn_email(rs.getString("person.pn_email"));
                c.setCt_number(rs.getString("person.pn_ct_number"));
                c.setCt_warranty_number(rs.getString("person.pn_ct_warranty_number"));
                //System.out.println("ESTE ES EL CLIENTE " + c.getPerson_id());
                //aList = getAddressPerCustomer(c);
                c.setListAddress(getAddressPerCustomer(c));
                c.setLoan(getLoanPerPerson(c));
                
                listCustomer.add(c);
            }
            
            //System.out.println("Aqui esta la lista " + listCustomer);
            rs.close();
            st.close();
            
            conex.close();

        } catch (SQLException sq) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes, por error en: " + sq);
        }
        return listCustomer;
    }
     
     public Guarantor getGuarantorPerLoanGuarantorId(Long loanGuarantorId) {
        Guarantor guarantor = new Guarantor();
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
                //System.out.println("EL IDE DEL GARANTE ES " + guarantor.getPerson_id());
                List<Address> a = getAddressPerCustomer(guarantor);
                guarantor.setLoan(getLoanPerPerson(guarantor));
                
                //listPerson.add(guarantor);
            }
            st.close();
            rs.close();
            conex.close();

        } catch (SQLException sq) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes, por error en: " + sq);
        }
        return guarantor;
    }
     
      public Loan getLoanPerPerson(Person person) {
        conex = cone.conectate();
        Loan loanObject = new Loan();
        //List<Loan> loanList = new ArrayList<>();
        UCCFee uccFee = new UCCFee();
        try {

            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM loan WHERE loan.ln_customer_id =" + person.getPerson_id());

            while (rs.next()) {
                
                loanObject.setLoan_id(rs.getLong("loan.loan_id"));
                loanObject.setLn_number(rs.getString("loan.ln_number"));
                loanObject.setLn_issuance_date(rs.getDate("loan.ln_issuance_date"));
                loanObject.setLn_closing_date(rs.getDate("loan.ln_closing_date"));
                loanObject.setLn_user(rs.getString("loan.ln_user"));
                loanObject.setLn_amount_issued(rs.getBigDecimal("loan.ln_amount_issued"));
                loanObject.setLn_financing_time(rs.getBigDecimal("loan.ln_financing_time"));
                loanObject.setLn_fee_number(rs.getBigDecimal("loan.ln_fee_number"));
                loanObject.setLn_total_amount(rs.getBigDecimal("loan.ln_total_amount"));
                loanObject.setLn_pay_form(rs.getString("loan.ln_pay_form"));
                loanObject.setLn_method(rs.getString("loan.ln_method"));
                loanObject.setLn_interest_rate(rs.getBigDecimal("loan.ln_interest_rate"));
                loanObject.setLn_state(rs.getString("loan.ln_state"));
                loanObject.setLn_settlement_date(rs.getDate("loan.ln_settlement_date"));
                loanObject.setLn_fee(uccFee.getFeePerLoan(loanObject));
                loanObject.setLn_guarantor_name(rs.getString("loan.ln_guarantor_name"));
                loanObject.setLn_member(person);//uccCustomer.getCustomerPerLoanCustomerId(loanObject));//uccCustomer.getCustomerPerLoanCustomerId(rs.getLong("loan.ln_customer_id")));
               

                //loanList.add(loanObject);
            }
            rs.close();
            st.close();
            conex.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return loanObject;
    }
     
     public Customer getCustomerPerLoanCustomerId(Loan loan) {
        //List<Person> listPerson = new ArrayList<>();
        conex = cone.conectate();
        Customer customer = new Customer();;
       

        try {
            Statement st = conex.createStatement();
            //System.out.println(""+loan.getLn_member().getPn_first_name());
            String query = "SELECT * FROM person WHERE person.person_id =" + loan.getLn_member().getPerson_id();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //System.out.println("PARA VER SI ENTRA");
                
                customer.setPerson_id(rs.getLong("person.person_id"));
                customer.setPn_first_name(rs.getString("person.pn_first_name"));
                customer.setPn_last_name(rs.getString("person.pn_last_name"));
                customer.setPn_type(rs.getInt("person.pn_person_type"));
                customer.setPn_born_date(rs.getDate("person.pn_born_date"));
                customer.setPn_identification(rs.getString("person.pn_identification"));
                customer.setPn_identification_type(rs.getString("person.pn_identification_type"));
                customer.setPn_email(rs.getString("person.pn_email"));
                customer.setCt_number(rs.getString("person.pn_ct_number"));
                customer.setCt_warranty_number(rs.getString("person.pn_ct_warranty_number"));
                customer.setListAddress(getAddressPerCustomer(customer));
                //System.out.println("AQUI ESTA EL ID "+customer.getPerson_id());
                customer.setLoan(loan);
            }
            rs.close();
            st.close();            
            conex.close();

        } catch (SQLException sq) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes, por error en: " + sq);
        }
        return customer;
    }
}