package fishing.sunshine.model.division;

import fishing.sunshine.form.DivisionForm;
import fishing.sunshine.model.SunshineEntity;

/**
 * Created by sunshine on 1/26/16.
 */
public class District extends SunshineEntity {
    private String districtId;

    private String districtName;

    private City city;

    public District() {
        super();
    }

    public District(DivisionForm form) {
        this();
        this.districtName = form.getDistrict();
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
