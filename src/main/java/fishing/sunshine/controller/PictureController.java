package fishing.sunshine.controller;

import fishing.sunshine.service.FileUploadService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by sunshine on 2/15/16.
 */
@RequestMapping("/picture")
@RestController
public class PictureController {
    private Logger logger = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private FileUploadService fileUploadService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public ResultData upload(MultipartHttpServletRequest request) {
        ResultData result = new ResultData();
        String context = request.getSession().getServletContext().getRealPath("/");
        ResultData upload = fileUploadService.uploadPicture(request.getFile("picture"), context);
        if (upload.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(upload.getData());
        } else {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
        }
        return result;
    }
}
