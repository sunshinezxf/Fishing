package fishing.sunshine.model;


import fishing.sunshine.form.FishForm;

/**
 * Created by sunshine on 11/27/15.
 */
public class Fish extends SunshineEntity {
    private String fishId;
    private String fishName;

    public Fish() {
        super();
    }

    public Fish(FishForm form) {
        this();
        this.fishName = form.getFishName();
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
