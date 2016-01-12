package fishing.sunshine.dao.impl;

import com.alibaba.fastjson.JSONObject;
import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.CommentDao;
import fishing.sunshine.model.Comment;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by sunshine on 1/12/16.
 */
@Repository
public class CommentDaoImpl extends BaseDao implements CommentDao {
    private Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class);

    @Override
    public ResultData addComment(Comment comment) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("comment.insertComment", comment);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }finally {
            return result;
        }
    }
}
