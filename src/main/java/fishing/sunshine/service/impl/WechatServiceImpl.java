package fishing.sunshine.service.impl;

import fishing.sunshine.service.WechatService;
import fishing.sunshine.util.CommonValue;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sunshine on 1/11/16.
 */
@Service
public class WechatServiceImpl implements WechatService {
    private Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Override
    public ResultData queryAccessToken(String code) {
        ResultData result = new ResultData();
        String link = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + CommonValue.WECHAT_APPID + "&secret=" + CommonValue.WECHAT_SECRET + "&code=" + code + "&grant_type=authorization_code";
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            connection.connect();
            OutputStream os = connection.getOutputStream();
            os.flush();
            os.close();
            InputStream is = connection.getInputStream();
            int size = is.available();
            byte[] bytes = new byte[size];
            is.read(bytes);
            String message = new String(bytes, "UTF-8");
            logger.debug(message);
            result.setData(message);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
        } finally {
            return result;
        }
    }
}
