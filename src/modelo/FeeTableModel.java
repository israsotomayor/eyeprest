package modelo;

import dominio.Fee;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FeeTableModel extends AbstractTableModel{
    String titulo[] = {"Cuota","Pago","Vence El:","Capital","Intereses","Estado"};
    private List<Fee> filas;
    private Fee fee;

    public FeeTableModel(List<Fee> filas) {
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
        setFee(getFilas().get(rowIndex));

            switch (columnIndex) {
                case 0:
                    return getFee().getFe_number();
                case 1:
                    return getFee().getFe_payment();
                case 2:
                    return getFee().getFe_expiration_date();
                case 3:
                    return getFee().getFe_capital();
                case 4:
                    return getFee().getFe_interest();
                case 5:
                    return getFee().getFe_state();
            }

        return null;
    }

/**
     * @return the filas
     */
    public List<Fee> getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(List<Fee> filas) {
        this.filas = filas;
    }

    /**
     * @return the fee
     */
    public Fee getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(Fee fee) {
        this.fee = fee;
    }
}