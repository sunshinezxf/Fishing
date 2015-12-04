package fishing.sunshine.controller;

import fishing.sunshine.form.PondTypeForm;
import fishing.sunshine.model.PondType;
import fishing.sunshine.service.FishPondService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by sunshine on 12/3/15.
 */
@RequestMapping("/zonetype")
@RestController
public class PondTypeController {

    private Logger logger = LoggerFactory.getLogger(PondTypeController.class);

    @Autowired
    private FishPondService fishPondService;

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ModelAndView create() {
        ModelAndView view = new ModelAndView();
        view.setViewName("management/fish_zone_type/create");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ModelAndView create(@Valid PondTypeForm form, BindingResult result) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.setViewName("redirect:/zonetype/create");
            return view;
        }
        PondType pondType = new PondType(form);
        ResultData exist = fishPondService.queryFishPondType(pondType);
        if (exist.getResponseCode() != ResponseCode.RESPONSE_NULL) {
            view.setViewName("redirect:/zonetype/create");
            return view;
        }
        ResultData create = fishPondService.addFishPondType(pondType);
        if (create.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            view.setViewName("redirect:/zonetype/create");
            return view;
        }
        view.setViewName("redirect:/zonetype/overview");
        return view;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/overview")
    public ModelAndView overview() {
        ModelAndView view = new ModelAndView();
        view.setViewName("management/fish_zone_type/create");
        return view;
    }
}
