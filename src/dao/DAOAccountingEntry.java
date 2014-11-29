package dao;

import dominio.AccountingEntry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOAccountingEntry {
    
    ConexionBd cone = new ConexionBd();
    Connection conex = null;
    
    public boolean saveAccountingEntry(AccountingEntry aEntry){
        Boolean b;
        conex = cone.conectate();
        
        try{
            
            Statement st = conex.createStatement();            
            String aEntryQuery = "INSERT INTO accounting_entry VALUES(" + aEntry.getAccounting_entry_id() + "," + aEntry.getAe_type() + ",'"+ aEntry.getAe_description() +"','"+aEntry.getAe_date()+"',"+aEntry.getAe_value()+","+aEntry.getPerson().getPerson_id()+")";            
            int rs = st.executeUpdate(aEntryQuery);
            
            if(rs == 1){
                System.out.println("SE GRABO EN CAJA");
                b = true;
            }else{
                System.out.println("NO SE GRABO EN CAJA");
                b = false;
            }
            
            st.close();
            conex.close();
            
        }catch(SQLException ex){
            b = false;
            System.out.println("EL ERROR AL GUARDAR LA ENTRADA ES: " + ex);
        }
        return b;
    }
    
    public long getLastAccountingEntryId(){
        long lastId = -1;
        conex = cone.conectate();
        
        try{
            Statement st = conex.createStatement();
            String lastIdAEntry = "SELECT MAX(accounting_entry.accounting_entry_id) FROM accounting_entry";
            ResultSet rs = st.executeQuery(lastIdAEntry);
            
            if(rs.next()){
                if(rs.getLong(1) == 0){
                    lastId = 0;
                }else{
                    lastId = rs.getLong(1);
                }
            }
            rs.close();
            st.close();
            conex.close();
        }catch(SQLException ex){
            System.out.println("EL ERROR EN ENCONTRAR EL ULTIMO ID DE ACCOUNTING ENTRY ES: " + ex);
        }        
        return lastId;
    }   
}