package fishing.sunshine.controller;

import fishing.sunshine.form.FishForm;
import fishing.sunshine.model.Fish;
import fishing.sunshine.service.FishService;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.DataTablePage;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;

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
        ResultData exist = fishService.queryFishType(fish);
        if (exist.getResponseCode() != ResponseCode.RESPONSE_NULL) {
            view.setViewName("redirect:/fishtype/create");
            return view;
        }
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
        view.setViewName("management/fish_type/overview");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/overview")
    public DataTablePage<Fish> overview(DataTableParam param) {
        DataTablePage<Fish> result = new DataTablePage<Fish>();
        if (StringUtils.isEmpty(param)) {
            return result;
        }
        ResultData content = fishService.queryFishTypeByPage(param);
        if (content.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result = (DataTablePage<Fish>) content.getData();
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{fishId}")
    public ModelAndView edit(@PathVariable("fishId") String fishId) {
        ModelAndView view = new ModelAndView();
        Fish fish = new Fish();
        fish.setFishId(fishId);
        ResultData content = fishService.queryFishType(fish);
        if (content.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/management/fish_type/overview");
            return view;
        }
        Fish target = ((ArrayList<Fish>) content.getData()).get(0);
        view.addObject("fish", target);
        view.setViewName("/management/fish_type/edit");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/{fishId}")
    public ModelAndView edit(@PathVariable("fishId") String fishId, @Valid FishForm form, BindingResult result) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.setViewName("redirect:/fishtype/edit/" + fishId);
            return view;
        }

        Fish fish = new Fish();
        fish.setFishId(fishId);
        ResultData query = fishService.queryFishType(fish);
        if (query.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishtype/create");
            return view;
        }

        Fish previous = ((ArrayList<Fish>) query.getData()).get(0);

        Fish update = new Fish(form);
        ResultData edit = fishService.updateFishType(previous, update);
        if (edit.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishtype/edit/" + fishId);
            return view;
        }
        view.setViewName("redirect:/fishtype/overview");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/check")
    public String check(@Valid FishForm form, BindingResult result) {
        String message = "invalid";
        if (result.hasErrors()) {
            return message;
        }
        Fish fish = new Fish(form);
        ResultData exist = fishService.queryFishType(fish);
        //若已经存在,则返回invalid, 若存在错误,则返回invalid,若之前不存在,则返回valid
        if (exist.getResponseCode() == ResponseCode.RESPONSE_OK) {
            return message;
        } else if (exist.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            return message;
        } else if (exist.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            message = "valid";
            return message;
        }
        return message;
    }
}
