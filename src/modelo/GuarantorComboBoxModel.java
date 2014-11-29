package modelo;

import dominio.Customer;
import dominio.Guarantor;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class GuarantorComboBoxModel extends AbstractListModel implements ComboBoxModel {

    boolean loEncontro;
    List<Guarantor> items = null;//cadena q recibira los datos a mostrar.
    Guarantor guarantor = null;

    public GuarantorComboBoxModel(List<Guarantor>  list) {
        items = list;
    }

    @Override
    public Guarantor getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public void setSelectedItem(Object item) {
        try{
            guarantor = (Guarantor) item;
            fireContentsChanged(this, -1, -1);
        }catch(Exception ex){            
        }        
    }     

    @Override
    public  Guarantor getSelectedItem() { //metodo implementado por la interface  JComboBoxModel
        return guarantor;
    }
    
}
