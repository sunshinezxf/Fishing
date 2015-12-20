package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.FishFanDao;
import fishing.sunshine.model.FishFan;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by sunshine on 12/20/15.
 */
@Repository
public class FishFanDaoImpl extends BaseDao implements FishFanDao {
    private Logger logger = LoggerFactory.getLogger(FishFanDaoImpl.class);

    @Override
    public ResultData insertFishFan(FishFan fishFan) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("fan.insertFishFan", fishFan);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }
}
