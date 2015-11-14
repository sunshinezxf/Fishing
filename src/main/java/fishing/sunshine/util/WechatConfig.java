package fishing.sunshine.util;

import fishing.sunshine.model.Configuration;
import org.sword.wechat4j.token.TokenProxy;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

/**
 * Created by sunshine on 11/14/15.
 */
public class WechatConfig {
    public static Configuration config(String url) {
        Configuration result = new Configuration();
        String jsApiTicket = getJsapiTicket();
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String signature = "";

        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=").append(jsApiTicket).append("&noncestr=")
                .append(nonceStr).append("&timestamp=").append(timestamp).append("&url=").append(url);
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sb.toString().getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        result.setJsapiTicket(jsApiTicket);
        result.setNonceStr(nonceStr);
        result.setSignature(signature);
        result.setTimestamp(timestamp);
        result.setUrl(url);
        return result;
    }

    private static String getJsapiTicket() {
        return TokenProxy.jsApiTicket();
    }

    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
