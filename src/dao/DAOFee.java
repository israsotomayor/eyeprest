package dao;

import dominio.Fee;
import dominio.Loan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOFee {

    ConexionBd cone = new ConexionBd();
    Connection conex = null;

    public boolean saveLoanFee(Loan ln) {
        Boolean b;
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();

            int rs = 0;

            for (int i = 0; i < ln.getLn_fee().size(); i++) {

                String itemQuery = "INSERT INTO fee VALUES (" + ln.getLn_fee().get(i).getFee_id() + "," + ln.getLn_fee().get(i).getFe_number() + "," + ln.getLn_fee().get(i).getFe_payment() + ",'" + ln.getLn_fee().get(i).getFe_expiration_date() + "'," + ln.getLn_fee().get(i).getFe_capital() + "," + ln.getLn_fee().get(i).getFe_interest() + ",'" + ln.getLn_fee().get(i).getFe_state() + "'," + ln.getLn_fee().get(i).getFe_loan().getLoan_id() + ")";
                //System.out.println(itemQuery);
                rs = st.executeUpdate(itemQuery);
            }
            if (rs == 1) {
                System.out.println("SI SE GRABO LOS INTEMS");
                b = true;
            } else {
                System.out.println("NO SE GRABO NADA DE ITEMS");
                b = false;
            }
            st.close();
            conex.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
            b = false;
        }
        return b;

    }

    public boolean updateFee(Fee fee) {
        boolean b;
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();
            String feeQuery = "UPDATE fee SET fee.fe_state='" + fee.getFe_state() + "' WHERE fee.fee_id=" + fee.getFee_id();
            int feeResultQuery = st.executeUpdate(feeQuery);

            b = feeResultQuery == 1;

            st.close();
            conex.close();
        } catch (SQLException ex) {
            System.out.println("EL ERROR EN ACTUALIZAR LA CUOTA ES: " + ex);
            b = false;
        }
        return b;
    }

    public boolean updateLastFee(Fee lastFee) {
        boolean b;
        conex = cone.conectate();

        try {
            Statement st = conex.createStatement();
            String lastFeeQuery = "UPDATE fee SET fee.fe_payment= " + lastFee.getFe_payment() + " WHERE fee.fee_id=" + lastFee.getFee_id();
            int feeResultQuery = st.executeUpdate(lastFeeQuery);

            b = feeResultQuery == 1;

            st.close();
            conex.close();
        } catch (SQLException ex) {
            System.out.println("EL ERROR EN ACTUALIZAR LA ULTIMA CUOTA ES: " + ex);
            b = false;
        }
        return b;
    }

    public long getLastIdFee() {
        long lastId = -1;
        conex = cone.conectate();
        try {
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(fee.fee_id) FROM fee");

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
        return lastId;
    }

    public List<Fee> getFeePerLoan(Loan loan) {
        conex = cone.conectate();
        List<Fee> feeList = new ArrayList<>();
        try {
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM fee WHERE fe_loan_id =" + loan.getLoan_id());

            while (rs.next()) {
                Fee feeObject = new Fee();
                feeObject.setFee_id(rs.getLong("fee.fee_id"));
                feeObject.setFe_number(rs.getInt("fee.fe_number"));
                feeObject.setFe_payment(rs.getBigDecimal("fee.fe_payment"));
                feeObject.setFe_expiration_date(rs.getDate("fee.fe_expiration_date"));
                feeObject.setFe_capital(rs.getBigDecimal("fee.fe_capital"));
                feeObject.setFe_interest(rs.getBigDecimal("fee.fe_interest"));
                feeObject.setFe_state(rs.getString("fee.fe_state"));
                feeObject.setFe_loan(loan);

                feeList.add(feeObject);
            }
            st.close();
            conex.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return feeList;
    }

}
