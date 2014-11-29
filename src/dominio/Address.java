package dominio;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Address {
    
    private Long address_id;    
    private String adr_street;    
    private String adr_locality;
    private String adr_city;
    private String adr_country;
    private String adr_reference;
    private Person adr_person_id;

    public static final String PROP_ADR_STREET = "adr_street";

    /**
     * Get the value of adr_street
     *
     * @return the value of adr_street
     */
    public String getAdr_street() {
        return adr_street;
    }

    /**
     * Set the value of adr_street
     *
     * @param adr_street new value of adr_street
     */
    public void setAdr_street(String adr_street) {
        String oldAdr_street = this.adr_street;
        this.adr_street = adr_street;
        propertyChangeSupport.firePropertyChange(PROP_ADR_STREET, oldAdr_street, adr_street);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    

    /**
     * @return the address_id
     */
    public Long getAddress_id() {
        return address_id;
    }

    /**
     * @param address_id the address_id to set
     */
    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    

    /**
     * @return the adr_locality
     */
    public String getAdr_locality() {
        return adr_locality;
    }

    /**
     * @param adr_locality the adr_locality to set
     */
    public void setAdr_locality(String adr_locality) {
        this.adr_locality = adr_locality;
    }

    /**
     * @return the adr_city
     */
    public String getAdr_city() {
        return adr_city;
    }

    /**
     * @param adr_city the adr_city to set
     */
    public void setAdr_city(String adr_city) {
        this.adr_city = adr_city;
    }

    /**
     * @return the adr_country
     */
    public String getAdr_country() {
        return adr_country;
    }

    /**
     * @param adr_country the adr_country to set
     */
    public void setAdr_country(String adr_country) {
        this.adr_country = adr_country;
    }

    /**
     * @return the adr_reference
     */
    public String getAdr_reference() {
        return adr_reference;
    }

    /**
     * @param adr_reference the adr_reference to set
     */
    public void setAdr_reference(String adr_reference) {
        this.adr_reference = adr_reference;
    }

    /**
     * @return the adr_person_id
     */
    public Person getAdr_person_id() {
        return adr_person_id;
    }

    /**
     * @param adr_person_id the adr_person_id to set
     */
    public void setAdr_person_id(Person adr_person_id) {
        this.adr_person_id = adr_person_id;
    }

    

   }
