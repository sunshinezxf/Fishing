package fishing.sunshine.service.impl;

import fishing.sunshine.dao.ContractorDao;
import fishing.sunshine.model.Contractor;
import fishing.sunshine.service.ContractorService;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sunshine on 12/6/15.
 */
public class ContractorServiceImpl implements ContractorService {
    private Logger logger = LoggerFactory.getLogger(ContractorServiceImpl.class);

    @Autowired
    private ContractorDao contractorDao;

    @Override
    public ResultData addContractor(Contractor contractor) {
        ResultData result = new ResultData();
        
        return result;
    }
}
