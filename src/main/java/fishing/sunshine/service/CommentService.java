package fishing.sunshine.service;

import fishing.sunshine.model.Comment;
import fishing.sunshine.util.ResultData;

/**
 * Created by sunshine on 1/12/16.
 */
public interface CommentService {
    ResultData addComment(Comment comment);
}
