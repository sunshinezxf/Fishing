package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.PondTypeDao;
import fishing.sunshine.model.PondType;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunshine on 12/3/15.
 */
@Repository
public class PondTypeDaoImpl extends BaseDao implements PondTypeDao {
    private Logger logger = LoggerFactory.getLogger(PondTypeDaoImpl.class);

    @Transactional
    @Override
    public ResultData insertPondType(PondType type) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("pond.insertFishType", type);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryPondType(PondType type) {
        ResultData result = new ResultData();
        try {
            List<PondType> list = sqlSession.selectList("pond.queryPondType", type);
            result.setData(list);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }
}
