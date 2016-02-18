package fishing.sunshine.dao.impl;

import fishing.sunshine.dao.BaseDao;
import fishing.sunshine.dao.CommentDao;
import fishing.sunshine.model.Comment;
import fishing.sunshine.pagination.MobilePage;
import fishing.sunshine.pagination.MobilePageParam;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunshine on 1/12/16.
 */
@Repository
public class CommentDaoImpl extends BaseDao implements CommentDao {
    private Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class);

    @Transactional
    @Override
    public ResultData addComment(Comment comment) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("comment.insertComment", comment);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public ResultData queryComment(Comment comment) {
        ResultData result = new ResultData();
        try {
            List<Comment> list = sqlSession.selectList("comment.queryComment", comment);
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
    public ResultData queryTopic(MobilePageParam param) {
        ResultData result = new ResultData();
        MobilePage<Comment> page = new MobilePage<Comment>();
        try {
            List<Comment> total = sqlSession.selectList("comment.queryTopic");
            page.setTotal(total.size());
            List<Comment> list = sqlSession.selectList("comment.queryTopic", null, new RowBounds(param.getStart(), param.getLength()));
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
