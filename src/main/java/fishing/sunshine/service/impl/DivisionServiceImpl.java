package fishing.sunshine.service.impl;

import fishing.sunshine.dao.DivisionDao;
import fishing.sunshine.model.division.City;
import fishing.sunshine.model.division.District;
import fishing.sunshine.model.division.Province;
import fishing.sunshine.service.DivisionService;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunshine on 1/27/16.
 */
@Service
public class DivisionServiceImpl implements DivisionService {
    private Logger logger = LoggerFactory.getLogger(DivisionServiceImpl.class);

    @Autowired
    private DivisionDao divisionDao;

    @Override
    public ResultData queryProvince(Province province) {
        ResultData result = new ResultData();
        ResultData query = divisionDao.queryProvince(province);
        result.setResponseCode(query.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((List<Province>) query.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData addProvince(Province province) {
        ResultData result = new ResultData();
        province.setProvinceId(IDGenerator.generate("PRO"));
        ResultData insert = divisionDao.insertProvince(province);
        result.setResponseCode(insert.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(province);
        } else {
            result.setDescription(insert.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryCity(City city) {
        ResultData result = new ResultData();
        ResultData query = divisionDao.queryCity(city);
        result.setResponseCode(query.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((List<City>) query.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData addCity(City city) {
        ResultData result = new ResultData();
        city.setCityId(IDGenerator.generate("CIT"));
        ResultData insert = divisionDao.insertCity(city);
        result.setResponseCode(insert.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(city);
        } else {
            result.setDescription(insert.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryDistrict(District district) {
        ResultData result = new ResultData();
        ResultData query = divisionDao.queryDistrict(district);
        result.setResponseCode(query.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((List<District>) query.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData addDistrict(District district) {
        ResultData result = new ResultData();
        district.setDistrictId(IDGenerator.generate("DIS"));
        ResultData insert = divisionDao.insertDistrict(district);
        result.setResponseCode(insert.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(district);
        } else {
            result.setDescription(insert.getDescription());
        }
        return result;
    }
}
