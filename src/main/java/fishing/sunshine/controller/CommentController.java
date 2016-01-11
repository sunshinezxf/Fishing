package fishing.sunshine.controller;

import fishing.sunshine.form.CommentForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by sunshine on 1/11/16.
 */
@RequestMapping("/comment")
@RestController
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String create(@Valid CommentForm commentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "failure";
        }
        
        return "success";
    }
}
