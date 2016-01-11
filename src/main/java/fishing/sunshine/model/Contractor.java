package fishing.sunshine.model;

import com.alibaba.fastjson.JSONObject;
import fishing.sunshine.form.ContracterForm;

/**
 * Created by sunshine on 12/5/15.
 */
public class Contractor extends SunshineEntity {
    private String contractorId;
    private String name;
    private String phone;
    private FishPond fishPond;

    public Contractor() {
        super();
    }

    public Contractor(ContracterForm form) {
        this();
        this.name = form.getContracterName();
        this.phone = form.getContracterPhone();
        this.fishPond = new FishPond();
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public FishPond getFishPond() {
        return fishPond;
    }

    public void setFishPond(FishPond fishPond) {
        this.fishPond = fishPond;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
