package fishing.sunshine.controller;

import fishing.sunshine.handler.WxMpMessageHandlerImpl;
import fishing.sunshine.util.CommonValue;
import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sunshine on 12/17/15.
 */
@Configuration
public class FishingApplication {
    @Bean
    public WxMpXmlOutTextMessage defaultMessage() {
        WxMpXmlOutTextMessage message = new WxMpXmlOutTextMessage();
        message.setContent(CommonValue.WECHAT_WARNING);
        return message;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(wxMpInMemoryConfigStorage());
        return service;
    }

    @Bean
    public WxMpMessageHandler wxMpMessageHandler() {
        return new WxMpMessageHandlerImpl();
    }

    @Bean
    public WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage() {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(CommonValue.WECHAT_APPID);
        config.setToken(CommonValue.WECHAT_TOKEN);
        config.setAesKey(CommonValue.WECHAT_AESKEY);
        return config;
    }

    @Bean
    public WxMpMessageRouter wxMpMessageRouter() {
        WxMpMessageRouter router = new WxMpMessageRouter(wxMpService());
        router.rule().handler(wxMpMessageHandler()).end();
        return router;
    }
}
