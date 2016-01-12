package fishing.sunshine.form;

import javax.validation.constraints.NotNull;

/**
 * Created by sunshine on 1/10/16.
 */
public class CommentForm {
    @NotNull
    private String openId;

    @NotNull
    private String fishPondId;

    @NotNull
    private String comment;

    private String parentId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFishPondId() {
        return fishPondId;
    }

    public void setFishPondId(String fishPondId) {
        this.fishPondId = fishPondId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
