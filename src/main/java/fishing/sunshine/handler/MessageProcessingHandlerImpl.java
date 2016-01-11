package fishing.sunshine.handler;

import com.alibaba.fastjson.JSONObject;
import com.gson.bean.*;
import com.gson.inf.MessageProcessingHandler;
import fishing.sunshine.controller.FishingApplication;
import fishing.sunshine.model.FishFan;
import fishing.sunshine.model.FishPond;
import fishing.sunshine.model.Location;
import fishing.sunshine.service.FishFanService;
import fishing.sunshine.service.FishPondService;
import fishing.sunshine.service.LocationService;
import fishing.sunshine.util.CommonValue;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunshine on 12/17/15.
 */
@Service
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {
    private Logger logger = LoggerFactory.getLogger(MessageProcessingHandlerImpl.class);

    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FishingApplication.class);

    @Autowired
    private LocationService locationService;

    @Autowired
    private FishFanService fishFanService;

    @Autowired
    private FishPondService fishPondService;

    private OutMessage outMessage;

    @Override
    public void allType(InMessage inMessage) {

    }

    @Override
    public void textTypeMsg(InMessage inMessage) {
        logger.debug("text: " + JSONObject.toJSONString(inMessage));
        ResultData result = fishpond(inMessage);
        if (result.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            outMessage = new TextOutMessage();
            ((TextOutMessage) outMessage).setContent(String.valueOf(result.getData()));
        } else {
            outMessage = new NewsOutMessage();
            List<FishPond> list = (List<FishPond>) result.getData();
            List<Articles> articles = new ArrayList<Articles>();
            for (FishPond item : list) {
                Articles article = new Articles();
                article.setTitle(item.getFishPondName());
                article.setDescription(item.getFishPondAddress());
                article.setPicUrl(CommonValue.SERVER_URL + item.getThumbnail());
                String url = CommonValue.SERVER_URL + "/fishzone/" + item.getFishPondId();
                try {
                    article.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + CommonValue.WECHAT_APPID + "&redirect_uri=" + URLEncoder.encode(url, "utf-8") + "&response_type=code&scope=snsapi_base#wechat_redirect");
                } catch (Exception e) {
                    logger.debug(e.getMessage());
                    continue;
                }
                articles.add(article);
            }
            ((NewsOutMessage) outMessage).setArticles(articles);
        }
    }

    @Override
    public void locationTypeMsg(InMessage inMessage) {
        outMessage = new TextOutMessage();
        ResultData result = location(inMessage);
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            ((TextOutMessage) outMessage).setContent(String.valueOf(result.getData()));
        } else {
            ((TextOutMessage) outMessage).setContent("Upload fail.");
        }
    }

    @Override
    public void imageTypeMsg(InMessage inMessage) {

    }

    @Override
    public void videoTypeMsg(InMessage inMessage) {

    }

    @Override
    public void linkTypeMsg(InMessage inMessage) {

    }

    @Override
    public void voiceTypeMsg(InMessage inMessage) {

    }

    @Override
    public void verifyTypeMsg(InMessage inMessage) {

    }

    @Override
    public void eventTypeMsg(InMessage inMessage) {
        logger.debug("=== " + JSONObject.toJSONString(inMessage));
        if (inMessage.getEvent().equals("subscribe")) {
            logger.debug("subscribe: " + inMessage.getFromUserName());
            outMessage = new TextOutMessage();
            ((TextOutMessage) outMessage).setContent(CommonValue.WECHAT_GREETING);
        }
        if (inMessage.getEvent().equals("LOCATION")) {

        }
    }

    @Override
    public void afterProcess(InMessage inMessage, OutMessage outMessage) {
        TextOutMessage message = new TextOutMessage();
        message.setContent(CommonValue.WECHAT_WARNING);
        this.outMessage = message;
    }

    @Override
    public void setOutMessage(OutMessage outMessage) {
        this.outMessage = outMessage;
    }

    @Override
    public OutMessage getOutMessage() {
        return outMessage;
    }

    private ResultData subscribe(InMessage message) {
        ResultData result = new ResultData();
        FishFan fan = new FishFan();
        fan.setFishFanId(message.getFromUserName());
        ResultData query = fishFanService.queryFishFan(fan);
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData("欢迎回来");
            return result;
        } else if (query.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            result.setData("系统正在打瞌睡,请稍后再试");
            return result;
        }
        try {
            WxMpService wxMpService = applicationContext.getBean(WxMpService.class);
            WxMpUser user = wxMpService.userInfo(fan.getFishFanId(), "zh_CN");
            fan.setUsername(user.getNickname());
        } catch (WxErrorException e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
            return result;
        }
        ResultData insert = fishFanService.addFishFan(fan);
        if (insert.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(CommonValue.WECHAT_GREETING);
        }
        return result;
    }

    /**
     * 通过发送地址消息来上传地理位置
     *
     * @param message
     * @return
     */
    private ResultData location(InMessage message) {
        ResultData result = new ResultData();
        FishFan fan = new FishFan();
        fan.setFishFanId(message.getFromUserName());
        ResultData query = fishFanService.queryFishFan(fan);
        if (query.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            subscribe(message);
        }
        Location location = new Location();
        location.setWechat(message.getFromUserName());
        location.setLatitude(Double.parseDouble(message.getLocation_X()));
        location.setLongitude(Double.parseDouble(message.getLocation_Y()));
        ResultData insert = locationService.addLocation(location);
        if (insert.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData("经度: " + location.getLongitude() + ", 纬度: " + location.getLatitude());
        }
        return result;
    }

    private ResultData fishpond(InMessage message) {
        ResultData result = new ResultData();
        FishPond fishPond = new FishPond();
        fishPond.setFishPondName(message.getContent());
        ResultData query = fishPondService.queryFishPond(fishPond);
        if (query.getResponseCode() != ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
            result.setData("未找到相关钓场");
            return result;
        }
        result.setData(query.getData());
        return result;
    }


}