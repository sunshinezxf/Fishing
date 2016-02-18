package fishing.sunshine.dao;

import fishing.sunshine.model.Comment;
import fishing.sunshine.pagination.MobilePageParam;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 1/12/16.
 */
public interface CommentDao {
    ResultData addComment(Comment comment);

    ResultData queryComment(Comment comment);

    ResultData queryTopic(MobilePageParam param);
}
