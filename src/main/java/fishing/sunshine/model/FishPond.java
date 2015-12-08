package fishing.sunshine.model;

import fishing.sunshine.form.FishPondForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunshine on 12/8/15.
 */
public class FishPond extends SunshineEntity {
    private String fishPondId;
    private String fishPondName;
    private double longitude;
    private double latitude;
    private String fishPondAddress;
    private double fishPondFee;
    private boolean nightable;
    private List<PondType> pondTypes;
    private List<Fish> fishes;
    private MultipartFile thumbnail;
    private String introduction;
    private Contractor contractor;

    public FishPond() {
        super();
    }

    public FishPond(FishPondForm form) {
        this.fishPondName = form.getPondName();
        this.longitude = Double.parseDouble(form.getLongitude());
        this.latitude = Double.parseDouble(form.getLatitude());
        this.fishPondAddress = form.getPondAddress();
        this.fishPondFee = Double.parseDouble(form.getPondFee());
        this.nightable = form.isNightable();
        pondTypes = new ArrayList<PondType>();
        for (String item : form.getPondTypes()) {
            PondType type = new PondType();
            type.setPondTypeId(item);
            pondTypes.add(type);
        }
        fishes = new ArrayList<Fish>();
        for (String item : form.getFishTypes()) {
            Fish fish = new Fish();
            fish.setFishId(item);
            fishes.add(fish);
        }
        this.thumbnail = form.getThumbnail();
        this.introduction = form.getIntroduction();
        Contractor contractor = new Contractor();
        contractor.setContractorId(form.getContractorId());
        this.contractor = contractor;
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

    public double getFishPondFee() {
        return fishPondFee;
    }

    public void setFishPondFee(double fishPondFee) {
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

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
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
}
