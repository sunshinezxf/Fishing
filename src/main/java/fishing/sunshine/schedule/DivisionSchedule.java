package fishing.sunshine.schedule;

import fishing.sunshine.service.DivisionService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sunshine on 2/5/16.
 */
public class DivisionSchedule {
    private Logger logger = LoggerFactory.getLogger(DivisionSchedule.class);

    @Autowired
    private DivisionService divisionService;

    public void schedule() {
        ResultData scale = divisionService.scale();
        if (scale.getResponseCode() == ResponseCode.RESPONSE_OK) {
            logger.debug("Scale success");
        } else {
            logger.debug("Scale fail");
        }
    }
}
