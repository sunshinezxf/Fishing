package fishing.sunshine.service.impl;

import fishing.sunshine.dao.LocationDao;
import fishing.sunshine.model.Location;
import fishing.sunshine.service.LocationService;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sunshine on 12/17/15.
 */
@Service
public class LocationServiceImpl implements LocationService {
    private Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    private LocationDao locationDao;

    @Override
    public ResultData addLocation(Location location) {
        ResultData result = new ResultData();
        location.setLocationId(IDGenerator.generate("LOC"));
        ResultData insert = locationDao.insertLocation(location);
        result.setResponseCode(insert.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(location);
        } else {
            result.setDescription(insert.getDescription());
        }
        return result;
    }
}
