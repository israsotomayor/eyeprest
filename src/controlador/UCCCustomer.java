package controlador;

import dao.DAOCustomer;
import dominio.Customer;
import dominio.Loan;
import java.util.List;

public class UCCCustomer {

    public List<Customer> getCustomerPerCriteria(String text) {
        DAOCustomer dc = new DAOCustomer();
        return dc.getCustomerPerCriteria(text);
    }
    
    public List<Customer> getCustomerPerLoan(String text) {
        DAOCustomer dc = new DAOCustomer();
        return dc.getCustomerPerLoan(text);
    }
    
    public Customer getCustomerPerLoanCustomerId(Loan loan){
        DAOCustomer dc = new DAOCustomer();
        return dc.getCustomerPerLoanCustomerId(loan);
    }

}
