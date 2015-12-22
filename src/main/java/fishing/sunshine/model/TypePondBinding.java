package fishing.sunshine.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by sunshine on 12/9/15.
 */
public class TypePondBinding extends SunshineEntity {
    private String bindingId;
    private PondType type;
    private FishPond pond;

    public String getBindingId() {
        return bindingId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }

    public PondType getType() {
        return type;
    }

    public void setType(PondType type) {
        this.type = type;
    }

    public FishPond getPond() {
        return pond;
    }

    public void setPond(FishPond pond) {
        this.pond = pond;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
