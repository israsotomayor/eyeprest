package dominio;

import java.math.BigDecimal;
import java.util.Date;

public class Fee {
    
    private Long fee_id; 
    private int fe_number;
    private BigDecimal fe_payment;
    private Date fe_expiration_date;
    private BigDecimal fe_capital;
    private BigDecimal fe_interest;
    private String fe_state;
    private Loan fe_loan;

    /**
     * @return the fee_id
     */
    public Long getFee_id() {
        return fee_id;
    }

    /**
     * @param fee_id the fee_id to set
     */
    public void setFee_id(Long fee_id) {
        this.fee_id = fee_id;
    }

    /**
     * @return the fe_number
     */
    public int getFe_number() {
        return fe_number;
    }

    /**
     * @param fe_number the fe_number to set
     */
    public void setFe_number(int fe_number) {
        this.fe_number = fe_number;
    }      

    /**
     * @return the fe_expiration_date
     */
    public Date getFe_expiration_date() {
        return fe_expiration_date;
    }

    /**
     * @param fe_expiration_date the fe_expiration_date to set
     */
    public void setFe_expiration_date(Date fe_expiration_date) {
        this.fe_expiration_date = fe_expiration_date;
    }
       
    /**
     * @return the fe_state
     */
    public String getFe_state() {
        return fe_state;
    }

    /**
     * @param fe_state the fe_state to set
     */
    public void setFe_state(String fe_state) {
        this.fe_state = fe_state;
    }

    /**
     * @return the fe_loan
     */
    public Loan getFe_loan() {
        return fe_loan;
    }

    /**
     * @param fe_loan the fe_loan to set
     */
    public void setFe_loan(Loan fe_loan) {
        this.fe_loan = fe_loan;
    }  

    /**
     * @return the fe_payment
     */
    public BigDecimal getFe_payment() {
        return fe_payment;
    }

    /**
     * @param fe_payment the fe_payment to set
     */
    public void setFe_payment(BigDecimal fe_payment) {
        this.fe_payment = fe_payment;
    }

    /**
     * @return the fe_capital
     */
    public BigDecimal getFe_capital() {
        return fe_capital;
    }

    /**
     * @param fe_capital the fe_capital to set
     */
    public void setFe_capital(BigDecimal fe_capital) {
        this.fe_capital = fe_capital;
    }

    /**
     * @return the fe_interest
     */
    public BigDecimal getFe_interest() {
        return fe_interest;
    }

    /**
     * @param fe_interest the fe_interest to set
     */
    public void setFe_interest(BigDecimal fe_interest) {
        this.fe_interest = fe_interest;
    }
}