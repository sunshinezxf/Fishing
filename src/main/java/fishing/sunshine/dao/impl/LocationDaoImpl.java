package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.LocationDao;
import fishing.sunshine.model.Location;
import fishing.sunshine.util.DataTablePage;
import fishing.sunshine.util.DataTableParam;
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
 * Created by sunshine on 12/20/15.
 */
public class LocationDaoImpl extends BaseDao implements LocationDao {
    private Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);

    @Override
    public ResultData queryLocation(Location location) {
        ResultData result = new ResultData();
        try {
            List<Location> list = sqlSession.selectList("location.queryLocation", location);
            result.setData(list);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData insertLocation(Location location) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("location.insertLocation", location);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryLocationByPage(DataTableParam param) {
        ResultData result = new ResultData();
        DataTablePage<Location> page = new DataTablePage<Location>();
        page.setsEcho(param.getsEcho());
        Location location = new Location();
        Map args = param.getParams();
        if (!StringUtils.isEmpty(args) && !StringUtils.isEmpty(args.get("wechat"))) {
            location.setWechat(String.valueOf(args.get("wechat")));
        }
        ResultData total = queryLocation(location);
        if (total.getResponseCode() != ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(total.getDescription());
            return result;
        }
        page.setiTotalRecords(((ArrayList<Location>) total.getData()).size());
        page.setiTotalDisplayRecords(((ArrayList<Location>) total.getData()).size());
        try {
            List<Location> list = sqlSession.selectList("location.queryLocation", location, new RowBounds(param.getiDisplayStart(), param.getiDisplayLength()));
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
