package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.LocationDao;
import fishing.sunshine.model.Location;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sunshine on 12/20/15.
 */
public class LocationDaoImpl extends BaseDao implements LocationDao {
    private Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);

    @Override
    public ResultData queryLocation(Location location) {
        return null;
    }

    @Override
    public ResultData insertLocation(Location location) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("location.insertLocation", location);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryLocationByPage(DataTableParam param) {
        return null;
    }
}
