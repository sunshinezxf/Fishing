package fishing.sunshine.dao.impl;

import com.alibaba.fastjson.JSONObject;
import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.FishPondDao;
import fishing.sunshine.model.*;
import fishing.sunshine.pagination.DataTablePage;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.pagination.MobilePage;
import fishing.sunshine.pagination.MobilePageParam;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

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

    @Override
    public ResultData queryFishPond(String[] fishPondIds) {
        ResultData result = new ResultData();
        try {
            List<FishPond> list = sqlSession.selectList("pond.queryFishPondByIds", fishPondIds);
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

    @Override
    public ResultData queryFishPondByPage(MobilePageParam param) {
        Set<String> ids = new HashSet<String>();
        ResultData result = new ResultData();
        MobilePage<FishPond> page = new MobilePage<FishPond>();
        FishPond pond = new FishPond();
        Map args = param.getParams();
        ResultData total;
        if (!StringUtils.isEmpty(args.get("provinceId")) || !StringUtils.isEmpty(args.get("cityId")) || !StringUtils.isEmpty(args.get("districtId"))) {
            List<String> divisionList = new ArrayList<String>();
            if (!StringUtils.isEmpty(args.get("districtId"))) {
                divisionList.add((String) args.get("districtId"));
            } else if (!StringUtils.isEmpty(args.get("cityId"))) {
                Map<String, String> division = new HashMap<String, String>();
                division.put("cityId", (String) args.get("cityId"));
                divisionList = sqlSession.selectList("district.queryDistrictIds", division);
            } else if (!StringUtils.isEmpty(args.get("provinceId"))) {
                Map<String, String> division = new HashMap<String, String>();
                division.put("provinceId", (String) args.get("provinceId"));
                divisionList = sqlSession.selectList("district.queryDistrictIds", division);
            }
            if (!StringUtils.isEmpty(divisionList)) {
                List<String> fishPondIds = sqlSession.selectList("pond.queryFishPondIdsByDivision", divisionList);
                if (!StringUtils.isEmpty(fishPondIds) && fishPondIds.size() > 0) {
                    ids.addAll(fishPondIds);
                }
            }
            String[] idsArray = new String[ids.size()];
            ids.toArray(idsArray);
            total = queryFishPond(idsArray);
            logger.debug(JSONObject.toJSONString(total));
            page.setTotal(((List<FishPond>) total.getData()).size());
            if (total.getResponseCode() != ResponseCode.RESPONSE_OK) {
                result.setResponseCode(ResponseCode.RESPONSE_ERROR);
                result.setDescription(total.getDescription());
                return result;
            }
            try {
                List<FishPond> list = sqlSession.selectList("pond.queryFishPondByIds", idsArray, new RowBounds(param.getStart(), param.getLength()));
                page.setData(list);
                result.setData(page);
            } catch (Exception e) {
                logger.debug(e.getMessage());
                result.setResponseCode(ResponseCode.RESPONSE_ERROR);
                result.setDescription(e.getMessage());
            } finally {
                return result;
            }
        } else {
            total = queryFishPond(pond);
            page.setTotal(((List<FishPond>) total.getData()).size());
            if (total.getResponseCode() != ResponseCode.RESPONSE_OK) {
                result.setResponseCode(ResponseCode.RESPONSE_ERROR);
                result.setDescription(total.getDescription());
                return result;
            }
            try {
                List<FishPond> list = sqlSession.selectList("pond.queryFishPond", pond, new RowBounds(param.getStart(), param.getLength()));
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
}
