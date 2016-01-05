package fishing.sunshine.controller;

import fishing.sunshine.form.FishPondForm;
import fishing.sunshine.model.Contractor;
import fishing.sunshine.model.Fish;
import fishing.sunshine.model.FishPond;
import fishing.sunshine.model.PondType;
import fishing.sunshine.service.ContractorService;
import fishing.sunshine.service.FileUploadService;
import fishing.sunshine.service.FishPondService;
import fishing.sunshine.service.FishService;
import fishing.sunshine.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private FishPondService fishPondService;

    @Autowired
    private FileUploadService fileUploadService;

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
        PondType type = new PondType();
        ResultData typeResult = fishPondService.queryFishPondType(type);
        if (typeResult.getResponseCode() == ResponseCode.RESPONSE_OK) {
            view.addObject("typeList", typeResult.getData());
        }
        view.setViewName("/management/fish_zone/create");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ModelAndView create(MultipartHttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        FishPondForm form = new FishPondForm(request);
        String context = request.getSession().getServletContext().getRealPath("/");
        ResultData saveThumbnail = fileUploadService.uploadPicture(form.getThumbnail(), context);
        if (saveThumbnail.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            view.setViewName("redirect:/fishzone/create");
            return view;
        }
        FishPond fishPond = new FishPond(form);
        fishPond.setThumbnail(saveThumbnail.getResponseCode() == ResponseCode.RESPONSE_OK ? String.valueOf(saveThumbnail.getData()) : "");
        ResultData contractorExist = contractorService.queryContractor(fishPond.getContractor());
        if (contractorExist.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            view.setViewName("redirect:/fishzone/create");
            return view;
        } else if (contractorExist.getResponseCode() == ResponseCode.RESPONSE_OK) {
            Contractor contractor = ((ArrayList<Contractor>) contractorExist.getData()).get(0);
            fishPond.setContractor(contractor);
        } else {
            ResultData contractorCreate = contractorService.addContractor(fishPond.getContractor());
            if (contractorCreate.getResponseCode() != ResponseCode.RESPONSE_OK) {
                view.setViewName("redirect:/fishzone/create");
                return view;
            }
            fishPond.setContractor((Contractor) contractorCreate.getData());
        }
        ResultData exist = fishPondService.queryFishPond(fishPond);
        if (exist.getResponseCode() != ResponseCode.RESPONSE_NULL) {
            view.setViewName("redirect:/fishzone/create");
            return view;
        }
        ResultData createResult = fishPondService.addFishPond(fishPond);
        if (createResult.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishzone/create");
            return view;
        }
        view.setViewName("redirect:/fishzone/overview");
        return view;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/overview")
    public ModelAndView overview() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/management/fish_zone/overview");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/overview")
    public DataTablePage<FishPond> overview(DataTableParam param) {
        DataTablePage<FishPond> result = new DataTablePage<FishPond>();
        if (StringUtils.isEmpty(param)) {
            return result;
        }
        ResultData content = fishPondService.queryFishPondByPage(param);
        if (content.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result = (DataTablePage<FishPond>) content.getData();
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{fishPondId}")
    public ModelAndView view(@PathVariable String fishPondId, HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        String ip = IPTool.getIP(request);
        if (StringUtils.isEmpty(fishPondId)) {
            view.setViewName("/error");
            return view;
        }
        FishPond fishPond = new FishPond();
        fishPond.setFishPondId(fishPondId);
        ResultData content = fishPondService.queryFishPond(fishPond);
        if (content.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("/error");
            return view;
        }
        List<FishPond> list = (List<FishPond>) content.getData();
        if (list.size() != 1) {
            view.setViewName("/error");
            return view;
        }
        view.addObject("ip", ip);
        view.addObject("fishPond", list.get(0));
        view.setViewName("/client/fish_pond/view");
        return view;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{fishPondId}")
    public ModelAndView edit(@PathVariable("fishPondId") String fishPondId) {
        ModelAndView view = new ModelAndView();
        FishPond fishPond = new FishPond();
        fishPond.setFishPondId(fishPondId);
        ResultData content = fishPondService.queryFishPond(fishPond);
        if (content.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishzone/overview");
            return view;
        }
        FishPond target = ((ArrayList<FishPond>) content.getData()).get(0);
        view.addObject("fishPond", target);
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
        PondType type = new PondType();
        ResultData typeResult = fishPondService.queryFishPondType(type);
        if (typeResult.getResponseCode() == ResponseCode.RESPONSE_OK) {
            view.addObject("typeList", typeResult.getData());
        }
        view.setViewName("/management/fish_zone/edit");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/{fishPondId}")
    public ModelAndView edit(@PathVariable("fishPondId") String fishPondId, MultipartHttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        FishPond fishPond = new FishPond();
        fishPond.setFishPondId(fishPondId);
        ResultData query = fishPondService.queryFishPond(fishPond);
        if (query.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishzone/overview");
            return view;
        }
        FishPond previous = ((ArrayList<FishPond>) query.getData()).get(0);
        FishPondForm form = new FishPondForm(request);
        FishPond updated = new FishPond(form);
        String context = request.getSession().getServletContext().getRealPath("/");
        ResultData saveThumbnail = fileUploadService.uploadPicture(form.getThumbnail(), context);
        if (saveThumbnail.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            view.setViewName("redirect:/fishzone/edit/" + fishPondId);
            return view;
        } else if (saveThumbnail.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            updated.setThumbnail(previous.getThumbnail());
        } else {
            fishPond.setThumbnail(String.valueOf(saveThumbnail.getData()));
        }
        ResultData edit = fishPondService.updateFishPond(previous, updated);
        if (edit.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishzone/edit/" + fishPondId);
            return view;
        }
        view.setViewName("redirect:/fishzone/overview");
        return view;
    }
}
