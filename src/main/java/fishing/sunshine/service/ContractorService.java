package fishing.sunshine.service;

import fishing.sunshine.model.Contractor;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 12/6/15.
 */
public interface ContractorService {
    ResultData addContractor(Contractor contractor);

    ResultData queryContractor(Contractor contractor);

    ResultData queryContractorByPage(DataTableParam param);

    ResultData updateContractor(Contractor previous, Contractor updated);
}
