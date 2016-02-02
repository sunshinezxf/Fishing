package fishing.sunshine.query;

import fishing.sunshine.model.FishPond;

import java.util.List;

/**
 * Created by sunshine on 2/2/16.
 */
public class FishPondParam extends FishPond {
    private List<String> districtIds;
    private List<String> fishIds;
    private List<String> pondTypeIds;

    public FishPondParam() {
        super();
    }

    public List<String> getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(List<String> districtIds) {
        this.districtIds = districtIds;
    }

    public List<String> getFishIds() {
        return fishIds;
    }

    public void setFishIds(List<String> fishIds) {
        this.fishIds = fishIds;
    }

    public List<String> getPondTypeIds() {
        return pondTypeIds;
    }

    public void setPondTypeIds(List<String> pondTypeIds) {
        this.pondTypeIds = pondTypeIds;
    }
}
