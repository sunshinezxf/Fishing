package fishing.sunshine.dao;

import fishing.sunshine.model.division.City;
import fishing.sunshine.model.division.District;
import fishing.sunshine.model.division.Province;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 1/26/16.
 */
public interface DivisionDao {
    ResultData queryProvince(Province province);

    ResultData insertProvince(Province province);

    ResultData queryCity(City city);

    ResultData insertCity(City city);

    ResultData queryDistrict(District district);

    ResultData insertDistrict(District district);

    ResultData deleteProvince();

    ResultData deleteCity();

    ResultData deleteDistrict();
}
