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
}
