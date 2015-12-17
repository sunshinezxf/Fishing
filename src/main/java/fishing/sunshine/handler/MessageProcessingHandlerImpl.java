package fishing.sunshine.handler;

import com.gson.bean.InMessage;
import com.gson.bean.OutMessage;
import com.gson.inf.MessageProcessingHandler;
import org.springframework.stereotype.Service;

/**
 * Created by sunshine on 12/17/15.
 */
@Service
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {
    @Override
    public OutMessage eventTypeMsg(InMessage inMessage) {
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
}
