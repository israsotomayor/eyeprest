package controlador;

import com.toedter.calendar.JDateChooser;
import dao.DAOAccountingEntry;
import dominio.AccountingEntry;
import dominio.Fee;
import java.math.BigDecimal;
import java.util.Date;
import utils.Operaciones;
import vistas.LoanChargeView;


public class UCCAccountingEntry {
    
    public boolean saveAccountingEntry(AccountingEntry aEntry){
        DAOAccountingEntry dae = new DAOAccountingEntry();
               
        
       return dae.saveAccountingEntry(aEntry);
    }
    
    public long getLastAccountingEntryId(){
        DAOAccountingEntry dae = new DAOAccountingEntry();
        return dae.getLastAccountingEntryId();
    }
    
    public void saveFeeAccountingEntry(LoanChargeView lcv, Fee fee) {
        long lastAEntryId = getLastAccountingEntryId();
        long aEntryId = lastAEntryId + 1;

        JDateChooser jdc = new JDateChooser(new Date());
        AccountingEntry aEntry = new AccountingEntry();
        aEntry.setAccounting_entry_id(aEntryId);
        aEntry.setAe_type(0);
        aEntry.setAe_description("PAGO DE CUOTA DEL SOCIO " + fee.getFe_loan().getLn_member().getPn_first_name() + " " + fee.getFe_loan().getLn_member().getPn_last_name());
        aEntry.setAe_date(Operaciones.formatearDate(Operaciones.formatearFecha(jdc)));
        aEntry.setAe_value(new BigDecimal(lcv.paymentDetailAmountTXT.getText()));
        aEntry.setPerson(fee.getFe_loan().getLn_member());
        saveAccountingEntry(aEntry);
    }
}