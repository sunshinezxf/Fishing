package fishing.sunshine.controller;

import fishing.sunshine.form.CommentForm;
import fishing.sunshine.model.Comment;
import fishing.sunshine.model.FishPond;
import fishing.sunshine.service.CommentService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public List<Comment> topic() {
        List<Comment> list = new ArrayList<Comment>();
        ResultData content = commentService.queryTopic();
        if (content.getResponseCode() == ResponseCode.RESPONSE_OK) {
            list = (List<Comment>) content.getData();
        }
        return list;
    }
}
