package fishing.sunshine.controller;

import fishing.sunshine.form.ContracterForm;
import fishing.sunshine.model.Contractor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by sunshine on 11/27/15.
 */
@RequestMapping("/fishman")
@RestController
public class ContracterController {
    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ModelAndView create() {
        ModelAndView view = new ModelAndView();
        view.setViewName("management/fish_manager/create");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ModelAndView create(@Valid ContracterForm form, BindingResult result) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.setViewName("redirect:/fishman/create");
            return view;
        }
        Contractor contractor = new Contractor(form);
        
        return view;
    }
}
