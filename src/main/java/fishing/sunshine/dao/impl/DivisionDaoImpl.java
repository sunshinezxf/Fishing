package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.DivisionDao;
import fishing.sunshine.model.division.City;
import fishing.sunshine.model.division.District;
import fishing.sunshine.model.division.Province;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunshine on 1/26/16.
 */
@Repository
public class DivisionDaoImpl extends BaseDao implements DivisionDao {
    private Logger logger = LoggerFactory.getLogger(DivisionDaoImpl.class);

    @Override
    public ResultData queryProvince(Province province) {
        ResultData result = new ResultData();
        try {
            List<Province> list = sqlSession.selectList("province.queryProvince", province);
            result.setData(list);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Transactional
    @Override
    public ResultData insertProvince(Province province) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("province.insertProvince", province);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryCity(City city) {
        ResultData result = new ResultData();
        try {
            List<City> list = sqlSession.selectList("city.queryCity", city);
            result.setData(list);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData insertCity(City city) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("city.insertCity", city);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryDistrict(District district) {
        ResultData result = new ResultData();
        try {
            List<District> list = sqlSession.selectList("district.queryDistrict", district);
            result.setData(list);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData insertDistrict(District district) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("district.insertDistrict", district);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }
}
