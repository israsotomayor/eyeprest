package modelo;

import dominio.Loan;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class LoanComboBoxModel extends AbstractListModel implements ComboBoxModel {

    boolean loEncontro;
    List<Loan> items = null;//cadena q recibira los datos a mostrar.
    Loan loan = null;

    public LoanComboBoxModel(List<Loan>  list) {
        items = list;
    }

    @Override
    public Loan getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public void setSelectedItem(Object item) {
        try{
            loan = (Loan) item;
            fireContentsChanged(this, -1, -1);
        }catch(Exception ex){            
        }        
    }     

    @Override
    public Loan getSelectedItem() { //metodo implementado por la interface  JComboBoxModel
        return loan;
    } 
}