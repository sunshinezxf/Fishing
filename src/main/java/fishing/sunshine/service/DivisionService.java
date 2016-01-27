package fishing.sunshine.service;

import fishing.sunshine.model.division.City;
import fishing.sunshine.model.division.District;
import fishing.sunshine.model.division.Province;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 1/27/16.
 */
public interface DivisionService {
    ResultData queryProvince(Province province);

    ResultData addProvince(Province province);

    ResultData queryCity(City city);

    ResultData addCity(City city);

    ResultData queryDistrict(District district);

    ResultData addDistrict(District district);
}
