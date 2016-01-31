package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.ContractorDao;
import fishing.sunshine.model.Contractor;
import fishing.sunshine.pagination.DataTablePage;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sunshine on 12/6/15.
 */
public class ContractorDaoImpl extends BaseDao implements ContractorDao {
    private Logger logger = LoggerFactory.getLogger(ContractorDaoImpl.class);

    @Override
    public ResultData insertContractor(Contractor contractor) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("contractor.insertContractor", contractor);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryContractor(Contractor contractor) {
        ResultData result = new ResultData();
        try {
            List<Contractor> list = sqlSession.selectList("contractor.queryContractor", contractor);
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
    public ResultData queryContractorByPage(DataTableParam param) {
        ResultData result = new ResultData();
        DataTablePage<Contractor> page = new DataTablePage<Contractor>();
        page.setsEcho(param.getsEcho());
        Contractor contractor = new Contractor();
        Map args = param.getParams();
        if (!StringUtils.isEmpty(args)) {
            if (!StringUtils.isEmpty(args.get("name"))) {
                contractor.setName(String.valueOf(args.get("name")));
            }
            if (!StringUtils.isEmpty(args.get("phone"))) {
                contractor.setPhone(String.valueOf(args.get("phone")));
            }
        }
        ResultData total = queryContractor(contractor);
        if (total.getResponseCode() != ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(total.getDescription());
            return result;
        }
        page.setiTotalRecords(((ArrayList<Contractor>) total.getData()).size());
        page.setiTotalDisplayRecords(((ArrayList<Contractor>) total.getData()).size());
        try {
            List<Contractor> list = sqlSession.selectList("contractor.queryContractor", contractor, new RowBounds(param.getiDisplayStart(), param.getiDisplayLength()));
            page.setData(list);
            result.setData(page);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData updateContractor(Contractor contractor) {
        ResultData result = new ResultData();
        try {
            sqlSession.update("contractor.updateContractor", contractor);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }
}
