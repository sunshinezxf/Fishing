package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.FishDao;
import fishing.sunshine.model.Fish;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunshine on 11/27/15.
 */
@Repository
public class FishDaoImpl extends BaseDao implements FishDao {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(FishDaoImpl.class);

    @Transactional
    public ResultData insertFish(Fish fish) {
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

    @Override
    public ResultData queryFish(Fish fish) {
        ResultData result = new ResultData();
        try {
            List<Fish> list = sqlSession.selectList("fish.queryFish", fish);
            result.setData(list);
        } catch (Exception e) {
            logger.debug(e.toString());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }
}