package controlador;

import dao.DAOGuarantor;
import dominio.Guarantor;
import java.util.List;

public class UCCGuarantor {
    
    public List<Guarantor> getGuarantorPerCriteria(String text) {
        DAOGuarantor dg = new DAOGuarantor();
        return dg.getGuarantorPerCriteria(text);
    }
    
    public Guarantor getGuarantorPerLoanGuarantorid(Long loanGuarantorId){
        DAOGuarantor dg = new DAOGuarantor();
        return dg.getGuarantorPerLoanGuarantorId(loanGuarantorId);
    }
}
