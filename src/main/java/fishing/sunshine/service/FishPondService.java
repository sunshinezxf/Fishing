package fishing.sunshine.service;

import fishing.sunshine.model.FishPond;
import fishing.sunshine.model.PondType;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/3/15.
 */
public interface FishPondService {
    ResultData addFishPondType(PondType type);

    ResultData queryFishPondType(PondType type);

    ResultData queryFishPondTypeByPage(DataTableParam param);

    ResultData addFishPond(FishPond fishPond);

    ResultData queryFishPond(FishPond fishPond);
}
