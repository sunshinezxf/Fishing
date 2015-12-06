package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.ContractorDao;
import fishing.sunshine.model.Contractor;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by sunshine on 12/6/15.
 */
public class ContractorDaoImpl extends BaseDao implements ContractorDao {
    private Logger logger = LoggerFactory.getLogger(ContractorDaoImpl.class);

    @Override
    public ResultData insertContractor(Contractor contractor) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("contractor.insertContractor", contractor);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    public ResultData queryContractor(Contractor contractor) {
        ResultData result = new ResultData();
        try {
            List<Contractor> list = sqlSession.selectList("contractor.queryContractor", contractor);
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
