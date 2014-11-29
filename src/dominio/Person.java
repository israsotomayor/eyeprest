package dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person {

    private Long person_id;
    private String pn_first_name;
    private String pn_last_name;    
    private int pn_type;    
    private Date pn_born_date;
    private String pn_identification_type;  
    private String pn_identification;      
    private String pn_email;    
    private String pn_profession;
    private Loan loan;
    private List<Input> listInput;
    private List<Address> listAddress;
    
    public Person() {
        listAddress = new ArrayList<>();
        listInput = new ArrayList<>();
    }

    /**
     * @return the person_id
     */
    public Long getPerson_id() {
        return person_id;
    }

    /**
     * @param person_id the person_id to set
     */
    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    /**
     * @return the pn_first_name
     */
    public String getPn_first_name() {
        return pn_first_name;
    }

    /**
     * @param pn_first_name the pn_first_name to set
     */
    public void setPn_first_name(String pn_first_name) {
        this.pn_first_name = pn_first_name;
    }

    /**
     * @return the pn_last_name
     */
    public String getPn_last_name() {
        return pn_last_name;
    }

    /**
     * @param pn_last_name the pn_last_name to set
     */
    public void setPn_last_name(String pn_last_name) {
        this.pn_last_name = pn_last_name;
    }

    /**
     * @return the pn_type
     */
    public int getPn_type() {
        return pn_type;
    }

    /**
     * @param pn_type the pn_type to set
     */
    public void setPn_type(int pn_type) {
        this.pn_type = pn_type;
    }

    /**
     * @return the pn_born_date
     */
    public Date getPn_born_date() {
        return pn_born_date;
    }

    /**
     * @param pn_born_date the pn_born_date to set
     */
    public void setPn_born_date(Date pn_born_date) {
        this.pn_born_date = pn_born_date;
    }

    /**
     * @return the pn_identification_type
     */
    public String getPn_identification_type() {
        return pn_identification_type;
    }

    /**
     * @param pn_identification_type the pn_identification_type to set
     */
    public void setPn_identification_type(String pn_identification_type) {
        this.pn_identification_type = pn_identification_type;
    }

    /**
     * @return the pn_identification
     */
    public String getPn_identification() {
        return pn_identification;
    }

    /**
     * @param pn_identification the pn_identification to set
     */
    public void setPn_identification(String pn_identification) {
        this.pn_identification = pn_identification;
    }

    /**
     * @return the pn_email
     */
    public String getPn_email() {
        return pn_email;
    }

    /**
     * @param pn_email the pn_email to set
     */
    public void setPn_email(String pn_email) {
        this.pn_email = pn_email;
    }

    /**
     * @return the pn_profession
     */
    public String getPn_profession() {
        return pn_profession;
    }

    /**
     * @param pn_profession the pn_profession to set
     */
    public void setPn_profession(String pn_profession) {
        this.pn_profession = pn_profession;
    }

    /**
     * @return the listInput
     */
    public List<Input> getListInput() {
        return listInput;
    }

    /**
     * @param listInput the listInput to set
     */
    public void setListInput(List<Input> listInput) {
        this.listInput = listInput;
    }

    /**
     * @return the listAddress
     */
    public List<Address> getListAddress() {
        return listAddress;
    }

    /**
     * @param listAddress the listAddress to set
     */
    public void setListAddress(List<Address> listAddress) {
        this.listAddress = listAddress;
    }

    /**
     * @return the loan
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * @param loan the loan to set
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
