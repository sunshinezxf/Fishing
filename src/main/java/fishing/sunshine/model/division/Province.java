package fishing.sunshine.model.division;

import fishing.sunshine.form.DivisionForm;
import fishing.sunshine.model.SunshineEntity;

/**
 * Created by sunshine on 1/26/16.
 */
public class Province extends SunshineEntity {
    private String provinceId;

    private String provinceName;

    public Province() {
        super();
    }

    public Province(DivisionForm form) {
        this();
        this.provinceName = form.getProvince();
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
