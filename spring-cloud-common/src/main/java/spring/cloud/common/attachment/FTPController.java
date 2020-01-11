package spring.cloud.common.attachment;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/ftp")
public class FTPController {

    @PostMapping("/upload")
    public Boolean pictureUpload(MultipartFile file) throws Exception {

        String newName = UUID.randomUUID().toString();
        String oldName = file.getOriginalFilename();
        newName = newName + oldName.substring(oldName.lastIndexOf("."));
        InputStream inputStream = file.getInputStream();
        return FtpUtil.uploadFile("192.168.6.1",21,"testftp",
                "testftp999","/home/testftp/images","/2015/01/02",newName,inputStream);
    }

    @GetMapping("/download")
    public void pictureDownload(HttpServletResponse response) throws Exception {

        FtpUtil.udownLoadFile("192.168.6.1",21,"testftp",
                "testftp999","/home/testftp/images/2015/01/02","645c4e0a-4c23-4922-994e-84171a61a494.png",response);
    }



}
