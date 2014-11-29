package dominio;

public class Customer extends Person {
    
    private String ct_number;
    private String ct_warranty_number;

    /**
     * @return the ct_number
     */
    public String getCt_number() {
        return ct_number;
    }

    /**
     * @param ct_number the ct_number to set
     */
    public void setCt_number(String ct_number) {
        this.ct_number = ct_number;
    }

    /**
     * @return the ct_warranty_number
     */
    public String getCt_warranty_number() {
        return ct_warranty_number;
    }

    /**
     * @param ct_warranty_number the ct_warranty_number to set
     */
    public void setCt_warranty_number(String ct_warranty_number) {
        this.ct_warranty_number = ct_warranty_number;
    }
    
    @Override
    public String toString(){
        return getPn_identification()+"   -   "+ getPn_first_name() +" "+getPn_last_name();
    }   
}
