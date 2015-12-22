package fishing.sunshine.service;

import fishing.sunshine.model.Location;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/17/15.
 */
public interface LocationService {
    ResultData addLocation(Location location);

    ResultData queryLocationByPage(DataTableParam param);
}
