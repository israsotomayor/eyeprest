package dominio;

import java.math.BigDecimal;
import java.util.Date;

public class AccountingEntry {
    
    private Long accounting_entry_id;
    private int ae_type;
    private String ae_description;
    private Date ae_date;
    private BigDecimal ae_value;
    private Person person;

    /**
     * @return the accounting_entry_id
     */
    public Long getAccounting_entry_id() {
        return accounting_entry_id;
    }

    /**
     * @param accounting_entry_id the accounting_entry_id to set
     */
    public void setAccounting_entry_id(Long accounting_entry_id) {
        this.accounting_entry_id = accounting_entry_id;
    }

    /**
     * @return the ae_type
     */
    public int getAe_type() {
        return ae_type;
    }

    /**
     * @param ae_type the ae_type to set
     */
    public void setAe_type(int ae_type) {
        this.ae_type = ae_type;
    }

    /**
     * @return the ae_description
     */
    public String getAe_description() {
        return ae_description;
    }

    /**
     * @param ae_description the ae_description to set
     */
    public void setAe_description(String ae_description) {
        this.ae_description = ae_description;
    }

    /**
     * @return the ae_date
     */
    public Date getAe_date() {
        return ae_date;
    }

    /**
     * @param ae_date the ae_date to set
     */
    public void setAe_date(Date ae_date) {
        this.ae_date = ae_date;
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

    /**
     * @return the ae_value
     */
    public BigDecimal getAe_value() {
        return ae_value;
    }

    /**
     * @param ae_value the ae_value to set
     */
    public void setAe_value(BigDecimal ae_value) {
        this.ae_value = ae_value;
    }
}
