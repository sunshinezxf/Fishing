package fishing.sunshine.controller;

import com.alibaba.fastjson.JSONObject;
import fishing.sunshine.form.CommentForm;
import fishing.sunshine.model.Comment;
import fishing.sunshine.service.CommentService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by sunshine on 1/11/16.
 */
@RequestMapping("/comment")
@RestController
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

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
}
