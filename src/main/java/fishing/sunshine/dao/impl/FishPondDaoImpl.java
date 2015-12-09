package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.FishPondDao;
import fishing.sunshine.model.*;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunshine on 12/9/15.
 */
@Repository
public class FishPondDaoImpl extends BaseDao implements FishPondDao {
    private Logger logger = LoggerFactory.getLogger(FishPondDaoImpl.class);

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
}
