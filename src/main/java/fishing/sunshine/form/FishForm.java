package fishing.sunshine.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by sunshine on 11/28/15.
 */
public class FishForm {
    @NotNull
    @Size(min = 2, max = 8, message = "长度为2-8个字符")
    private String fishName;

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }
}
