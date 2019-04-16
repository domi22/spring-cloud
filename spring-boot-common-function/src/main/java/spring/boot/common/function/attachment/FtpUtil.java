package spring.boot.common.function.attachment;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class FtpUtil {

    public static boolean uploadFile(String host, int port, String username, String password,
                                     String basePath, String filePath, String filename, InputStream input) {
        FTPClient ftp = new FTPClient();
        Boolean result = false;
        try {
            // 连接FTP服务器
            ftp.connect(host, port);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            String path = basePath + filePath;
            if (!ftp.changeWorkingDirectory(path)) {
                //如果目录不存在则创建目录
                String[] dirs = path.split("/");
                StringBuffer tempPath = new StringBuffer();
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    tempPath.append("/").append(dir);
                    if (!ftp.changeWorkingDirectory(tempPath.toString())) {
                        if (!ftp.makeDirectory(tempPath.toString())) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath.toString());
                        }
                    }
                }
            }
            //设置上传文件为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件默认是10M大小
            //客户端编码等基本设置
            //FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);设置unix下下载大小的限制
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }


    public static void udownLoadFile(String host, int port, String username, String password,
                                        String remotePath,String fileName, HttpServletResponse response) {

        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                String name = ff.getName();
                if (name.equals(fileName)) {
                    response.setContentType("application/form-data");
                    String realFileName = "我的重命名" + name.substring(name.lastIndexOf("."));
                    //"inline; filename=\""
                    //+ URLEncoder.encode(title, "UTF-8")
                    //+ ".txt\"");
                    response.setCharacterEncoding("utf-8");
                    response.setHeader("Content-disposition",
                            "attachment; filename=" + new String(realFileName.getBytes("utf-8"), "ISO8859-1"));
                    ftp.retrieveFile(ff.getName(), response.getOutputStream());
                }
            }
            ftp.logout();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {

                }
            }

        }


    }
}