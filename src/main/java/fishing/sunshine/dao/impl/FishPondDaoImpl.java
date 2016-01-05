package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.FishPondDao;
import fishing.sunshine.model.*;
import fishing.sunshine.util.*;
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
 * Created by sunshine on 12/9/15.
 */
@Repository
public class FishPondDaoImpl extends BaseDao implements FishPondDao {
    private Logger logger = LoggerFactory.getLogger(FishPondDaoImpl.class);

    @Transactional
    @Override
    public ResultData insertFishPond(FishPond fishPond) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("pond.insertFishPond", fishPond);
            for (Fish item : fishPond.getFishes()) {
                PondFishBinding binding = new PondFishBinding();
                binding.setBindingId(IDGenerator.generate("PFB"));
                binding.setFish(item);
                binding.setFishPond(fishPond);
                sqlSession.insert("pond.insertPondFishBind", binding);
            }
            for (PondType item : fishPond.getPondTypes()) {
                TypePondBinding binding = new TypePondBinding();
                binding.setBindingId(IDGenerator.generate("TPB"));
                binding.setType(item);
                binding.setPond(fishPond);
                sqlSession.insert("pond.insertTypePondBind", binding);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryFishPond(FishPond fishPond) {
        ResultData result = new ResultData();
        try {
            List<FishPond> list = sqlSession.selectList("pond.queryFishPond", fishPond);
            result.setData(list);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Transactional
    @Override
    public ResultData updateFishPond(FishPond fishPond) {
        ResultData result = new ResultData();
        try {
            sqlSession.update("pond.updateFishPond", fishPond);
            PondFishBinding pfb = new PondFishBinding();
            pfb.setFishPond(fishPond);
            sqlSession.delete("pond.deletePondFishBind", pfb);
            for (Fish item : fishPond.getFishes()) {
                PondFishBinding binding = new PondFishBinding();
                binding.setBindingId(IDGenerator.generate("PFB"));
                binding.setFish(item);
                binding.setFishPond(fishPond);
                sqlSession.insert("pond.insertPondFishBind", binding);
            }
            TypePondBinding tpb = new TypePondBinding();
            tpb.setPond(fishPond);
            sqlSession.delete("pond.deleteTypePondBind", tpb);
            for (PondType item : fishPond.getPondTypes()) {
                TypePondBinding binding = new TypePondBinding();
                binding.setBindingId(IDGenerator.generate("TPB"));
                binding.setType(item);
                binding.setPond(fishPond);
                sqlSession.insert("pond.insertTypePondBind", binding);
            }
            sqlSession.update("contractor.updateContractor", fishPond.getContractor());
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryFishPondByPage(DataTableParam param) {
        ResultData result = new ResultData();
        DataTablePage<FishPond> page = new DataTablePage<FishPond>();
        page.setsEcho(param.getsEcho());
        FishPond pond = new FishPond();
        Map args = param.getParams();
        if (!StringUtils.isEmpty(args)) {
            if (!StringUtils.isEmpty(args.get("fishPondId"))) {
                pond.setFishPondId(String.valueOf(args.get("fishPondId")));
            }
            if (!StringUtils.isEmpty(args.get("fishPondName"))) {
                pond.setFishPondName(String.valueOf(args.get("fishPondName")));
            }
        }
        ResultData total = queryFishPond(pond);
        if (total.getResponseCode() != ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(total.getDescription());
            return result;
        }
        page.setiTotalRecords(((ArrayList<PondType>) total.getData()).size());
        page.setiTotalDisplayRecords(((ArrayList<PondType>) total.getData()).size());
        try {
            List<FishPond> list = sqlSession.selectList("pond.queryFishPond", pond, new RowBounds(param.getiDisplayStart(), param.getiDisplayLength()));
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
}
