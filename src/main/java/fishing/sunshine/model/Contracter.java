package fishing.sunshine.model;

import fishing.sunshine.form.ContracterForm;

/**
 * Created by sunshine on 12/5/15.
 */
public class Contracter extends SunshineEntity {
    private String name;
    private String phone;

    public Contracter() {
        super();
    }

    public Contracter(ContracterForm form) {
        this();
        this.name = form.getContracterName();
        this.phone = form.getContracterPhone();
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
