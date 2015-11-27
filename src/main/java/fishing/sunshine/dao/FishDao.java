package fishing.sunshine.dao;

import fishing.sunshine.model.Fish;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 11/27/15.
 */
public interface FishDao {
    //ResultData queryFishes();

    ResultData insertFish(Fish fish);

    //ResultData deleteFish(Fish fish);
}
