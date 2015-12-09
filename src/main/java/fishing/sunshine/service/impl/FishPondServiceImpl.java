package fishing.sunshine.service.impl;

import fishing.sunshine.dao.FishPondDao;
import fishing.sunshine.dao.PondTypeDao;
import fishing.sunshine.model.FishPond;
import fishing.sunshine.model.PondType;
import fishing.sunshine.service.FishPondService;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by sunshine on 12/3/15.
 */
@Service("FishPondService")
public class FishPondServiceImpl implements FishPondService {
    private Logger logger = LoggerFactory.getLogger(FishPondServiceImpl.class);

    @Autowired
    private PondTypeDao pondTypeDao;

    @Autowired
    private FishPondDao fishPondDao;

    @Override
    public ResultData addFishPondType(PondType type) {
        ResultData result = new ResultData();
        type.setPondTypeId(IDGenerator.generate("POT"));
        ResultData insert = pondTypeDao.insertPondType(type);
        result.setResponseCode(insert.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(type);
        } else {
            result.setDescription(insert.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryFishPondType(PondType type) {
        ResultData result = new ResultData();
        ResultData query = pondTypeDao.queryPondType(type);
        result.setResponseCode(query.getResponseCode());
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((ArrayList<PondType>) result.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryFishPondTypeByPage(DataTableParam param) {
        ResultData result = new ResultData();
        ResultData query = pondTypeDao.queryPondTypeByPage(param);
        result.setResponseCode(query.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData addFishPond(FishPond fishPond) {
        ResultData result = new ResultData();
        fishPond.setFishPondId(IDGenerator.generate("FPD"));
        ResultData insert = fishPondDao.insertFishPond(fishPond);
        result.setResponseCode(insert.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(fishPond);
        } else {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(insert.getDescription());
        }
        return result;
    }
}
