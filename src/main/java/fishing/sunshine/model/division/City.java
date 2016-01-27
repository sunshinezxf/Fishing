package fishing.sunshine.model.division;

import fishing.sunshine.form.DivisionForm;
import fishing.sunshine.model.SunshineEntity;

/**
 * Created by sunshine on 1/26/16.
 */
public class City extends SunshineEntity {
    private String cityId;

    private String cityName;

    private Province province;

    public City() {
        super();
    }

    public City(DivisionForm form) {
        this();
        this.cityName = form.getCity();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
