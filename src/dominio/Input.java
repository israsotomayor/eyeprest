package dominio;

import java.math.BigDecimal;
import java.util.Date;

public class Input {
 
    private Long input_id;
    private int ipt_number;
    private int ipt_type;
    private BigDecimal ipt_value;
    private Date ipt_date;
    private BigDecimal ipt_total;
    private Person ipt_person_id;

    /**
     * @return the input_id
     */
    public Long getInput_id() {
        return input_id;
    }

    /**
     * @param input_id the input_id to set
     */
    public void setInput_id(Long input_id) {
        this.input_id = input_id;
    }

    /**
     * @return the ipt_number
     */
    public int getIpt_number() {
        return ipt_number;
    }

    /**
     * @param ipt_number the ipt_number to set
     */
    public void setIpt_number(int ipt_number) {
        this.ipt_number = ipt_number;
    }

    /**
     * @return the ipt_date
     */
    public Date getIpt_date() {
        return ipt_date;
    }

    /**
     * @param ipt_date the ipt_date to set
     */
    public void setIpt_date(Date ipt_date) {
        this.ipt_date = ipt_date;
    }

    /**
     * @return the ipt_person_id
     */
    public Person getIpt_person_id() {
        return ipt_person_id;
    }

    /**
     * @param ipt_person_id the ipt_person_id to set
     */
    public void setIpt_person_id(Person ipt_person_id) {
        this.ipt_person_id = ipt_person_id;
    }   

    /**
     * @return the ipt_type
     */
    public int getIpt_type() {
        return ipt_type;
    }

    /**
     * @param ipt_type the ipt_type to set
     */
    public void setIpt_type(int ipt_type) {
        this.ipt_type = ipt_type;
    }

    /**
     * @return the ipt_value
     */
    public BigDecimal getIpt_value() {
        return ipt_value;
    }

    /**
     * @param ipt_value the ipt_value to set
     */
    public void setIpt_value(BigDecimal ipt_value) {
        this.ipt_value = ipt_value;
    }

    /**
     * @return the ipt_total
     */
    public BigDecimal getIpt_total() {
        return ipt_total;
    }

    /**
     * @param ipt_total the ipt_total to set
     */
    public void setIpt_total(BigDecimal ipt_total) {
        this.ipt_total = ipt_total;
    }
}