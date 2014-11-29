package modelo;

import dominio.Input;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class InputTableModel extends AbstractTableModel{
    String titulo[] = {"Número","Cliente","Fecha","Valor"};
    private List<Input> filas;
    private Input input;

    public InputTableModel(List<Input> filas) {
        this.filas = filas;
    }

    @Override
    public int getRowCount() {
        return getFilas() != null ? getFilas().size() : 0;//retorna el numero de filas
    }

    @Override
    public int getColumnCount() {

            return titulo.length;
    }

    @Override
    public String getColumnName(int column) {
            return titulo[column];
            
            

    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        setInput(getFilas().get(rowIndex));

            switch (columnIndex) {
                case 0:
                    return getInput().getIpt_number();
                case 1:
                    return getInput().getIpt_person_id().getPn_first_name() + " " + getInput().getIpt_person_id().getPn_last_name();
                case 2:
                    return getInput().getIpt_date();
                case 3:
                    return getInput().getIpt_value();
            }

        return null;
    }



    /**
     * @return the input
     */
    public Input getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(Input input) {
        this.input = input;
    }

    /**
     * @return the filas
     */
    public List<Input> getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(List<Input> filas) {
        this.filas = filas;
    }
}