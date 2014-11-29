package dao;

import controlador.UCCFee;
import dominio.Loan;
import dominio.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DAOLoan {

    ConexionBd cone = new ConexionBd();
    Connection conex = null;
    private DAOFee daoFee = new DAOFee();

    public void save(Loan loan) {
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();
            String cliente = "INSERT INTO loan VALUES (" + loan.getLoan_id() + ",'" + loan.getLn_number() + "','" + loan.getLn_issuance_date() + "','" + loan.getLn_closing_date() + "','" + loan.getLn_user() + "'," + loan.getLn_amount_issued() + "," + loan.getLn_financing_time() + "," + loan.getLn_fee_number() + "," + loan.getLn_total_amount() + ", '" + loan.getLn_pay_form() + "','" + loan.getLn_method() + "'," + loan.getLn_interest_rate() + ",'" + loan.getLn_state() + "','" + loan.getLn_settlement_date() + "','" + loan.getLn_guarantor_name() + "'," + loan.getLn_member().getPerson_id() + ")";

            if (st.executeUpdate(cliente) == 1) {
                if (daoFee.saveLoanFee(loan)) {
                    System.out.println("SE GUARDO LAS CUOTAS");
                } else {
                    System.out.println("ERROR AL GURDAR LAS CUOTAS");
                }
                JOptionPane.showMessageDialog(null, "PRESTAMO GUARDADO");
            } else {
                System.out.println("ERROR AL GUARDAR");
            }
            st.close();
            conex.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo ejecutar la consulta " + ex);
        }
    }

    public long getLastIdLoan() {
        long lastId = -1;
        conex = cone.conectate();
        try {
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(loan.loan_id) FROM loan");

            if (rs.next()) {
                if (rs.getLong(1) == 0) {
                    lastId = 0;
                } else {
                    lastId = rs.getLong(1);
                }
            }
            rs.close();
            st.close();
            conex.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
        }
        return lastId;
    }
    
    public int getLastLoanNumber() {
        int lastId = -1;
        conex = cone.conectate();
        try {
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(loan.ln_number) FROM loan");

            if (rs.next()) {
                if (rs.getLong(1) == 0) {
                    lastId = 0;
                } else {
                    lastId = Integer.parseInt(rs.getString(1));
                }
            }
            rs.close();
            st.close();
            conex.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
        }
        return lastId;
    }

    public Loan getLoanPerPerson(Person person) {
        conex = cone.conectate();
        Loan loanObject = new Loan();

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
                loanObject.setLn_member(person);
            }
            rs.close();
            st.close();
            conex.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return loanObject;
    }
}