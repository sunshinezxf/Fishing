package fishing.sunshine.service.impl;

import fishing.sunshine.dao.CommentDao;
import fishing.sunshine.model.Comment;
import fishing.sunshine.service.CommentService;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunshine on 1/12/16.
 */
@Service
public class CommentServiceImpl implements CommentService {

    private Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentDao commentDao;

    @Override
    public ResultData addComment(Comment comment) {
        ResultData result = new ResultData();
        comment.setCommentId(IDGenerator.generate("FZC"));
        ResultData insert = commentDao.addComment(comment);
        result.setResponseCode(insert.getResponseCode());
        if (insert.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(comment);
        } else {
            result.setDescription(insert.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryComment(Comment comment) {
        ResultData result = new ResultData();
        ResultData query = commentDao.queryComment(comment);
        result.setResponseCode(query.getResponseCode());
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((List<Comment>) query.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }
}
