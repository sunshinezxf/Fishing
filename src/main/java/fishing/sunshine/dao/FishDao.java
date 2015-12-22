package fishing.sunshine.dao;

import fishing.sunshine.model.Fish;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 11/27/15.
 */
public interface FishDao {
    ResultData queryFish(Fish fish);

    ResultData insertFish(Fish fish);

    ResultData deleteFish(Fish fish);

    ResultData queryFishByPage(DataTableParam param);

    ResultData updateFish(Fish fish);
}
