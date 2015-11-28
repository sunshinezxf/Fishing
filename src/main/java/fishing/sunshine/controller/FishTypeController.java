package fishing.sunshine.controller;

import fishing.sunshine.form.FishForm;
import fishing.sunshine.model.Fish;
import fishing.sunshine.service.FishService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by sunshine on 11/18/15.
 */
@RequestMapping("/fishtype")
@RestController
public class FishTypeController {
    @Autowired
    private FishService fishService;

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ModelAndView create() {
        ModelAndView view = new ModelAndView();
        view.setViewName("management/fish_type/create");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ModelAndView create(@Valid FishForm form, BindingResult result) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.addObject(form);
            view.setViewName("redirect:/fishtype/create");
            return view;
        }
        Fish fish = new Fish(form);
        ResultData create = fishService.addFishType(fish);
        if (create.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            view.setViewName("redirect:/fishtype/create");
            return view;
        }
        view.setViewName("redirect:/fishtype/overview");
        return view;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/overview")
    public ModelAndView overview() {
        ModelAndView view = new ModelAndView();

        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/check")
    public String check(@Valid FishForm form) {

        return "valid";
    }
}
