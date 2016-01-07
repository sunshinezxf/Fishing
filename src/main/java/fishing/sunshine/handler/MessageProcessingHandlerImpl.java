package fishing.sunshine.handler;

import com.gson.bean.InMessage;
import com.gson.bean.OutMessage;
import com.gson.bean.TextOutMessage;
import com.gson.inf.MessageProcessingHandler;
import fishing.sunshine.model.FishFan;
import fishing.sunshine.model.Location;
import fishing.sunshine.service.FishFanService;
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
import org.springframework.stereotype.Service;

/**
 * Created by sunshine on 12/17/15.
 */
@Service
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {
    private Logger logger = LoggerFactory.getLogger(MessageProcessingHandlerImpl.class);

    @Autowired
    private LocationService locationService;

    @Autowired
    private FishFanService fishFanService;

    @Autowired
    private WxMpService wxMpService;

    private OutMessage outMessage;

    @Override
    public void allType(InMessage inMessage) {

    }

    @Override
    public void textTypeMsg(InMessage inMessage) {

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
        if (inMessage.getEvent().equals("subscribe")) {
            outMessage = new TextOutMessage();
            ResultData result = subscribe(inMessage);
            if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
                ((TextOutMessage) outMessage).setContent(String.valueOf(result.getData()));
            } else {
                ((TextOutMessage) outMessage).setContent("获取关注信息失败.");
            }
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
        try {
            WxMpUser user = wxMpService.userInfo(fan.getFishFanId(), "zh_CN");
            fan.setUsername(user.getNickname());
        }catch (WxErrorException e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
            return result;
        }
        ResultData insert = fishFanService.addFishFan(fan);
        if (insert.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData("欢迎您关注南京钓鱼");
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
        Location location = new Location();
        location.setWechat(message.getFromUserName());
        location.setLatitude(Double.parseDouble(message.getLocation_X()));
        location.setLongitude(Double.parseDouble(message.getLocation_Y()));
        ResultData insert = locationService.addLocation(location);
        if (insert.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData("LONG: " + location.getLongitude() + ", LAT: " + location.getLatitude());
        }
        return result;
    }


}