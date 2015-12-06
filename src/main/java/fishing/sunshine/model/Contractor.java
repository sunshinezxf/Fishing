package fishing.sunshine.model;

import fishing.sunshine.form.ContracterForm;

/**
 * Created by sunshine on 12/5/15.
 */
public class Contractor extends SunshineEntity {
    private String contractorId;
    private String name;
    private String phone;

    public Contractor() {
        super();
    }

    public Contractor(ContracterForm form) {
        this();
        this.name = form.getContracterName();
        this.phone = form.getContracterPhone();
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
