package fishing.sunshine.service.impl;

import fishing.sunshine.service.FileUploadService;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import fishing.sunshine.util.SystemTeller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sunshine on 12/8/15.
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {
    private Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Override
    public ResultData uploadPicture(MultipartFile file, String base) {
        ResultData result = new ResultData();
        String PATH = "/material/upload";
        Date current = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(current);
        StringBuilder builder = new StringBuilder(base);
        builder.append(PATH);
        builder.append("/");
        builder.append(time);
        File directory = new File(builder.toString());
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String name = IDGenerator.generate("TH") + suffix;
        String completeName = builder.append(File.separator).append(name).toString();
        File temp = new File(completeName);
        try {
            file.transferTo(temp);
            int index = temp.getPath().indexOf(SystemTeller.tellPath(PATH + "/" + time));
            result.setData(temp.getPath().substring(index));
        } catch (IOException e) {
            logger.debug(e.getMessage());
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        } finally {
            return result;
        }
    }
}
