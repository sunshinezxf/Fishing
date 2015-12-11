package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.AccountDao;
import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.model.Account;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunshine on 12/11/15.
 */
@Repository
public class AccountDaoImpl extends BaseDao implements AccountDao {
    private Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

    @Override
    public ResultData queryAccount(Account account) {
        ResultData result = new ResultData();
        try {
            List<Account> list = sqlSession.selectList("manager.queryAccount", account);
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
