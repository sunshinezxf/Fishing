package fishing.sunshine.model;

import com.alibaba.fastjson.JSONObject;
import fishing.sunshine.form.FishPondForm;
import fishing.sunshine.model.division.District;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunshine on 12/8/15.
 */
public class FishPond extends SunshineEntity {
    private String fishPondId;
    private String fishPondName;
    private String introduction;
    private String fishPondFee;
    private boolean nightable;
    private double longitude;
    private double latitude;
    private String fishPondAddress;
    private String thumbnail;
    private String limitation;
    private Contractor contractor;
    private District district;
    private List<PondType> pondTypes;
    private List<Fish> fishes;

    public FishPond() {
        super();
    }

    public FishPond(FishPondForm form) {
        this.fishPondName = form.getPondName();
        this.longitude = Double.parseDouble(StringUtils.isEmpty(form.getLongitude()) ? "0" : form.getLongitude());
        this.latitude = Double.parseDouble(StringUtils.isEmpty(form.getLatitude()) ? "0" : form.getLatitude());
        this.fishPondAddress = form.getPondAddress();
        this.fishPondFee = form.getPondFee();
        this.nightable = form.isNightable();
        this.limitation = form.getLimitation();
        pondTypes = new ArrayList<PondType>();
        if (form.getPondTypes() != null) {
            for (String item : form.getPondTypes()) {
                PondType type = new PondType();
                type.setPondTypeId(item);
                pondTypes.add(type);
            }
        }
        fishes = new ArrayList<Fish>();
        if (form.getFishTypes() != null) {
            for (String item : form.getFishTypes()) {
                Fish fish = new Fish();
                fish.setFishId(item);
                fishes.add(fish);
            }
        }
        this.introduction = form.getIntroduction();
        Contractor contractor = null;
        if (!StringUtils.isEmpty(form.getContractorName()) && !StringUtils.isEmpty(form.getContractorPhone())) {
            contractor = new Contractor();
            contractor.setName(form.getContractorName());
            contractor.setPhone(form.getContractorPhone());
            contractor.setFishPond(this);
        }
        this.contractor = contractor;
        District district = null;
        if (!StringUtils.isEmpty(form.getDistrictId())) {
            district = new District();
            district.setDistrictId(form.getDistrictId());
        }
        this.setDistrict(district);
    }

    public String getFishPondId() {
        return fishPondId;
    }

    public void setFishPondId(String fishPondId) {
        this.fishPondId = fishPondId;
    }

    public String getFishPondName() {
        return fishPondName;
    }

    public void setFishPondName(String fishPondName) {
        this.fishPondName = fishPondName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getFishPondAddress() {
        return fishPondAddress;
    }

    public void setFishPondAddress(String fishPondAddress) {
        this.fishPondAddress = fishPondAddress;
    }

    public String getFishPondFee() {
        return fishPondFee;
    }

    public void setFishPondFee(String fishPondFee) {
        this.fishPondFee = fishPondFee;
    }

    public boolean isNightable() {
        return nightable;
    }

    public void setNightable(boolean nightable) {
        this.nightable = nightable;
    }

    public List<PondType> getPondTypes() {
        return pondTypes;
    }

    public void setPondTypes(List<PondType> pondTypes) {
        this.pondTypes = pondTypes;
    }

    public List<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(List<Fish> fishes) {
        this.fishes = fishes;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
