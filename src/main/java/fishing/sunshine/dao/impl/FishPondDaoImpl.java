package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.FishPondDao;
import fishing.sunshine.model.FishPond;
import fishing.sunshine.util.ResultData;
import org.springframework.stereotype.Repository;

/**
 * Created by sunshine on 12/9/15.
 */
@Repository
public class FishPondDaoImpl extends BaseDao implements FishPondDao {
    @Override
    public ResultData insertFishPond(FishPond fishPond) {
        ResultData result = new ResultData();
        
        return result;
    }
}
