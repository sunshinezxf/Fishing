package fishing.sunshine.service.impl;

import fishing.sunshine.dao.FishDao;
import fishing.sunshine.model.Fish;
import fishing.sunshine.service.FishService;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sunshine on 11/28/15.
 */
@Service
public class FishServiceImpl implements FishService {
    private Logger logger = LoggerFactory.getLogger(FishServiceImpl.class);

    @Autowired
    private FishDao fishDao;

    public ResultData addFishType(Fish fish) {
        logger.debug("class: FishServiceImpl; method: addFishType");
        ResultData result = new ResultData();
        ResultData medium = fishDao.insertFish(fish);
        //set response code
        result.setResponseCode(medium.getResponseCode());
        //set response data
        result.setData(medium.getData());
        //set response description
        result.setDescription(medium.getDescription());
        return result;
    }
}
