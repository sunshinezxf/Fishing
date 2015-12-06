package fishing.sunshine.controller;

import fishing.sunshine.form.ContracterForm;
import fishing.sunshine.model.Contractor;
import fishing.sunshine.service.ContractorService;
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
 * Created by sunshine on 11/27/15.
 */
@RequestMapping("/fishman")
@RestController
public class ContractorController {
    private Logger logger = LoggerFactory.getLogger(ContractorController.class);

    @Autowired
    private ContractorService contractorService;

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
            view.addObject(form);
            view.setViewName("redirect:/fishman/create");
            return view;
        }
        Contractor contractor = new Contractor(form);
        ResultData exist = contractorService.queryContractor(contractor);
        if (exist.getResponseCode() != ResponseCode.RESPONSE_NULL) {
            view.setViewName("redirect:/fishman/create");
            return view;
        }
        ResultData create = contractorService.addContractor(contractor);
        if (create.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            view.setViewName("redirect:/fishman/create");
            return view;
        }
        view.setViewName("redirect:/fishman/overview");
        return view;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/overview")
    public ModelAndView overview() {
        ModelAndView view = new ModelAndView();
        view.setViewName("management/fish_manager/overview");
        return view;
    }
}
