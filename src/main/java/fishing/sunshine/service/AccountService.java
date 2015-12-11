package fishing.sunshine.service;

import fishing.sunshine.model.Account;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/11/15.
 */
public interface AccountService {
    ResultData queryAccount(Account account);
}
