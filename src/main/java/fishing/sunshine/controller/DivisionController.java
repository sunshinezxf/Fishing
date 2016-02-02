package fishing.sunshine.controller;

import fishing.sunshine.form.DivisionForm;
import fishing.sunshine.model.division.City;
import fishing.sunshine.model.division.District;
import fishing.sunshine.model.division.Province;
import fishing.sunshine.service.DivisionService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sunshine on 1/27/16.
 */
@RequestMapping("/division")
@RestController
public class DivisionController {
    private Logger logger = LoggerFactory.getLogger(DivisionController.class);

    @Autowired
    private DivisionService divisionService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResultData createDivision(DivisionForm divisionForm) {
        ResultData result = new ResultData();
        ResultData exist;
        Province province = new Province(divisionForm);
        exist = divisionService.queryProvince(province);
        if (exist.getResponseCode() == ResponseCode.RESPONSE_OK) {
            province = ((List<Province>) exist.getData()).get(0);
        } else if (exist.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            ResultData create = divisionService.addProvince(province);
            if (create.getResponseCode() != ResponseCode.RESPONSE_OK) {
                result.setResponseCode(ResponseCode.RESPONSE_ERROR);
                return result;
            }
        } else {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            return result;
        }
        City city = new City(divisionForm);
        city.setProvince(province);
        exist = divisionService.queryCity(city);
        if (exist.getResponseCode() == ResponseCode.RESPONSE_OK) {
            city = ((List<City>) exist.getData()).get(0);
        } else if (exist.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            ResultData create = divisionService.addCity(city);
            if (create.getResponseCode() != ResponseCode.RESPONSE_OK) {
                result.setResponseCode(ResponseCode.RESPONSE_ERROR);
                return result;
            }
        } else {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            return result;
        }
        District district = new District(divisionForm);
        district.setCity(city);
        exist = divisionService.queryDistrict(district);
        if (exist.getResponseCode() == ResponseCode.RESPONSE_OK) {
            district = ((List<District>) exist.getData()).get(0);
        } else if (exist.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            ResultData create = divisionService.addDistrict(district);
            if (create.getResponseCode() != ResponseCode.RESPONSE_OK) {
                result.setResponseCode(ResponseCode.RESPONSE_ERROR);
                return result;
            }
        } else {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            return result;
        }
        result.setData(district);
        return result;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/province")
    public ResultData queryProvince() {
        ResultData result = new ResultData();
        Province province = new Province();
        ResultData query = divisionService.queryProvince(province);
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
        } else if (query.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
        } else {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{provinceId}/city")
    public ResultData queryCity(@PathVariable("provinceId") String provinceId) {
        ResultData result = new ResultData();
        Province province = new Province();
        province.setProvinceId(provinceId);
        City city = new City();
        city.setProvince(province);
        ResultData query = divisionService.queryCity(city);
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
        } else if (query.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
        } else {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{cityId}/district")
    public ResultData queryDistrict(@PathVariable("cityId") String cityId) {
        ResultData result = new ResultData();
        City city = new City();
        city.setCityId(cityId);
        District district = new District();
        district.setCity(city);
        ResultData query = divisionService.queryDistrict(district);
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
        } else if (query.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
        } else {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
        }
        return result;
    }
}
