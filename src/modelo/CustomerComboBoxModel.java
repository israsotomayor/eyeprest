package modelo;

import dominio.Customer;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class CustomerComboBoxModel extends AbstractListModel implements ComboBoxModel {

    boolean loEncontro;
    List<Customer> items = null;//cadena q recibira los datos a mostrar.
    Customer customer = null;

    public CustomerComboBoxModel(List<Customer>  list) {
        items = list;
    }

    @Override
    public Customer getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public void setSelectedItem(Object item) {
        try{
            customer = (Customer) item;
            fireContentsChanged(this, -1, -1);
        }catch(Exception ex){            
        }        
    }     

    @Override
    public  Customer getSelectedItem() { //metodo implementado por la interface  JComboBoxModel
        return customer;
    }
    
}
