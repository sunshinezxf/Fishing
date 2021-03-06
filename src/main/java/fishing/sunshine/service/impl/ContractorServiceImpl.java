package fishing.sunshine.service.impl;

import fishing.sunshine.dao.ContractorDao;
import fishing.sunshine.model.Contractor;
import fishing.sunshine.service.ContractorService;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by sunshine on 12/6/15.
 */
@Service
public class ContractorServiceImpl implements ContractorService {
    private Logger logger = LoggerFactory.getLogger(ContractorServiceImpl.class);

    @Autowired
    private ContractorDao contractorDao;

    @Override
    public ResultData addContractor(Contractor contractor) {
        ResultData result = new ResultData();
        contractor.setContractorId(IDGenerator.generate("CON"));
        ResultData insert = contractorDao.insertContractor(contractor);
        result.setResponseCode(insert.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(contractor);
        } else {
            result.setDescription(insert.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryContractor(Contractor contractor) {
        ResultData result = new ResultData();
        ResultData query = contractorDao.queryContractor(contractor);
        result.setResponseCode(query.getResponseCode());
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((ArrayList<Contractor>) query.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryContractorByPage(DataTableParam param) {
        ResultData result = new ResultData();
        ResultData query = contractorDao.queryContractorByPage(param);
        result.setResponseCode(query.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData updateContractor(Contractor previous, Contractor updated) {
        ResultData result = new ResultData();
        updated.setContractorId(previous.getContractorId());
        ResultData update = contractorDao.updateContractor(updated);
        result.setResponseCode(update.getResponseCode());
        if (update.getResponseCode() == ResponseCode.RESPONSE_OK) {
            update.setData(update.getData());
        } else {
            update.setDescription(update.getDescription());
        }
        return result;
    }
}
