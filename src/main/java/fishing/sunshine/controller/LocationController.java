package fishing.sunshine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunshine on 12/17/15.
 */
@RequestMapping("/location")
@RestController
public class LocationController {
    @RequestMapping(method = RequestMethod.GET, value = "/index")
    public ModelAndView location(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.setViewName("/client/");
        return view;
    }
}
