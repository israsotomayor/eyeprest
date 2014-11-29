package controlador;

import dao.DAOInput;
import dominio.AccountingEntry;
import dominio.Customer;
import dominio.Fee;
import dominio.Input;
import dominio.Loan;
import dominio.Person;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import modelo.CustomerComboBoxModel;
import modelo.InputTableModel;
import utils.Operaciones;
import vistas.InputPanel;
import vistas.LoanChargeView;

public class UCCInput {

    private JTextField loanSearchNameText;
    private JTextField loanSearchGuarantorText;
    private JTextField customerInputSearchText;
    private Customer customer;
    private UCCCustomer uccCustomer = new UCCCustomer();
    private UCCAccountingEntry uccAEntry = new UCCAccountingEntry();

    public boolean save(LoanChargeView lcv,int typeInput, BigDecimal valueType, Person customer) {
        DAOInput di = new DAOInput();
        boolean b = false;

        Input input = new Input();
        AccountingEntry aEntry = new AccountingEntry();

        Long lastInputId = di.getLastInputId();
        Long inputId = lastInputId + 1;
        int inputNumber = inputId.intValue();        
        

        input.setInput_id(inputId);
        input.setIpt_number(inputNumber);        
        input.setIpt_type(typeInput);
        input.setIpt_value(valueType);
        input.setIpt_date(Operaciones.formatearDate(Operaciones.formatearFecha(lcv.paymentDetailFeePayDATE)));
        input.setIpt_person_id(customer);

        //REVISAR EL ORDEN DE GUARDADO PRIMERO DEBE SER EL APORTE LUEGO LA ENTRADA
        if (di.saveInput(input)) {
            b = true;
            
            //-----------------------> Cargamos la transacción del aporte para agregarlo en el asiento contable <--------------------------

            Long lastAEntryId = uccAEntry.getLastAccountingEntryId();
            Long aEntryId = lastAEntryId + 1;

            aEntry.setAccounting_entry_id(aEntryId);
            aEntry.setAe_type(0);
            String descText;
            if(typeInput == 0){
                descText = "APORTE"; 
            }else{
                descText = "FONDO MORTUORIO";
            }
            aEntry.setAe_description("PAGO DE "+ descText +" DEL SOCIO " + customer.getPn_first_name() + " " + customer.getPn_last_name());
            aEntry.setAe_date(Operaciones.formatearDate(Operaciones.formatearFecha(lcv.paymentDetailFeePayDATE)));
            aEntry.setAe_value(valueType);
            aEntry.setPerson(customer);
            uccAEntry.saveAccountingEntry(aEntry);
            
            //-------------------------> Actualizamos la tabla con la nueva entrada del aporte <-----------------------------
            //InputTableModel model = (InputTableModel) ip.inputTable.getModel();
            //model.getFilas().add(input);
            //ip.inputTable.setModel(model);
            //ip.inputTable.revalidate();
            
            //--------------------------> Actualizamos el total de aportes en la ventana <------------------------------------
            
            //ip.inputTotalTXT.setText(calculateTotalInput());
        }

        return b;

    }

    public List<Input> getInputListPerCustomer(Person person) {

        DAOInput di = new DAOInput();
        return di.getInputListPerCustomer(person);
    }

    //-------------------------Sección de la ventana InputPanel------------------------------->>>>
    //-----Para manejo de aportes lo que es pago de cuotas de aporte semanal de socios.
    //Para cargar un cliente en el JComboBox segun el criterio de búsqueda el cual estara definido por el nombre del cliente. 
    public void chargeCustomerCombo(final JComboBox customerSearchInputJCB) {

        customerInputSearchText = (JTextField) customerSearchInputJCB.getComponent(2);
        customerInputSearchText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == 27) {
                    customerSearchInputJCB.setPopupVisible(false);
                    return;
                }
                if (evt.getKeyCode() == 10) {
                    if (customerSearchInputJCB.getSelectedIndex() != - 1) {

                        CustomerComboBoxModel ccbm = (CustomerComboBoxModel) customerSearchInputJCB.getModel();
                        customer = ccbm.getSelectedItem();
                        customerCharge();

                    }
                    customerSearchInputJCB.setPopupVisible(false);
                    return;
                }

                if (evt.getKeyCode() == 40 || evt.getKeyCode() == 39 || evt.getKeyCode() == 38 || evt.getKeyCode() == 37
                        || evt.getKeyCode() == 36 || evt.getKeyCode() == 16 || evt.getKeyCode() == 35) {
                    return;
                }

                int position = customerInputSearchText.getCaretPosition();
                String s = customerInputSearchText.getText();
                customerSearchInputJCB.setModel(new CustomerComboBoxModel(uccCustomer.getCustomerPerCriteria(customerInputSearchText.getText())));
                customerInputSearchText.setText(s);
                customerInputSearchText.setCaretPosition(position);
                customerSearchInputJCB.setPopupVisible(true);
            }
        });
    }

    public void customerCharge() {

    }

    public BigDecimal calculateLoanBalance(Loan loan) {
        BigDecimal loanBalance = new BigDecimal("0.0");

        for (Fee ln_fee : loan.getLn_fee()) {

            if (ln_fee.getFe_state().equals("Pendiente")) {
                loanBalance.add(ln_fee.getFe_payment());
            }

        }
        //System.out.println("EL TOTAL ES: " + loanBalance);
        return loanBalance;
    }

    public void chargeInput(InputPanel ip) {
        CustomerComboBoxModel ccbm = (CustomerComboBoxModel) ip.inputCustomerSearchJCB.getModel();
        customer = ccbm.getSelectedItem();
        //System.out.println("EL NUMERO DE PRESTAMO ES: " + customer.getLoan().getLn_number());
        //mlv.loanNumberMLTXT.setText(customer.getLoan().getLn_number());
        ip.inputNameTXT.setText(customer.getPn_first_name() + " " + customer.getPn_last_name());
        //mlv.loanCustomerMLTXT.setText();
        ip.inputAddressTXT.setText(customer.getListAddress().get(0).getAdr_street());
        ip.inputIdentificationTXT.setText(customer.getPn_identification());
        ip.inputTotalTXT.setText(calculateTotalInput());

        ip.inputTable.setModel(new InputTableModel(customer.getListInput()));
        ip.inputTable.revalidate();

    }

    public String calculateTotalInput() {
        BigDecimal totalInput = new BigDecimal("0.0");

        for (int i = 0; i < customer.getListInput().size(); i++) {
            totalInput.add(customer.getListInput().get(i).getIpt_value());
        }
        return "" + totalInput;
    }

    public void loanSearchJCBPopupMenuWillBecomeInvisible(InputPanel ip) {
        try {
            CustomerComboBoxModel ccbm = (CustomerComboBoxModel) ip.inputCustomerSearchJCB.getModel();
            Customer c = ccbm.getSelectedItem();
            if (c != null) {
                chargeInput(ip);
            }
        } catch (Exception ex) {
        }
    }
}