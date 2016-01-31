package fishing.sunshine.service;

import fishing.sunshine.model.FishPond;
import fishing.sunshine.model.PondType;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.pagination.MobilePageParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/3/15.
 */
public interface FishPondService {
    ResultData addFishPondType(PondType type);

    ResultData queryFishPondType(PondType type);

    ResultData queryFishPondTypeByPage(DataTableParam param);

    ResultData updateFishPondType(PondType previous, PondType updated);

    ResultData addFishPond(FishPond fishPond);

    ResultData queryFishPond(FishPond fishPond);

    ResultData updateFishPond(FishPond previous, FishPond updated);

    ResultData queryFishPondByPage(DataTableParam param);

    ResultData queryFishPondByPage(MobilePageParam param);
}
