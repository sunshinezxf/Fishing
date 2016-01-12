package fishing.sunshine.model;

import fishing.sunshine.form.CommentForm;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunshine on 1/9/16.
 */
public class Comment extends SunshineEntity {
    private String commentId;
    private String wechat;
    private FishPond fishPond;
    private String comment;
    private Comment parent;
    private List<Comment> commentList;

    public Comment() {
        this.fishPond = new FishPond();
        this.commentList = new ArrayList<Comment>();
    }

    public Comment(CommentForm form) {
        this();
        this.wechat = form.getOpenId();
        this.fishPond.setFishPondId(form.getFishPondId());
        this.comment = form.getComment();
        if (!StringUtils.isEmpty(form.getParentId())) {
            this.parent = new Comment();
            parent.setCommentId(form.getParentId());
        }
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public FishPond getFishPond() {
        return fishPond;
    }

    public void setFishPond(FishPond fishPond) {
        this.fishPond = fishPond;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
