package fishing.sunshine.controller;

import com.alibaba.fastjson.JSONObject;
import fishing.sunshine.form.CommentForm;
import fishing.sunshine.model.Comment;
import fishing.sunshine.model.Configuration;
import fishing.sunshine.model.FishFan;
import fishing.sunshine.model.FishPond;
import fishing.sunshine.pagination.MobilePage;
import fishing.sunshine.pagination.MobilePageParam;
import fishing.sunshine.service.CommentService;
import fishing.sunshine.service.FishFanService;
import fishing.sunshine.service.WechatService;
import fishing.sunshine.util.CommonValue;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import fishing.sunshine.util.WechatConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunshine on 1/11/16.
 */
@RequestMapping("/comment")
@RestController
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @Autowired
    private FishFanService fishFanService;

    @Autowired
    private WechatService wechatService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String create(@Valid CommentForm commentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "failure";
        }
        Comment comment = new Comment(commentForm);
        ResultData create = commentService.addComment(comment);
        if (create.getResponseCode() != ResponseCode.RESPONSE_OK) {
            return "failure";
        }
        return "success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{fishPondId}")
    public List<Comment> overview(@PathVariable("fishPondId") String fishPondId) {
        List<Comment> list = new ArrayList<Comment>();
        Comment comment = new Comment();
        FishPond fishPond = new FishPond();
        fishPond.setFishPondId(fishPondId);
        comment.setFishPond(fishPond);
        ResultData content = commentService.queryComment(comment);
        if (content.getResponseCode() == ResponseCode.RESPONSE_OK) {
            list = (List<Comment>) content.getData();
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/topic")
    public ModelAndView topic(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        String code = request.getParameter("code");
        if (!StringUtils.isEmpty(code)) {
            String state = request.getParameter("state");
            String url = CommonValue.SERVER_URL + "/comment/topic";
            String configLink = url + "?code=" + code + "&state=" + state;
            try {
                String shareLink = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + CommonValue.WECHAT_APPID + "&redirect_uri=" + URLEncoder.encode(url, "utf-8") + "&response_type=code&scope=snsapi_base&state=view#wechat_redirect";
                Configuration configuration = WechatConfig.config(configLink);
                configuration.setShareLink(shareLink);
                view.addObject("configuration", configuration);
                ResultData accessToken = wechatService.queryAccessToken(code);
                if (accessToken.getResponseCode() == ResponseCode.RESPONSE_OK) {
                    JSONObject json = JSONObject.parseObject((String) accessToken.getData());
                    String openId = json.getString("openid");
                    FishFan fan = new FishFan();
                    fan.setFishFanId(openId);
                    ResultData query = fishFanService.queryFishFan(fan);
                    if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
                        view.addObject("fishFan", ((List<FishFan>) query.getData()).get(0));
                    }
                }
            } catch (Exception e) {
                logger.debug(e.getMessage());
            }
        }
        view.addObject("appId", CommonValue.WECHAT_APPID);
        view.setViewName("/client/fish_info/index");
        return view;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/topic")
    public MobilePage<Comment> topic(MobilePageParam param) {
        MobilePage<Comment> result = new MobilePage<Comment>();
        ResultData query = commentService.queryTopic(param);
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result = (MobilePage<Comment>) query.getData();
        }
        return result;
    }
}
