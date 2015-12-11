package fishing.sunshine.service.impl;

import fishing.sunshine.dao.AccountDao;
import fishing.sunshine.model.Account;
import fishing.sunshine.service.AccountService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunshine on 12/11/15.
 */
@Service
public class AccountServiceImpl implements AccountService {
    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Override
    public ResultData queryAccount(Account account) {
        ResultData result = new ResultData();
        ResultData query = accountDao.queryAccount(account);
        result.setResponseCode(query.getResponseCode());
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((List<Account>) query.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }
}
