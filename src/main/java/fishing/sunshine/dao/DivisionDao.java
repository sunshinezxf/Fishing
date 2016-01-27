package fishing.sunshine.dao;

import fishing.sunshine.model.division.Province;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 1/26/16.
 */
public interface DivisionDao {
    ResultData queryProvince(Province province);

    ResultData insertProvince(Province province);
    
}
