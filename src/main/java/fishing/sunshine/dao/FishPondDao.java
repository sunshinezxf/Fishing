package fishing.sunshine.dao;

import fishing.sunshine.model.FishPond;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.pagination.MobilePageParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/9/15.
 */
public interface FishPondDao {
    ResultData insertFishPond(FishPond fishPond);

    ResultData queryFishPond(FishPond fishPond);

    ResultData updateFishPond(FishPond fishPond);

    ResultData queryFishPondByPage(DataTableParam param);

    ResultData queryFishPondByPage(MobilePageParam param);
}
