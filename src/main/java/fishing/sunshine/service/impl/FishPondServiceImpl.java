package fishing.sunshine.service.impl;

import fishing.sunshine.dao.ContractorDao;
import fishing.sunshine.dao.FishPondDao;
import fishing.sunshine.dao.PondTypeDao;
import fishing.sunshine.model.Contractor;
import fishing.sunshine.model.FishPond;
import fishing.sunshine.model.PondType;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.pagination.MobilePageParam;
import fishing.sunshine.service.FishPondService;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ContractorDao contractorDao;

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
    public ResultData updateFishPondType(PondType previous, PondType updated) {
        ResultData result = new ResultData();
        updated.setPondTypeId(previous.getPondTypeId());
        ResultData update = pondTypeDao.updatePondType(updated);
        result.setResponseCode(update.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(update.getData());
        } else {
            result.setDescription(update.getDescription());
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

    @Override
    public ResultData queryFishPond(FishPond fishPond) {
        ResultData result = new ResultData();
        ResultData query = fishPondDao.queryFishPond(fishPond);
        result.setResponseCode(query.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((ArrayList<FishPond>) query.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryFishPond(List<String>... fishPondIds) {
        return null;
    }

    @Override
    public ResultData updateFishPond(FishPond previous, FishPond updated) {
        ResultData result = new ResultData();
        updated.setFishPondId(previous.getFishPondId());
        Contractor contractor = previous.getContractor();
        ResultData update;
        if (contractor != null) {
            contractor.setName(updated.getContractor().getName());
            contractor.setPhone(updated.getContractor().getPhone());
            updated.setContractor(contractor);
            contractorDao.updateContractor(contractor);
        } else {
            contractor = updated.getContractor();
            if (contractor != null) {
                contractor.setContractorId(IDGenerator.generate("CON"));
                contractor.setFishPond(updated);
                contractorDao.insertContractor(contractor);
            }
        }
        update = fishPondDao.updateFishPond(updated);
        result.setResponseCode(update.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(result.getData());
        } else {
            result.setDescription(result.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryFishPondByPage(DataTableParam param) {
        ResultData result = new ResultData();
        ResultData query = fishPondDao.queryFishPondByPage(param);
        result.setResponseCode(query.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryFishPondByPage(MobilePageParam param) {
        ResultData result = new ResultData();
        ResultData query = fishPondDao.queryFishPondByPage(param);
        result.setResponseCode(query.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
        }else {
            result.setDescription(query.getDescription());
        }
        return result;
    }
}
