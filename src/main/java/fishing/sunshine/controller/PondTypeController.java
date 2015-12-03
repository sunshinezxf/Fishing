package fishing.sunshine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sunshine on 12/3/15.
 */
@RequestMapping("/zonetype")
@RestController
public class PondTypeController {
    public ModelAndView create() {
        ModelAndView view = new ModelAndView();

        return view;
    }
}
