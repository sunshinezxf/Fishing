package fishing.sunshine.model;

import com.alibaba.fastjson.JSONObject;
import fishing.sunshine.form.PondTypeForm;

/**
 * Created by sunshine on 12/3/15.
 */
public class PondType extends SunshineEntity {
    private String pondTypeId;

    private String pondTypeName;

    public PondType() {
        super();
    }

    public PondType(PondTypeForm form) {
        this();
        this.pondTypeName = form.getPondTypeName();
    }

    public String getPondTypeId() {
        return pondTypeId;
    }

    public void setPondTypeId(String pondTypeId) {
        this.pondTypeId = pondTypeId;
    }

    public String getPondTypeName() {
        return pondTypeName;
    }

    public void setPondTypeName(String pondTypeName) {
        this.pondTypeName = pondTypeName;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
