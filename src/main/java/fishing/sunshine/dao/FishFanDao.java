package fishing.sunshine.dao;

import fishing.sunshine.model.FishFan;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/20/15.
 */
public interface FishFanDao {
    ResultData insertFishFan(FishFan fishFan);

    ResultData queryFishFan(FishFan fishFan);

    ResultData updateFishFan(FishFan fishFan);
}
