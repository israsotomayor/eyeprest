package controlador;

import dao.DAOAddress;
import dao.DAOPerson;
import dominio.Address;
import dominio.Customer;
import dominio.Person;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.PersonSearchTableModel;
import utils.Operaciones;
import vistas.CustomerView;

public class UCCPerson {

    private Person pAux;

    public void savePerson(CustomerView cv, int viewState,Person customer) {//JTextField ct_first_name,JTextField ct_last_name,JComboBox ct_type,JDateChooser ct_born_date,
        //JComboBox ct_identification_type, JTextField ct_identification, JTextField ct_email, JComboBox ct_profession, JTextArea ct_address) {

        DAOPerson dc = new DAOPerson();
        DAOAddress da = new DAOAddress();
        //UCCAddress ucca = new UCCAddress();
        //Customer customer = new Customer();
        Address address = new Address();

        long lastIdPerson = dc.getLastIdPerson();
        long idPerson = lastIdPerson + 1;

        long lastAddressId = da.getLastAddressId();
        long addressId = lastAddressId + 1;
        
        System.out.println("AQUI ENTRO PAERA VER EL ESTADO " + viewState + " " + customer.getPerson_id());

        
            if (cv.clientTypeCB.getSelectedItem().equals("Cliente")) {
                System.out.println("AQUI ENTRO PAERA GUARADAR DAO");
                customer.setPerson_id(idPerson);
                customer.setPn_first_name(cv.clientFirstNameTxt.getText());
                customer.setPn_last_name(cv.clientLastNameTxt.getText());
                customer.setPn_type(cv.clientTypeCB.getSelectedIndex());
                customer.setPn_born_date(cv.clientBornDateDC.getDate());
                customer.setPn_identification_type(cv.clientIdentificationTypeCB.getSelectedItem().toString());
                customer.setPn_identification(cv.clientIdentificationTxt.getText());
                customer.setPn_email(cv.clientEmailTxt.getText());
                customer.setPn_profession(cv.clientProfessionCB.getSelectedItem().toString());
                //customer.setCt_warranty_number("2");
                //customer.setCt_number("3");
                address.setAdr_street(cv.clientAddressTA.getText());
                address.setAddress_id(addressId);
                address.setAdr_person_id(customer);

                customer.getListAddress().add(address);

                dc.save(customer);
                da.save(address);
            
        }
    }
    
    public void updatePerson(CustomerView cv, int viewState,Person customer){
        System.out.println("AQUI ENTRO PAERA EDITAR DAO");
                DAOPerson dc = new DAOPerson();
                
                customer.setPn_first_name(cv.clientFirstNameTxt.getText());
                customer.setPn_last_name(cv.clientLastNameTxt.getText());
                customer.setPn_type(cv.clientTypeCB.getSelectedIndex());
                customer.setPn_born_date(cv.clientBornDateDC.getDate());
                customer.setPn_identification_type(cv.clientIdentificationTypeCB.getSelectedItem().toString());
                customer.setPn_identification(cv.clientIdentificationTxt.getText());
                customer.setPn_email(cv.clientEmailTxt.getText());
                customer.setPn_profession(cv.clientProfessionCB.getSelectedItem().toString());
                
                dc.update(customer);
                cv.dispose();
    }
    
    public void removePerson(CustomerView cv){
        JOptionPane.showMessageDialog(null, "CLIENTE ELIMINADO");
        cv.dispose();
    }

    public List<Person> getPersonPerCriteria(String text) {
        DAOPerson dp = new DAOPerson();
        return dp.getPersonPerCriteria(text);
    }

    public void searchPersonTxtKeyRelased(JTable personSearchTable, String criteria) {

        personSearchTable.setModel(new PersonSearchTableModel(getPersonPerCriteria(criteria)));
        personSearchTable.getColumnModel().getColumn(1).setPreferredWidth(190);
        personSearchTable.getColumnModel().getColumn(2).setCellRenderer(Operaciones.getRenderCellToRigth());
        personSearchTable.revalidate();
    }

    public void searchPersonTableMouseClicked(JDialog dialog, int indicador, MouseEvent evt, JTable personSearchTable) {
        if (evt.getClickCount() == 2) {
            PersonSearchTableModel ptm = (PersonSearchTableModel) personSearchTable.getModel();
            setpAux(ptm.getFilas().get(personSearchTable.getSelectedRow()));

            System.out.println("EL VALOR DEL ID " + pAux.getPerson_id());

            dialog.dispose();

            if (indicador == 2) {
                CustomerView cv = new CustomerView(null, true, indicador, pAux);
                cv.clientFirstNameTxt.setText(pAux.getPn_first_name());
                cv.clientLastNameTxt.setText(pAux.getPn_last_name());
                cv.clientTypeCB.setSelectedIndex(pAux.getPn_type());
                cv.clientBornDateDC.setDate(pAux.getPn_born_date());
                cv.clientIdentificationTypeCB.setSelectedItem(pAux.getPn_identification_type());
                cv.clientIdentificationTxt.setText(pAux.getPn_identification());
                cv.clientEmailTxt.setText(pAux.getPn_email());
                cv.clientProfessionCB.setSelectedItem(pAux.getPn_profession());
               //cv.clientAddressTA.setText(pAux.getListAddress().get(0).getAdr_street());

                cv.clientSaveBT.setText("EDITAR");

                cv.setVisible(true);

            } else if (indicador == 3) {
                //new CustomerView(null, true,indicador, pAux).setVisible(true);
                CustomerView cv = new CustomerView(null, true, indicador, pAux);

                cv.clientFirstNameTxt.setEditable(false);
                cv.clientLastNameTxt.setEditable(false);
                cv.clientTypeCB.setEnabled(false);
                cv.clientBornDateDC.setEnabled(false);
                cv.clientIdentificationTypeCB.setEnabled(false);
                cv.clientIdentificationTxt.setEditable(false);
                cv.clientEmailTxt.setEditable(false);
                cv.clientProfessionCB.setEnabled(false);
                cv.clientAddressTA.setEditable(false);

                cv.clientFirstNameTxt.setText(pAux.getPn_first_name());
                cv.clientLastNameTxt.setText(pAux.getPn_last_name());
                cv.clientTypeCB.setSelectedIndex(pAux.getPn_type());
                cv.clientBornDateDC.setDate(pAux.getPn_born_date());
                cv.clientIdentificationTypeCB.setSelectedItem(pAux.getPn_identification_type());
                cv.clientIdentificationTxt.setText(pAux.getPn_identification());
                cv.clientEmailTxt.setText(pAux.getPn_email());
                cv.clientProfessionCB.setSelectedItem(pAux.getPn_profession());

                cv.clientSaveBT.setText("ELIMINAR");

                cv.setVisible(true);
            }
        }
    }

    /**
     * @return the pAux
     */
    public Person getpAux() {
        return pAux;
    }

    /**
     * @param pAux the pAux to set
     */
    public void setpAux(Person pAux) {
        this.pAux = pAux;
    }

}
