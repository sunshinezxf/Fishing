package fishing.sunshine.service;

import fishing.sunshine.model.Location;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/17/15.
 */
public interface LocationService {
    ResultData addLocation(Location location);

    ResultData queryLocationByPage(DataTableParam param);

    ResultData deleteLocation(Location location);
}
