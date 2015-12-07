package fishing.sunshine.controller;

import com.alibaba.fastjson.JSON;
import fishing.sunshine.form.FishForm;
import fishing.sunshine.model.Fish;
import fishing.sunshine.service.FishService;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.DataTableResult;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.json.JSONObject;
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
    public DataTableResult<Fish> overview(DataTableParam param) {
        DataTableResult<Fish> result = new DataTableResult<Fish>();

        return result;
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
