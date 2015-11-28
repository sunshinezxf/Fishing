package fishing.sunshine.form;

import javax.validation.constraints.Size;

/**
 * Created by sunshine on 11/28/15.
 */
public class FishForm {
    @Size(min = 1, max = 8)
    private String fishName;

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }
}
