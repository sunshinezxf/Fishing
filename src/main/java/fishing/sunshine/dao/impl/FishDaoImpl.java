package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.FishDao;
import fishing.sunshine.model.Fish;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sunshine on 11/27/15.
 */
@Repository
public class FishDaoImpl extends BaseDao implements FishDao {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(FishDaoImpl.class);

    @Transactional
    public ResultData insertFish(Fish fish) {
        logger.debug("class: FishDaoImpl; method: insertFish;");
        ResultData result = new ResultData();
        try {
            sqlSession.insert("fish.insertFish", fish);
        } catch (Exception e) {
            logger.debug(e.toString());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    public ResultData queryFish(Fish fish) {
        logger.debug("class: FishDaoImpl; method: queryFish");
        ResultData result = new ResultData();
        
        return result;
    }
}
