package fishing.sunshine.dao;

import fishing.sunshine.model.Location;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/20/15.
 */
public interface LocationDao {

    ResultData queryLocation(Location location);

    ResultData insertLocation(Location location);

    ResultData queryLocationByPage(DataTableParam param);

    ResultData deleteLocation(Location location);
}
