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
import fishing.sunshine.util.DataTablePage;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
        if (saveThumbnail.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishzone/create");
            return view;
        }
        FishPond fishPond = new FishPond(form);
        fishPond.setThumbnail(String.valueOf(saveThumbnail.getData()));
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
        
        return result;
    }
}
