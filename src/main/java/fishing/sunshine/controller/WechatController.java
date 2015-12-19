package fishing.sunshine.controller;

import com.gson.bean.Articles;
import com.gson.bean.InMessage;
import com.gson.bean.OutMessage;
import com.gson.bean.TextOutMessage;
import com.gson.inf.MessageProcessingHandler;
import com.gson.util.Tools;
import com.gson.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;
import fishing.sunshine.util.CommonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by sunshine on 11/14/15.
 */
@RestController
public class WechatController {
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private MessageProcessingHandler messageProcessingHandler;

    @RequestMapping(method = RequestMethod.GET, value = "/" + CommonValue.WECHAT_TOKEN)
    @ResponseBody
    public String check(HttpServletRequest request) {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");//
        // 验证
        if (Tools.checkSignature(CommonValue.WECHAT_TOKEN, signature, timestamp, nonce)) {
            return echostr;
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/" + CommonValue.WECHAT_TOKEN)
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/xml");
        OutMessage result;
        ServletInputStream stream;
        try {
            stream = request.getInputStream();
            XStream content = XStreamFactory.init(false);
            content.alias("xml", InMessage.class);
            String xmlMessage = Tools.inputStream2String(stream);
            logger.debug(xmlMessage);

            InMessage inMessage = (InMessage) content.fromXML(xmlMessage);

            //加载处理器
            Class<?> clazz = messageProcessingHandler.getClass();
            //取得消息类型
            String type = inMessage.getMsgType();
            Method method = clazz.getMethod(type + "TypeMsg", InMessage.class);
            result = (OutMessage) method.invoke(messageProcessingHandler, inMessage);

            if (result == null) {
                result = new TextOutMessage();
                ((TextOutMessage) result).setContent(CommonValue.WECHAT_WARNING);
            }

            Class<?> resultClass = result.getClass().getSuperclass();
            Field createTime = resultClass.getDeclaredField("CreateTime");
            Field toUserName = resultClass.getDeclaredField("ToUserName");
            Field fromUserName = resultClass.getDeclaredField("FromUserName");

            createTime.setAccessible(true);
            toUserName.setAccessible(true);
            fromUserName.setAccessible(true);
            createTime.set(result, new Date().getTime());
            toUserName.set(result, inMessage.getFromUserName());
            fromUserName.set(result, inMessage.getToUserName());

            content = XStreamFactory.init(true);
            content.alias("xml", result.getClass());
            content.alias("item", Articles.class);
            String xml = content.toXML(result);
            return xml;
        } catch (IOException e) {
            logger.debug(e.getMessage());
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return "";
    }
}
