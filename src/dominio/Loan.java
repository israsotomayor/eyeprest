package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan {
    
    private Long loan_id;
    private String ln_number;
    private Date ln_issuance_date;
    private Date ln_closing_date;
    private String ln_user;
    private BigDecimal ln_amount_issued;
    private BigDecimal ln_financing_time;
    private BigDecimal ln_fee_number;
    private BigDecimal ln_total_amount;
    private String ln_pay_form;
    private String ln_method;
    private BigDecimal ln_interest_rate;
    private String ln_state;
    private Date ln_settlement_date;
    private String ln_guarantor_name;
    private Person ln_member;
    private List<Fee> ln_fee;
    
    public Loan(){
        ln_fee = new ArrayList<>();
    }

    /**
     * @return the loan_id
     */
    public Long getLoan_id() {
        return loan_id;
    }

    /**
     * @param loan_id the loan_id to set
     */
    public void setLoan_id(Long loan_id) {
        this.loan_id = loan_id;
    }

    /**
     * @return the ln_number
     */
    public String getLn_number() {
        return ln_number;
    }

    /**
     * @param ln_number the ln_number to set
     */
    public void setLn_number(String ln_number) {
        this.ln_number = ln_number;
    }

    /**
     * @return the ln_issuance_date
     */
    public Date getLn_issuance_date() {
        return ln_issuance_date;
    }

    /**
     * @param ln_issuance_date the ln_issuance_date to set
     */
    public void setLn_issuance_date(Date ln_issuance_date) {
        this.ln_issuance_date = ln_issuance_date;
    }

    /**
     * @return the ln_closing_date
     */
    public Date getLn_closing_date() {
        return ln_closing_date;
    }

    /**
     * @param ln_closing_date the ln_closing_date to set
     */
    public void setLn_closing_date(Date ln_closing_date) {
        this.ln_closing_date = ln_closing_date;
    }

    /**
     * @return the ln_user
     */
    public String getLn_user() {
        return ln_user;
    }

    /**
     * @param ln_user the ln_user to set
     */
    public void setLn_user(String ln_user) {
        this.ln_user = ln_user;
    }
   
    /**
     * @return the ln_pay_form
     */
    public String getLn_pay_form() {
        return ln_pay_form;
    }

    /**
     * @param ln_pay_form the ln_pay_form to set
     */
    public void setLn_pay_form(String ln_pay_form) {
        this.ln_pay_form = ln_pay_form;
    }

    /**
     * @return the ln_method
     */
    public String getLn_method() {
        return ln_method;
    }

    /**
     * @param ln_method the ln_method to set
     */
    public void setLn_method(String ln_method) {
        this.ln_method = ln_method;
    }    

    /**
     * @return the ln_state
     */
    public String getLn_state() {
        return ln_state;
    }

    /**
     * @param ln_state the ln_state to set
     */
    public void setLn_state(String ln_state) {
        this.ln_state = ln_state;
    }

    /**
     * @return the ln_settlement_date
     */
    public Date getLn_settlement_date() {
        return ln_settlement_date;
    }

    /**
     * @param ln_settlement_date the ln_settlement_date to set
     */
    public void setLn_settlement_date(Date ln_settlement_date) {
        this.ln_settlement_date = ln_settlement_date;
    }

    /**
     * @return the ln_member
     */
    public Person getLn_member() {
        return ln_member;
    }

    /**
     * @param ln_member the ln_member to set
     */
    public void setLn_member(Person ln_member) {
        this.ln_member = ln_member;
    }
    
    /**
     * @return the ln_fee
     */
    public List<Fee> getLn_fee() {
        return ln_fee;
    }

    /**
     * @param ln_fee the ln_fee to set
     */
    public void setLn_fee(List<Fee> ln_fee) {
        this.ln_fee = ln_fee;
    }

    /**
     * @return the ln_guarantor_name
     */
    public String getLn_guarantor_name() {
        return ln_guarantor_name;
    }

    /**
     * @param ln_guarantor_name the ln_guarantor_name to set
     */
    public void setLn_guarantor_name(String ln_guarantor_name) {
        this.ln_guarantor_name = ln_guarantor_name;
    }

    /**
     * @return the ln_amount_issued
     */
    public BigDecimal getLn_amount_issued() {
        return ln_amount_issued;
    }

    /**
     * @param ln_amount_issued the ln_amount_issued to set
     */
    public void setLn_amount_issued(BigDecimal ln_amount_issued) {
        this.ln_amount_issued = ln_amount_issued;
    }

    /**
     * @return the ln_financing_time
     */
    public BigDecimal getLn_financing_time() {
        return ln_financing_time;
    }

    /**
     * @param ln_financing_time the ln_financing_time to set
     */
    public void setLn_financing_time(BigDecimal ln_financing_time) {
        this.ln_financing_time = ln_financing_time;
    }

    /**
     * @return the ln_total_amount
     */
    public BigDecimal getLn_total_amount() {
        return ln_total_amount;
    }

    /**
     * @param ln_total_amount the ln_total_amount to set
     */
    public void setLn_total_amount(BigDecimal ln_total_amount) {
        this.ln_total_amount = ln_total_amount;
    }

    /**
     * @return the ln_interest_rate
     */
    public BigDecimal getLn_interest_rate() {
        return ln_interest_rate;
    }

    /**
     * @param ln_interest_rate the ln_interest_rate to set
     */
    public void setLn_interest_rate(BigDecimal ln_interest_rate) {
        this.ln_interest_rate = ln_interest_rate;
    }

    /**
     * @return the ln_fee_number
     */
    public BigDecimal getLn_fee_number() {
        return ln_fee_number;
    }

    /**
     * @param ln_fee_number the ln_fee_number to set
     */
    public void setLn_fee_number(BigDecimal ln_fee_number) {
        this.ln_fee_number = ln_fee_number;
    }  
}