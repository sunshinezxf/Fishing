package fishing.sunshine.service;

import fishing.sunshine.model.Fish;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 11/28/15.
 */
public interface FishService {
    ResultData addFishType(Fish fish);

    ResultData queryFishType(Fish fish);

//    ResultData updateFishType(Fish fish);
}
