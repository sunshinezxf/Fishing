package fishing.sunshine.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by sunshine on 12/17/15.
 */
@Service
public class WxMpMessageHandlerImpl implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
        WxMpXmlOutMessage message = WxMpXmlOutMessage.NEWS().fromUser(wxMpXmlMessage.getToUserName()).toUser(wxMpXmlMessage.getFromUserName()).addArticle(item).build();
        return message;
    }
}
