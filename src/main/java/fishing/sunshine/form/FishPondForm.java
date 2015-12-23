package fishing.sunshine.form;

import fishing.sunshine.model.SunshineEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by sunshine on 12/8/15.
 */
public class FishPondForm extends SunshineEntity {
    private String pondName;

    private String contractorId;

    private String longitude;

    private String latitude;

    private String pondAddress;

    private String pondFee;

    private boolean nightable;

    private String[] pondTypes;

    private String[] fishTypes;

    private MultipartFile thumbnail;

    private String introduction;

    public FishPondForm(MultipartHttpServletRequest request) {
        this.pondName = request.getParameter("pondName");
        this.contractorId = request.getParameter("contractorId");
        this.longitude = request.getParameter("longitude");
        this.latitude = request.getParameter("latitude");
        this.pondAddress = request.getParameter("pondAddress");
        this.pondFee = request.getParameter("pondFee");
        this.nightable = Boolean.valueOf(request.getParameter("nightable"));
        this.pondTypes = request.getParameterValues("pondType");
        this.fishTypes = request.getParameterValues("fishType");
        this.thumbnail = request.getFile("pondThumbnail");
        this.introduction = request.getParameter("pondIntroduction");
    }

    public String getPondName() {
        return pondName;
    }

    public void setPondName(String pondName) {
        this.pondName = pondName;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPondAddress() {
        return pondAddress;
    }

    public void setPondAddress(String pondAddress) {
        this.pondAddress = pondAddress;
    }

    public String getPondFee() {
        return pondFee;
    }

    public void setPondFee(String pondFee) {
        this.pondFee = pondFee;
    }

    public boolean isNightable() {
        return nightable;
    }

    public void setNightable(boolean nightable) {
        this.nightable = nightable;
    }

    public String[] getPondTypes() {
        return pondTypes;
    }

    public void setPondTypes(String[] pondTypes) {
        this.pondTypes = pondTypes;
    }

    public String[] getFishTypes() {
        return fishTypes;
    }

    public void setFishTypes(String[] fishTypes) {
        this.fishTypes = fishTypes;
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
}
