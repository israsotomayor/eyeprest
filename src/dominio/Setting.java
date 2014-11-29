package dominio;

import java.math.BigDecimal;

public class Setting {
    
    private Long setting_id;
    
    private BigDecimal IVA_VALUE;
    private BigDecimal INPUT_VALUE;
    private BigDecimal MORTUARY_VALUE;
    private BigDecimal INTEREST_VALUE;

    /**
     * @return the setting_id
     */
    public Long getSetting_id() {
        return setting_id;
    }

    /**
     * @param setting_id the setting_id to set
     */
    public void setSetting_id(Long setting_id) {
        this.setting_id = setting_id;
    }

    /**
     * @return the IVA_VALUE
     */
    public BigDecimal getIVA_VALUE() {
        return IVA_VALUE;
    }

    /**
     * @param IVA_VALUE the IVA_VALUE to set
     */
    public void setIVA_VALUE(BigDecimal IVA_VALUE) {
        this.IVA_VALUE = IVA_VALUE;
    }

    /**
     * @return the INPUT_VALUE
     */
    public BigDecimal getINPUT_VALUE() {
        return INPUT_VALUE;
    }

    /**
     * @param INPUT_VALUE the INPUT_VALUE to set
     */
    public void setINPUT_VALUE(BigDecimal INPUT_VALUE) {
        this.INPUT_VALUE = INPUT_VALUE;
    }

    /**
     * @return the MORTUARY_VALUE
     */
    public BigDecimal getMORTUARY_VALUE() {
        return MORTUARY_VALUE;
    }

    /**
     * @param MORTUARY_VALUE the MORTUARY_VALUE to set
     */
    public void setMORTUARY_VALUE(BigDecimal MORTUARY_VALUE) {
        this.MORTUARY_VALUE = MORTUARY_VALUE;
    }

    /**
     * @return the INTEREST_VALUE
     */
    public BigDecimal getINTEREST_VALUE() {
        return INTEREST_VALUE;
    }

    /**
     * @param INTEREST_VALUE the INTEREST_VALUE to set
     */
    public void setINTEREST_VALUE(BigDecimal INTEREST_VALUE) {
        this.INTEREST_VALUE = INTEREST_VALUE;
    }
}
