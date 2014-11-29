package dao;

import dominio.Input;
import dominio.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOInput {
    
    ConexionBd cone = new ConexionBd();
    Connection conex = null;
    
    public boolean saveInput(Input input){
        boolean b;
        conex = cone.conectate();
        
        try{
            
            Statement st = conex.createStatement();
            String inputQuery = "INSERT INTO input VALUES("+input.getInput_id()+","+input.getIpt_number()+","+input.getIpt_type()+","+input.getIpt_value()+",'"+input.getIpt_date()+"',"+input.getIpt_person_id().getPerson_id()+")";
            int rs = st.executeUpdate(inputQuery);
            
            if(rs == 1){
                //System.out.println("SI SE GRABO LOS INTEMS");
                JOptionPane.showMessageDialog(null, "APORTE GUARDADO");
                b=true;
            }else{
                //System.out.println("SI SE GRABO LOS INTEMS");
                JOptionPane.showMessageDialog(null, "APORTE NO GUARDADO");
                b = false;
            }         
                       
            st.close();
            conex.close();
            
        }catch(SQLException ex){
            
            b = false;
            System.out.println("EL ERROR AL GUARDAR EL APORTE ES: " + ex);
        }
        return b;
    }
    
    public Long getLastInputId(){
        long lastId = -1;
        conex = cone.conectate();
               
           try{
            Statement st = conex.createStatement();
            String query = "SELECT MAX(input.input_id) FROM input";
            ResultSet rs = st.executeQuery(query);
            
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
            System.out.println("EL ERROR AL OBTENER EL ULTIMO ID DEL APORTE ES: " +ex);
        }
        
        
        return lastId;
    }
    
    public List<Input> getInputListPerCustomer(Person person){
        conex = cone.conectate();
        List<Input> inputList = new ArrayList<>();
        
        try{
            
            Statement st = conex.createStatement();
            String inputQuery = "SELECT * FROM input WHERE ipt_person_id=" + person.getPerson_id();
            ResultSet rs = st.executeQuery(inputQuery);
            
            while(rs.next()){
                Input input = new Input();
                input.setInput_id(rs.getLong("input.input_id"));
                input.setIpt_number(rs.getInt("input.ipt_number"));
                input.setIpt_value(rs.getBigDecimal("input.ipt_value"));
                input.setIpt_date(rs.getDate("input.ipt_date"));
                input.setIpt_person_id(person);
                
                inputList.add(input);
                
            }
            
            rs.close();
            st.close();
            conex.close();
            
        }catch(SQLException ex){
            System.out.println("EL ERROR DE LA LISTA DE APORTES ES: " + ex);
        }     
        return inputList;
    }
}