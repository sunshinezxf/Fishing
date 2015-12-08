package fishing.sunshine.service;

import fishing.sunshine.util.ResultData;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by sunshine on 12/8/15.
 */
public interface FileUploadService {
    ResultData uploadPicture(MultipartFile file, String base);
}
