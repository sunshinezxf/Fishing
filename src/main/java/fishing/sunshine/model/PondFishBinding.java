package fishing.sunshine.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by sunshine on 12/9/15.
 */
public class PondFishBinding extends SunshineEntity {
    private String bindingId;
    private FishPond fishPond;
    private Fish fish;

    public String getBindingId() {
        return bindingId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }

    public FishPond getFishPond() {
        return fishPond;
    }

    public void setFishPond(FishPond fishPond) {
        this.fishPond = fishPond;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
