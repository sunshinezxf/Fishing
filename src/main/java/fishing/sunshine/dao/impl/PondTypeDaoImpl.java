package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.PondTypeDao;
import fishing.sunshine.model.PondType;
import fishing.sunshine.util.DataTablePage;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sunshine on 12/3/15.
 */
@Repository
public class PondTypeDaoImpl extends BaseDao implements PondTypeDao {
    private Logger logger = LoggerFactory.getLogger(PondTypeDaoImpl.class);

    @Transactional
    @Override
    public ResultData insertPondType(PondType type) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("type.insertPondType", type);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryPondType(PondType type) {
        ResultData result = new ResultData();
        try {
            List<PondType> list = sqlSession.selectList("type.queryPondType", type);
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
    public ResultData queryPondTypeByPage(DataTableParam param) {
        ResultData result = new ResultData();
        DataTablePage<PondType> page = new DataTablePage<PondType>();
        page.setsEcho(param.getsEcho());
        PondType type = new PondType();
        Map args = param.getParams();
        if (!StringUtils.isEmpty(args) && !StringUtils.isEmpty(args.get("pondTypeName"))) {
            type.setPondTypeName(String.valueOf(args.get("pondTypeName")));
        }
        ResultData total = queryPondType(type);
        if (total.getResponseCode() != ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(total.getDescription());
            return result;
        }
        page.setiTotalRecords(((ArrayList<PondType>) total.getData()).size());
        page.setiTotalDisplayRecords(((ArrayList<PondType>) total.getData()).size());
        try {
            List<PondType> list = sqlSession.selectList("type.queryPondType", type, new RowBounds(param.getiDisplayStart(), param.getiDisplayLength()));
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
    public ResultData updatePondType(PondType type) {
        ResultData result = new ResultData();
        try {
            sqlSession.update("type.updatePondType", type);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }
}
