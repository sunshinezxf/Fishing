package fishing.sunshine.service.impl;

import fishing.sunshine.model.Location;
import fishing.sunshine.service.LocationService;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by sunshine on 12/17/15.
 */
@Service
public class LocationServiceImpl implements LocationService {
    private Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Override
    public ResultData addLocation(Location location) {
        return null;
    }
}
