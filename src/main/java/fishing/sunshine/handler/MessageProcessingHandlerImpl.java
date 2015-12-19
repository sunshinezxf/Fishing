package fishing.sunshine.handler;

import com.gson.bean.InMessage;
import com.gson.bean.OutMessage;
import com.gson.bean.TextOutMessage;
import com.gson.inf.MessageProcessingHandler;
import fishing.sunshine.model.Location;
import fishing.sunshine.service.LocationService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
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
            ((TextOutMessage) outMessage).setContent("Location upload failed.");
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
        if (inMessage.getEvent().equals("LOCATION")) {
            
        }
    }

    @Override
    public void afterProcess(InMessage inMessage, OutMessage outMessage) {

    }

    @Override
    public void setOutMessage(OutMessage outMessage) {
        this.outMessage = outMessage;
    }

    @Override
    public OutMessage getOutMessage() {
        return outMessage;
    }

    private ResultData location(InMessage message) {
        ResultData result = new ResultData();
        Location location = new Location();
        location.setWechat(message.getFromUserName());
        location.setLatitude(Double.parseDouble(message.getLocation_X()));
        location.setLongitude(Double.parseDouble(message.getLocation_Y()));
        ResultData insert = locationService.addLocation(location);
        if (insert.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData("Location upload success! Longitude: " + location.getLongitude() + ", latitude: " + location.getLatitude());
        }
        return result;
    }
}