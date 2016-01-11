package fishing.sunshine.service;

import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 1/11/16.
 */
public interface WechatService {
    ResultData queryAccessToken(String code);
}
