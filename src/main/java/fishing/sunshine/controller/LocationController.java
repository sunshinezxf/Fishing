package fishing.sunshine.controller;

import fishing.sunshine.model.Location;
import fishing.sunshine.service.LocationService;
import fishing.sunshine.pagination.DataTablePage;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sunshine on 12/17/15.
 */
@RequestMapping("/location")
@RestController
public class LocationController {
    private Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @RequestMapping(method = RequestMethod.GET, value = "/overview")
    public ModelAndView location() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/management/location/overview");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/overview")
    public DataTablePage<Location> location(DataTableParam param) {
        DataTablePage<Location> result = new DataTablePage<Location>();
        if (StringUtils.isEmpty(param)) {
            return result;
        }
        ResultData content = locationService.queryLocationByPage(param);
        if (content.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result = (DataTablePage<Location>) content.getData();
        }
        return result;
    }
}
