package controlador;

import dao.DAOFee;
import dao.DAOGuarantor;
import dao.DAOLoan;
import dominio.Customer;
import dominio.Fee;
import dominio.Guarantor;
import dominio.Loan;
import dominio.Person;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import modelo.CustomerComboBoxModel;
import modelo.FeeTableModel;
import modelo.GuarantorComboBoxModel;
import utils.Operaciones;
import utils.Report;
import utils.Utilidades;
import vistas.LoanView;
import vistas.LoanChargeView;

public class UCCLoan {

    private BigDecimal capital;
    private Customer customer;
    private Guarantor guarantor;
    private Fee fee;
    private JTextField loanSearchNameText;
    private JTextField loanSearchGuarantorText;
    private JTextField loanSearchText;
    private UCCCustomer uccCustomer = new UCCCustomer();
    private UCCGuarantor uccGuarantor = new UCCGuarantor();
    private UCCInput uccInput = new UCCInput();

    UCCAccountingEntry uccAEntry = new UCCAccountingEntry();
    DAOFee df = new DAOFee();

    public void loanCalculator(LoanView lv) {
        List<Fee> feeList;
        feeList = new ArrayList<>();
        DAOLoan dl = new DAOLoan();
        DAOFee df = new DAOFee();
        Loan loan = new Loan();

        long loanLastId = dl.getLastIdLoan();
        long loanId = loanLastId + 1;

        long feeLastId = df.getLastIdFee();
        long feeId = feeLastId + 1;

        // System.out.println("PARA SI GRABA LOS DATOS "+form.loanMemberTXT.getText());
        double amount = Double.parseDouble(lv.loanAmountTxt.getText());
        int feeNumber = Integer.parseInt(lv.loanFeeTxt.getText());
        double interestRate = Double.parseDouble(lv.loanAnnualInterestTxt.getText());
        String loanMethod = lv.loanMethodJCB.getSelectedItem().toString();
        String paymentMethod = lv.loanPaymentMethodJCB.getSelectedItem().toString();

        double annualInterestLoanAmount = amount * interestRate / 100;
        double monthlyInterestLoanAmount = annualInterestLoanAmount / 12;
        double weeklyInterestLoanAmount = monthlyInterestLoanAmount / 4;
        double generatedInterest = weeklyInterestLoanAmount * feeNumber;
        double totalAmountCalc = amount + generatedInterest;

        int cont = 1;
        for (int i = 0; i < feeNumber; i++) {
            Fee feeObject = new Fee();

            //capital = amount / fee;
            //double loan_fee = capital + weeklyInterestLoanAmount;
            // BigDecimal intre = weeklyInterestLoanAmount;
            feeObject.setFee_id(feeId);
            feeObject.setFe_number(cont);
            //feeObject.setFe_payment(loan_fee);
            feeObject.setFe_expiration_date(Operaciones.formatearDate(Operaciones.formatearFecha(lv.loanStartDateJC)));
            //feeObject.setFe_capital(capital);
            // feeObject.setFe_interest(intre);
            feeObject.setFe_state("Pendiente");
            feeObject.setFe_loan(loan);

            feeList.add(feeObject);
            cont++;
            feeId++;
        }

        lv.loanAmountInfTxt.setText("" + amount);
        lv.loanMonthsInfTxt.setText("" + feeNumber / 4);
        // lv.loanPaymentAmountInfTxt.setText("" + (capital + weeklyInterestLoanAmount));
        lv.totalPaymentInfTxt.setText("" + totalAmountCalc);
//        System.out.println("PARA SI GRABA LOS DATOS "+form.loanMemberTXT.getText());
//        if (optionLoan.equals("loanCalculator")) {
//            amortizationTableCalc.setModel(new CalculatorTableModel(feeList));
//            amortizationTableCalc.getColumnModel().getColumn(1).setPreferredWidth(190);
//            //amortizationTableCalc.setCellEditor(new DefaultCellEditor(new JCheckBox()));
//            //amortizationTableCalc.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JCheckBox()));
//            //amortizationTableCalc.getColumnModel().getColumn(2).setCellRenderer(Operaciones.getRenderCellToRigth());
//            amortizationTableCalc.revalidate();
//        } else {
//            dl.save(loan);
//        }
    }

    public void loanGenerate(LoanView lv, String loanOption) {

        List<Fee> feeList = new ArrayList<>();
        DAOLoan dl = new DAOLoan();
        DAOFee df = new DAOFee();
        Loan loan = new Loan();
        Double varMath = Math.pow(10, 4);

        long loanLastId = dl.getLastIdLoan();
        long loanId = loanLastId + 1;

        long feeLastId = df.getLastIdFee();
        long feeId = feeLastId + 1;

        RoundingMode RM = RoundingMode.HALF_UP;

        BigDecimal amount = new BigDecimal(lv.loanAmountTxt.getText());
        BigDecimal feeNumber = new BigDecimal(lv.loanFeeTxt.getText());
        BigDecimal interestRate = new BigDecimal("" + (Double.parseDouble(lv.loanAnnualInterestTxt.getText()) / 100));
        String loanMethod = lv.loanMethodJCB.getSelectedItem().toString();
        String paymentMethod = lv.loanPaymentMethodJCB.getSelectedItem().toString();
        BigDecimal varMonth = new BigDecimal("12");
        BigDecimal varWeek = new BigDecimal("4");

        BigDecimal annualInterestLoanAmount = amount.multiply(interestRate);
        BigDecimal monthlyInterestLoanAmount = annualInterestLoanAmount.divide(varMonth);
        BigDecimal weeklyInterestLoanAmount = monthlyInterestLoanAmount.divide(varWeek).setScale(2, RM);
        System.out.println("weeklyInterestLoanAmount" + weeklyInterestLoanAmount);
        BigDecimal generatedInterest = weeklyInterestLoanAmount.multiply(feeNumber);
        System.out.println("generatedInterest" + generatedInterest);
        capital = amount.divide(feeNumber, 2, RM);
        System.out.println("capital" + capital);
        BigDecimal totalAmountCalc = capital.multiply(feeNumber).add(generatedInterest);
        System.out.println("totalAmountCalc" + totalAmountCalc);

        lv.loanAmountInfTxt.setText("" + amount);
        lv.loanMonthsInfTxt.setText("" + feeNumber.divide(varWeek));
        lv.loanPaymentAmountInfTxt.setText("" + capital.add(weeklyInterestLoanAmount));
        lv.totalPaymentInfTxt.setText("" + totalAmountCalc);

        if (loanOption.equals("loanGenerateBT")) {
            loan.setLoan_id(loanId);
            //CAMBIAR CON UN METODO QUE CALCULE EL NUMERO DE PRESTAMO DE ACUERDO AL ULTIMO PRESTAMO

            int lastLoanNumber = dl.getLastLoanNumber();
            String loanNumber = "" + (lastLoanNumber + 1);

            loan.setLn_number(lv.loanNumberTxt.getText());
            loan.setLn_issuance_date(Operaciones.formatearDate(Operaciones.formatearFecha(lv.loanStartDateJC)));
            loan.setLn_closing_date(Operaciones.formatearDate(Operaciones.formatearFecha(lv.loanStartDateJC)));
            //CAMBIAR CON UN METODO QUE RELACIONE EL USUARIO QUE ESTA HACIENDO EL PRESTAMO
            loan.setLn_user("ADMIN");
            loan.setLn_amount_issued(amount);
            loan.setLn_financing_time(feeNumber.divide(varWeek));
            loan.setLn_fee_number(feeNumber);
            loan.setLn_total_amount(totalAmountCalc);
            loan.setLn_pay_form(paymentMethod);
            loan.setLn_method(loanMethod);
            loan.setLn_interest_rate(interestRate);
            loan.setLn_state(lv.loanStateCB.getSelectedItem().toString());
            loan.setLn_settlement_date(Operaciones.formatearDate(Operaciones.formatearFecha(lv.loanStartDateJC)));
            loan.setLn_guarantor_name(lv.loanGuarantorNameTXT.getText());
            loan.setLn_member(customer);

            int cont = 1;
            int feeCont = Integer.parseInt(lv.loanFeeTxt.getText());
            for (int i = 0; i < feeCont; i++) {
                Fee feeObject = new Fee();

                //double 
                BigDecimal loan_fee = capital.add(weeklyInterestLoanAmount);
                BigDecimal intre = weeklyInterestLoanAmount;
                feeObject.setFee_id(feeId);
                feeObject.setFe_number(cont);
                feeObject.setFe_payment(loan_fee);
                feeObject.setFe_expiration_date(Operaciones.formatearDate(Operaciones.formatearFecha(lv.loanStartDateJC)));
                feeObject.setFe_capital(capital);
                feeObject.setFe_interest(intre);
                feeObject.setFe_state("Pendiente");
                feeObject.setFe_loan(loan);

                feeList.add(feeObject);
                cont++;
                feeId++;
            }

            loan.setLn_fee(feeList);
            dl.save(loan);
        }

    }

    public void loanCustomerJCBPopupMenuWillBecomeInvisible(JComboBox loanCustomerJCB, JTextField loanCustomerNameTXT, JTextField loanCustomerAddressTXT, JTextField loanCustomerIdentificationTXT) {
        try {
            CustomerComboBoxModel ccbm = (CustomerComboBoxModel) loanCustomerJCB.getModel();
            Customer ct = ccbm.getSelectedItem();
            if (ct != null) {
                chargeCustomerComboBox(loanCustomerJCB, loanCustomerNameTXT, loanCustomerAddressTXT, loanCustomerIdentificationTXT);
            }
        } catch (Exception ex) {
        }
    }

    public void loanGuarantorJCBPopupMenuWillBecomeInvisible(JComboBox loanSearchGuarantorJCB, JTextField loanGuarantorNameTXT, JTextField loanGuarantorAddressTXT, JTextField loanGuarantorIdentificationTXT) {
        try {
            DAOGuarantor dg = new DAOGuarantor();
            GuarantorComboBoxModel gcbm = (GuarantorComboBoxModel) loanSearchGuarantorJCB.getModel();
            Guarantor g = gcbm.getSelectedItem();
            if (g != null) {

                chargeGuarantorComboBox(loanSearchGuarantorJCB, loanGuarantorNameTXT, loanGuarantorAddressTXT, loanGuarantorIdentificationTXT);
//                if(dg.getGuarantorCount(loanGuarantorNameTXT.getText())>=2){
//                    JOptionPane.showMessageDialog(loanSearchText, "YA NO PUEDE SER GARANTE");
//                    
//                }
            }
        } catch (Exception ex) {
        }
    }

    public void chargeCustomerComboBox(JComboBox loanCustomerJCB, JTextField loanCustomerNameTXT, JTextField loanCustomerAddressTXT, JTextField loanCustomerIdentificationTXT) {
        CustomerComboBoxModel ccbm = (CustomerComboBoxModel) loanCustomerJCB.getModel();
        customer = ccbm.getSelectedItem();
        //System.out.println(customer.getPn_identification());
        loanCustomerNameTXT.setText(customer.getPn_first_name() + " " + customer.getPn_last_name());
        loanCustomerAddressTXT.setText(customer.getListAddress().get(0).getAdr_street());
        loanCustomerIdentificationTXT.setText(customer.getPn_identification());
    }

    public void chargeGuarantorComboBox(JComboBox loanSearchGuarantorJCB, JTextField loanGuarantorNameTXT, JTextField loanGuarantorAddressTXT, JTextField loanGuarantorrIdentificationTXT) {
        GuarantorComboBoxModel gcbm = (GuarantorComboBoxModel) loanSearchGuarantorJCB.getModel();
        guarantor = gcbm.getSelectedItem();
        //System.out.println(guarantor.getPn_identification());
        loanGuarantorNameTXT.setText(guarantor.getPn_first_name() + " " + guarantor.getPn_last_name());
        loanGuarantorAddressTXT.setText(guarantor.getListAddress().get(0).getAdr_street());
        loanGuarantorrIdentificationTXT.setText(guarantor.getPn_identification());
    }

    public void chargeCustomerCombo(final JComboBox searchLoanCustomerJCB) {

        loanSearchNameText = (JTextField) searchLoanCustomerJCB.getComponent(2);
        loanSearchNameText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == 27) {
                    searchLoanCustomerJCB.setPopupVisible(false);
                    return;
                }
                if (evt.getKeyCode() == 10) {
                    if (searchLoanCustomerJCB.getSelectedIndex() != - 1) {

                        CustomerComboBoxModel sc = (CustomerComboBoxModel) searchLoanCustomerJCB.getModel();
                        customer = sc.getSelectedItem();
                        customerCharge();

                    }
                    searchLoanCustomerJCB.setPopupVisible(false);
                    return;
                }

                if (evt.getKeyCode() == 40 || evt.getKeyCode() == 39 || evt.getKeyCode() == 38 || evt.getKeyCode() == 37
                        || evt.getKeyCode() == 36 || evt.getKeyCode() == 16 || evt.getKeyCode() == 35) {
                    return;
                }

                int position = loanSearchNameText.getCaretPosition();
                String s = loanSearchNameText.getText();
                searchLoanCustomerJCB.setModel(new CustomerComboBoxModel(uccCustomer.getCustomerPerCriteria(loanSearchNameText.getText())));
                loanSearchNameText.setText(s);
                loanSearchNameText.setCaretPosition(position);
                searchLoanCustomerJCB.setPopupVisible(true);

            }
        });
    }

    public void chargeGuarantorCombo(final JComboBox searchLoanGuarantorJCB) {

        loanSearchGuarantorText = (JTextField) searchLoanGuarantorJCB.getComponent(2);
        loanSearchGuarantorText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == 27) {
                    searchLoanGuarantorJCB.setPopupVisible(false);
                    return;
                }
                if (evt.getKeyCode() == 10) {
                    if (searchLoanGuarantorJCB.getSelectedIndex() != - 1) {

                        GuarantorComboBoxModel gcbm = (GuarantorComboBoxModel) searchLoanGuarantorJCB.getModel();
                        guarantor = gcbm.getSelectedItem();
                        customerCharge();

                    }
                    searchLoanGuarantorJCB.setPopupVisible(false);
                    return;
                }

                if (evt.getKeyCode() == 40 || evt.getKeyCode() == 39 || evt.getKeyCode() == 38 || evt.getKeyCode() == 37
                        || evt.getKeyCode() == 36 || evt.getKeyCode() == 16 || evt.getKeyCode() == 35) {
                    return;
                }

                int position = loanSearchGuarantorText.getCaretPosition();
                String s = loanSearchGuarantorText.getText();
                searchLoanGuarantorJCB.setModel(new GuarantorComboBoxModel(uccGuarantor.getGuarantorPerCriteria(loanSearchGuarantorText.getText())));
                loanSearchGuarantorText.setText(s);
                loanSearchGuarantorText.setCaretPosition(position);
                searchLoanGuarantorJCB.setPopupVisible(true);

            }
        });
    }

    public void customerCharge() {

    }

    //-------------------------Sección de la ventana ManageLoan------------------------------->>>>
    //-----Para manejo de préstamos, lo que es pago de cuotas.
    //Para cargar un prestamo en el JComboBox segun el criterio de búsqueda el cual estara definido por el nombre del beneficiario. 
    public void chargeLoanCombo(final LoanChargeView mlv) {

        loanSearchText = (JTextField) mlv.loanSearchMLJCB.getComponent(2);
        loanSearchText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == 27) {
                    mlv.loanSearchMLJCB.setPopupVisible(false);
                    return;
                }
                if (evt.getKeyCode() == 10) {
                    if (mlv.loanSearchMLJCB.getSelectedIndex() != - 1) {

                        CustomerComboBoxModel ccbm = (CustomerComboBoxModel) mlv.loanSearchMLJCB.getModel();
                        customer = ccbm.getSelectedItem();
                        customerCharge();

                    }
                    mlv.loanSearchMLJCB.setPopupVisible(false);
                    return;
                }

                if (evt.getKeyCode() == 40 || evt.getKeyCode() == 39 || evt.getKeyCode() == 38 || evt.getKeyCode() == 37
                        || evt.getKeyCode() == 36 || evt.getKeyCode() == 16 || evt.getKeyCode() == 35) {
                    return;
                }

                int position = loanSearchText.getCaretPosition();
                String s = loanSearchText.getText();
                //System.out.println("PARA VER PORQUE SALE ERROR " + uccCustomer.getCustomerPerLoan(loanSearchText.getText()));
                mlv.loanSearchMLJCB.setModel(new CustomerComboBoxModel(uccCustomer.getCustomerPerCriteria(loanSearchText.getText())));
                loanSearchText.setText(s);
                loanSearchText.setCaretPosition(position);
                mlv.loanSearchMLJCB.setPopupVisible(true);
            }
        });
    }

    public void chargeLoan(LoanChargeView mlv) {
        CustomerComboBoxModel ccbm = (CustomerComboBoxModel) mlv.loanSearchMLJCB.getModel();
        customer = ccbm.getSelectedItem();
        //System.out.println("EL NUMERO DE PRESTAMO ES: " + customer.getLoan().getLn_number());
        mlv.loanNumberMLTXT.setText(customer.getLoan().getLn_number());
        mlv.loanCustomerMLTXT.setText(customer.getPn_first_name() + " " + customer.getPn_last_name());
        mlv.loanGuarantorMLTXT.setText(customer.getLoan().getLn_guarantor_name());
        mlv.loanAmountMLTXT.setText("" + customer.getLoan().getLn_amount_issued());
        mlv.loanTermMLTXT.setText(customer.getLoan().getLn_financing_time() + " meses");
        mlv.loanIssuanceDateMLTXT.setText("" + customer.getLoan().getLn_issuance_date());
        calculateLoanBalance(mlv);
        mlv.loanInterestRateMLTXT.setText(customer.getLoan().getLn_interest_rate() + " %");

        mlv.loanFeeTableML.setModel(new FeeTableModel(customer.getLoan().getLn_fee()));

        for (int i = 0; i < customer.getLoan().getLn_fee().size(); i++) {
            if (customer.getLoan().getLn_fee().get(i).getFe_state().equals("Pagado")) {
                mlv.loanFeeTableML.getColumnModel().getColumn(5).setCellRenderer(Operaciones.getRenderCellToColor(Color.GREEN));

            } else {
                //  mlv.loanFeeTableML.getColumnModel().getColumn(5).setCellRenderer(Operaciones.getRenderCellToColor(Color.RED));

            }
        }

        mlv.loanFeeTableML.revalidate();

    }

    public void calculateLoanBalance(LoanChargeView mlv) {
        BigDecimal loanBalance = new BigDecimal("0.0");
        //System.out.println("AQUI ESTA EL PRESTAMO: " + customer.getLoan().getLn_number());
        for (int i = 0; i < customer.getLoan().getLn_fee().size(); i++) {
            if (customer.getLoan().getLn_fee().get(i).getFe_state().equals("Pendiente")) {
                loanBalance.add(customer.getLoan().getLn_fee().get(i).getFe_payment());
            }
        }
        mlv.loanBalanceMLTXT.setText("" + loanBalance);
    }

    public void loanSearchJCBPopupMenuWillBecomeInvisible(LoanChargeView mlv) {
        try {
            CustomerComboBoxModel ccbm = (CustomerComboBoxModel) mlv.loanSearchMLJCB.getModel();
            Customer c = ccbm.getSelectedItem();
            if (c != null) {
                chargeLoan(mlv);
            }
        } catch (Exception ex) {
        }
    }

    public void loanFeeTableMLMouseClicked(MouseEvent evt, JTable feeTable, LoanChargeView lcv) {
        if (evt.getClickCount() == 1) {
            FeeTableModel ftm = (FeeTableModel) feeTable.getModel();
            fee = ftm.getFilas().get(feeTable.getSelectedRow());
            System.out.println(" PARA VER SI COGE LA CUOTA: " + fee.getFe_loan().getLn_number());

            lcv.paymentDetailFeeNumberTXT.setText("" + fee.getFe_number());
            lcv.paymentDetailFeeAmountTXT.setText("" + fee.getFe_payment());
            lcv.paymentDetailCashedTXT.setText("" + fee.getFe_loan().getLn_total_amount());

//            FeePaymentView fpv = new FeePaymentView(null, true, fee);
//            fpv.paymentDetailLoanNumberTXT.setText(fee.getFe_loan().getLn_number());
//            fpv.paymentDetailFeeAmountTXT.setText("" + fee.getFe_payment());
//            fpv.paymentDetailLoanBalanceTXT.setText("" + fee.getFe_loan().getLn_total_amount());
//            fpv.paymentDetailCashedTXT.setText("" + fee.getFe_payment());
//            fpv.setVisible(true);
            calculateLoanBalance(lcv);
        }
    }

    public void payment(LoanChargeView lcv) {

        if (lcv.paymentDetailAmountTXT.getText().trim().equals("") || !lcv.weeklyIntakeOption.isSelected() || !lcv.mortuaryBackgroundOption.isSelected()) {
            JOptionPane.showMessageDialog(lcv, "CAMPOS VACIOS");
        } else {
            UCCSetting uccSetting = new UCCSetting();
            if(fee != null){
                

            BigDecimal feeAmount = new BigDecimal(lcv.paymentDetailFeeAmountTXT.getText());
            BigDecimal feePaymentAmount = new BigDecimal(lcv.paymentDetailAmountTXT.getText());
            BigDecimal payFee = feePaymentAmount.subtract(feeAmount);

            fee.setFe_state("Pagado");

            if (Objects.equals(feeAmount, feePaymentAmount)) {

                df.updateFee(fee);
                saveInput(lcv, uccSetting);
                uccAEntry.saveFeeAccountingEntry(lcv, fee);
                System.out.println("PARA SI TIENE DATOS " + fee.getFe_loan().getLn_member().getPn_first_name());
                Report.printInput(fee, fee.getFe_loan().getLn_member());

                //fpv.dispose();
                // uccLoan.calculateLoanBalance();
            } else {

                List<Fee> feeList = df.getFeePerLoan(fee.getFe_loan());
                Fee lastFee = feeList.get(feeList.size() - 1);

                lastFee.setFe_payment(lastFee.getFe_payment().subtract(payFee));
                df.updateFee(fee);
                saveInput(lcv, uccSetting);
                df.updateLastFee(lastFee);
                uccAEntry.saveFeeAccountingEntry(lcv, fee);
                Report.printInput(fee, fee.getFe_loan().getLn_member());
            }
            }else{
                saveInput(lcv, uccSetting);
            }

            
        }
    }
    
    public void saveInput(LoanChargeView lcv,UCCSetting uccSetting){
        if (lcv.weeklyIntakeOption.isSelected()) {
                    uccInput.save(lcv, 0, uccSetting.getSetting().getINPUT_VALUE(), customer);
                }
                if (lcv.mortuaryBackgroundOption.isSelected()) {
                    uccInput.save(lcv, 1, uccSetting.getSetting().getMORTUARY_VALUE(), customer);
                }
    }

    public void paymentControl(LoanChargeView lcv) {
        if (!lcv.paymentDetailAmountTXT.getText().trim().equals("") || lcv.weeklyIntakeOption.isSelected() || lcv.mortuaryBackgroundOption.isSelected()) {
            JOptionPane.showMessageDialog(lcv, "BIEN");
        } else {
            JOptionPane.showMessageDialog(lcv, "CAMPOS VACIOS");
        }
    }

    public void clearLoanComponents(LoanView lv) {
        //Limpiar campos de cliente
        lv.loanCustomerNameTXT.setText("");
        lv.loanCustomerAddressTXT.setText("");
        lv.loanCustomerIdentificationTXT.setText("");

        //Limpiar campos de garante
        lv.loanGuarantorNameTXT.setText("");
        lv.loanGuarantorAddressTXT.setText("");
        lv.loanGuarantorIdentificationTXT.setText("");

        //Limpiar campos referentes a los datos de préstamo
        int loanNumber = Integer.parseInt(lv.loanNumberTxt.getText()) + 1;
        lv.loanNumberTxt.setText("" + loanNumber);
        lv.loanAmountTxt.setText("");
        lv.loanFeeTxt.setText("");
        lv.loanAnnualInterestTxt.setText("");
        lv.loanAmountInfTxt.setText("");
        lv.loanMonthsInfTxt.setText("");
        lv.loanPaymentAmountInfTxt.setText("");
        lv.totalPaymentInfTxt.setText("");
        lv.loanGenerateBT.setEnabled(false);

        //Limpiar los campos de busquedas de cliente y garante
        lv.loanCustomerJCB.setModel(new DefaultComboBoxModel());
        lv.loanSearchGuarantorJCB.setModel(new DefaultComboBoxModel());

        //Reseteamos los onjetos clientes y garantes
        customer = null;
        guarantor = null;
    }

    public Loan getLoanPerPerson(Person person) {
        DAOLoan dl = new DAOLoan();
        return dl.getLoanPerPerson(person);
    }

}
