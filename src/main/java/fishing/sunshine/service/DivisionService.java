package fishing.sunshine.service;

import fishing.sunshine.model.division.Province;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 1/27/16.
 */
public interface DivisionService {
    ResultData queryProvince(Province province);

    ResultData addProvince(Province province);
}
