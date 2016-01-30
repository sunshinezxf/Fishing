package fishing.sunshine.service;

import fishing.sunshine.model.FishFan;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/20/15.
 */
public interface FishFanService {
    ResultData addFishFan(FishFan fishFan);

    ResultData queryFishFan(FishFan fishFan);

    ResultData updateFishFan(FishFan fishFan);
}
