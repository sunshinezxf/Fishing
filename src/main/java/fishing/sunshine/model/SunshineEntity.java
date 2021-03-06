package fishing.sunshine.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * Created by sunshine on 11/27/15.
 */
public class SunshineEntity {
    protected Timestamp createAt;
    protected boolean delFlag;

    public SunshineEntity() {
        this.createAt = new Timestamp(System.currentTimeMillis());
        this.delFlag = false;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
