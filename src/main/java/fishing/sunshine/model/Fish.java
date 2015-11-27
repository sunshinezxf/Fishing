package fishing.sunshine.model;

import fishing.sunshine.util.IDGenerator;

/**
 * Created by sunshine on 11/27/15.
 */
public class Fish extends SunshineEntity {
    private String fishId;
    private String fishName;

    public Fish() {
        super();
        this.fishId = IDGenerator.generate("FIS");
    }

    public String getFishId() {
        return fishId;
    }

    public void setFishId(String fishId) {
        this.fishId = fishId;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }
}
