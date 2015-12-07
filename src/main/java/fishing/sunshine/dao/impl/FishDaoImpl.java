package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.FishDao;
import fishing.sunshine.model.Fish;
import fishing.sunshine.util.DataTablePage;
import fishing.sunshine.util.DataTableParam;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sunshine on 11/27/15.
 */
@Repository
public class FishDaoImpl extends BaseDao implements FishDao {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(FishDaoImpl.class);

    @Transactional
    public ResultData insertFish(Fish fish) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("fish.insertFish", fish);
        } catch (Exception e) {
            logger.debug(e.toString());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryFish(Fish fish) {
        ResultData result = new ResultData();
        try {
            List<Fish> list = sqlSession.selectList("fish.queryFish", fish);
            result.setData(list);
        } catch (Exception e) {
            logger.debug(e.toString());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Transactional
    @Override
    public ResultData deleteFish(Fish fish) {
        ResultData result = new ResultData();
        try {
            sqlSession.update("fish.updateFish", fish);
        } catch (Exception e) {
            logger.debug(e.toString());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryFishByPage(DataTableParam param) {
        ResultData result = new ResultData();
        DataTablePage<Fish> page = new DataTablePage<Fish>();
        page.setsEcho(param.getsEcho());
        Fish fish = new Fish();
        Map args = param.getParams();
        if (!StringUtils.isEmpty(args) && !StringUtils.isEmpty(args.get("fishName"))) {
            fish.setFishName(String.valueOf(args.get("fishName")));
        }
        ResultData total = queryFish(fish);
        if (result.getResponseCode() != ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(total.getDescription());
            return result;
        }
        page.setiTotalRecords(((ArrayList<Fish>) total.getData()).size());
        page.setiTotalDisplayRecords(((ArrayList<Fish>) total.getData()).size());
        try {
            List<Fish> list = sqlSession.selectList("fish.queryFish", fish, new RowBounds(param.getiDisplayStart(), param.getiDisplayLength()));
            page.setData(list);
            result.setData(page);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
            return result;
        } finally {
            return result;
        }
    }
}
