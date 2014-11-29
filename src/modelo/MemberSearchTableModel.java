/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import dominio.Person;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class MemberSearchTableModel extends AbstractTableModel {
    String titulo[] = {"Id","Nombre","Apellido","Cédula"};
    private List<Person> filas;
    private Person person;

    public MemberSearchTableModel(List<Person> filas) {
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
        setPerson(getFilas().get(rowIndex));

            switch (columnIndex) {
                case 0:
                    return getPerson().getPerson_id();
                case 1:
                    return getPerson().getPn_first_name();
                case 2:
                    return getPerson().getPn_last_name();
                case 3:
                    return getPerson().getPn_identification();
                 }

        return null;
    }

    /**
     * @return the filas
     */
    public List<Person> getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(List<Person> filas) {
        this.filas = filas;
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
