package fishing.sunshine.controller;

import fishing.sunshine.util.CommonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunshine on 11/14/15.
 */
@RestController
public class WechatController {
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/" + CommonValue.TOKEN)
    @ResponseBody
    public String check(HttpServletRequest request) {
        
        return "";
    }
}
