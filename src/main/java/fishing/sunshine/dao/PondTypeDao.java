package fishing.sunshine.dao;

import fishing.sunshine.model.PondType;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/3/15.
 */
public interface PondTypeDao {
    ResultData insertPondType(PondType type);

    ResultData queryPondType(PondType type);

    ResultData queryPondTypeByPage(DataTableParam param);

    ResultData updatePondType(PondType type);
}
