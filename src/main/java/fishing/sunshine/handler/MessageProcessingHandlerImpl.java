package fishing.sunshine.handler;

import com.gson.bean.InMessage;
import com.gson.bean.OutMessage;
import com.gson.bean.TextOutMessage;
import com.gson.inf.MessageProcessingHandler;
import fishing.sunshine.model.Location;
import fishing.sunshine.service.LocationService;
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

    @Override
    public OutMessage eventTypeMsg(InMessage inMessage) {
        if (inMessage.getEvent().equals("CLICK")) {
            if (inMessage.getEventKey().equals("location")) {
                return location(inMessage);
            }
        }
        return null;
    }

    @Override
    public OutMessage linkTypeMsg(InMessage inMessage) {
        return null;
    }

    @Override
    public OutMessage locationTypeMsg(InMessage inMessage) {
        return null;
    }

    @Override
    public OutMessage textTypeMsg(InMessage inMessage) {
        return null;
    }

    @Override
    public OutMessage imageTypeMsg(InMessage inMessage) {
        return null;
    }

    @Override
    public OutMessage voiceTypeMsg(InMessage inMessage) {
        return null;
    }

    private OutMessage location(InMessage message) {
        TextOutMessage result = new TextOutMessage();
        result.setContent("地理位置: 经度为" + message.getLocationX() + ", 纬度为" + message.getLocationY());
        Location location = new Location();
        location.setWechat(message.getFromUserName());
        location.setLongitude(message.getLocationX());
        location.setLatitude(message.getLocationY());
        locationService.addLocation(location);
        return result;
    }
}
