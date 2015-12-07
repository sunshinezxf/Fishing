package fishing.sunshine.controller;

import fishing.sunshine.model.Contractor;
import fishing.sunshine.model.Fish;
import fishing.sunshine.service.ContractorService;
import fishing.sunshine.service.FishService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sunshine on 12/3/15.
 */
@RequestMapping("/fishzone")
@RestController
public class FishPondController {
    private Logger logger = LoggerFactory.getLogger(FishPondController.class);

    @Autowired
    private FishService fishService;

    @Autowired
    private ContractorService contractorService;

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ModelAndView create() {
        ModelAndView view = new ModelAndView();
        Fish paramOfFishType = new Fish();
        ResultData fishResult = fishService.queryFishType(paramOfFishType);
        if (fishResult.getResponseCode() == ResponseCode.RESPONSE_OK) {
            view.addObject("fishList", fishResult.getData());
        }
        Contractor paramOfContractor = new Contractor();
        ResultData contractorResult = contractorService.queryContractor(paramOfContractor);
        if (contractorResult.getResponseCode() == ResponseCode.RESPONSE_OK) {
            view.addObject("contractorList", contractorResult.getData());
        }
        view.setViewName("/management/fish_zone/create");
        return view;
    }
}