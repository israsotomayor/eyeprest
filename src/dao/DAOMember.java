package dao;

import java.sql.Connection;

public class DAOMember {
    
    ConexionBd cone = new ConexionBd();
    Connection conex = null;

//    //Se conecta con la base de datos y permite guardar un nuevo cliente
//    public void save(Client client) {
//        conex = cone.conectate();
//
//        try {
//            Statement st = conex.createStatement();
//            String cliente = "INSERT INTO client VALUES (" + client.getIdCliente() + ",'" + client.getNombre() + "','" + client.getApellido() + "','" + client.getDireccion() + "','" + client.getTelefono() + "','" + client.getCedula() + "')";
//
//            if (st.executeUpdate(cliente) == 1) {
//                JOptionPane.showMessageDialog(null, "CLIENTE GUARDADO CON ÉXITO", "EYETIVE", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                System.out.println("Mal historial");
//            }
//
////            }
//            st.close();
//            conex.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//    
//    public Customer getLastIdCliente() {
//        long lastId = -1;
//        System.out.println("PARA VER SI ENTRA");
//        conex = cone.conectate();
//        
//        
//        
//        try {
//            Statement st = conex.createStatement();
//            ResultSet rs = st.executeQuery("SELECT MAX(client.client_id) FROM client");
//
//            if (rs.next()) {
//                if (rs.getLong(1) == 0) {
//
//                    lastId = 0;
//                    System.out.println("EL ID ES" + lastId);
//                    return lastId;
//
//                } else {
//                    lastId = rs.getLong(1);
//                    System.out.println("EL ID  NO ES" + lastId);
//                    return lastId;
//                }
//            }
//
//            rs.close();
//            st.close();
//            conex.close();
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
//            return lastId;
//        }
//        return lastId;
//    }
//
//    public Customer getMember(Long id) {
//        conex = cone.conectate();
//        Customer member = new Customer();
//        if (conex == null) {
//            JOptionPane.showMessageDialog(null, "Lo sentimos la operación no se terminó con éxito", "FALLO CONEXIÓN", 0);
//            return false;
//        }
//        try {
//            Statement sentencia = conex.createStatement();
//            String ced = "SELECT * FROM member";
//            ResultSet rs = (ResultSet) sentencia.executeQuery(ced);
//            while (rs.next() == true) {
//                String cedul = rs.getString("cedula");
//                if (cedul.equals(cedula)) {
//                    System.out.println("Ya existe");
//                    member.setMember_id(rs.getLong("member.member_id"));
//                    
//                    
//                    
//                    
//                }
//            }
//            rs.close();
//            sentencia.close();
//            conex.close();
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + ex, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
//            return false;
//        }
//        System.out.println("AQUI ESTA EL ID QUE RECUPERO" + member.getMember_id());
//        return member;
//    }
        //return false;
    
//
//    public void remove(Client c) {
//        conex = cone.conectate();
//        try {
//            Statement st = conex.createStatement();
//            int rpac = st.executeUpdate("delete from client where client_id = " + c.getIdCliente());
//            System.out.println("rprue: " + rpac);
//            if (rpac == 1) {
//                JOptionPane.showMessageDialog(null, "Cliente ha sido eliminado", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
//            }
//            st.close();
//            conex.close();
//        } catch (SQLException sq) {
//            //JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta\n" + sq, "BIEN MAN", JOptionPane.INFORMATION_MESSAGE);
//            
//            System.out.println("EL CLIENTE NO SE PUDO ELIMINAR, POR ERROR EN:"+ sq);
//            JOptionPane.showMessageDialog(null, "EL CLIENTE NO SE PUDO ELIMINAR","MENSAJE",JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//
//    public void update(Client c) {
//        conex = cone.conectate();
//        try {
//            Statement st = conex.createStatement();
//            //ResultSet rs = st.executeQuery("select * from client");
////            while (rs.next()) {
////                if (rs.getString("client.identification").equals(c.getCedula())) {
////                    JOptionPane.showMessageDialog(null, "NO SE PUDEN MODIFICAR LOS DATOS\nLA CEDULA YA PERTENECE A UN CLIENTE ", "MODIFICAR", JOptionPane.INFORMATION_MESSAGE);
////                    return false;
////                }
////            }
//
//            int uno = st.executeUpdate("update client set first_name = '" + c.getNombre() + "',last_name ='" + c.getApellido() + "',address = '" + c.getDireccion() + "',telephone = '" + c.getTelefono() + "', identification = '" + c.getCedula() + "' where client_id=" + c.getIdCliente());
//            System.out.println("EN EL DAO " + c.getIdCliente() + " " + uno);
//            if (uno == 1) {
//                JOptionPane.showMessageDialog(null, "LOS DATOS DEL CLIENTE SE HAN MODIFICADO CORRECTAMENTE", "MODIFICAR", JOptionPane.INFORMATION_MESSAGE);
//            }
//            st.close();
//            conex.close();
//
//        } catch (SQLException sq) {
//            System.out.println("EL CLIENTE NO SE MODIFICO, POR ERROR EN:"+ sq);
//            JOptionPane.showMessageDialog(null, "EL CLIENTE NO SE MODIFICO");
//        }
//    }
//
//    public List<Client> getClientPerCriteria(String text) {
//        List<Client> listClients = new ArrayList<>();
//        conex = cone.conectate();
//
//        try {
//            Statement st = conex.createStatement();
//            String query = "SELECT * FROM client WHERE client.identification LIKE '%" + text + "%' OR client.first_name LIKE '%" + text + "%' OR client.last_name LIKE '%" + text +"%'";
//            ResultSet rs = st.executeQuery(query); // or client.first_name like '" + text + "%' or client.last_name like '" + text + "%'");
//
//            while (rs.next()) {
//                System.out.println("PARA VER SI ENTRA");
//                Client c = new Client();
//                c.setIdCliente(rs.getLong("client.client_id"));
//                c.setNombre(rs.getString("client.first_name"));
//                c.setApellido(rs.getString("client.last_name"));
//                c.setDireccion(rs.getString("client.address"));
//                c.setTelefono(rs.getString("client.telephone"));
//                c.setCedula(rs.getString("client.identification"));
//                listClients.add(c);
//            }
//            System.out.println("Aqui esta el texto " + text);
//            System.out.println("Aqui esta la lista " + listClients);
//            st.close();
//            rs.close();
//            conex.close();
//
//        } catch (SQLException sq) {
//            JOptionPane.showMessageDialog(null, "Error al obtener clientes, por error en: " + sq);
//        }
//        return listClients;
//    }
    
}
