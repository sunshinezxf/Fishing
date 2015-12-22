package fishing.sunshine.controller;

import fishing.sunshine.form.ContracterForm;
import fishing.sunshine.model.Contractor;
import fishing.sunshine.service.ContractorService;
import fishing.sunshine.util.DataTablePage;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;

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

    @RequestMapping(method = RequestMethod.POST, value = "/overview")
    public DataTablePage<Contractor> overview(DataTableParam param) {
        DataTablePage<Contractor> result = new DataTablePage<Contractor>();
        if (StringUtils.isEmpty(param)) {
            return result;
        }
        ResultData content = contractorService.queryContractorByPage(param);
        if (content.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result = (DataTablePage<Contractor>) content.getData();
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{contractorId}")
    public ModelAndView edit(@PathVariable("contractorId") String contractorId) {
        ModelAndView view = new ModelAndView();
        Contractor contractor = new Contractor();
        contractor.setContractorId(contractorId);
        ResultData content = contractorService.queryContractor(contractor);
        if (content.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishman/overview");
            return view;
        }
        Contractor target = ((ArrayList<Contractor>) content.getData()).get(0);
        view.addObject("contractor", target);
        view.setViewName("management/fish_manager/edit");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/{contractorId}")
    public ModelAndView edit(@PathVariable("contractorId") String contractorId, @Valid ContracterForm form, BindingResult result) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.setViewName("redirect:/fishman/edit/" + contractorId);
            return view;
        }
        Contractor contractor = new Contractor();
        contractor.setContractorId(contractorId);
        ResultData query = contractorService.queryContractor(contractor);
        if (query.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishman/create");
            return view;
        }
        Contractor previous = ((ArrayList<Contractor>) query.getData()).get(0);
        Contractor update = new Contractor(form);
        ResultData edit = contractorService.updateContractor(previous, update);
        if (edit.getResponseCode() != ResponseCode.RESPONSE_OK) {
            view.setViewName("redirect:/fishman/edit/" + contractorId);
            return view;
        }
        view.setViewName("redirect:/fishman/overview");
        return view;
    }
}
