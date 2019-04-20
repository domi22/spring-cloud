package spring.boot.common.function.attachment;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * 导入文件的校验处理类
 */
public class FileValidator implements ConstraintValidator<IsFileWanner,Object> {

    /**
     * 单据类型
     * @param isFileWanner
     */
    private String[] fileTypes;

    /**
     * 文件大小(0L表示不需要校验)
     * @param isFileWanner
     */
    private long fileSize;

    @Override
    public void initialize(IsFileWanner isFileWanner) {
        this.fileTypes = isFileWanner.fileTypes();
        this.fileSize = isFileWanner.fileSize();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        MultipartFile file = (MultipartFile)o;
        //非空校验
        if (file == null || file.isEmpty()) {
//            constraintValidatorContext.buildConstraintViolationWithTemplate("上传附件不能为空").addConstraintViolation();
            return Boolean.FALSE;
        }
        try {
            String fullPathName = new String(file.getOriginalFilename().getBytes(),"UTF-8");
            String fileType = StringUtils.substringAfterLast(fullPathName,".");
            List<String> fileTypeList = Arrays.asList(fileType);
            if (!fileTypeList.contains(fileType)) {
//                constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
//                        .addConstraintViolation();
                return Boolean.FALSE;
            }
            long oriFileSize = file.getSize();
            //附件大小校验
            if(fileSize != 0L && fileSize < oriFileSize){
//                constraintValidatorContext.buildConstraintViolationWithTemplate("上传的附件过大")
//                        .addConstraintViolation();
                return Boolean.FALSE;
            }
        } catch (UnsupportedEncodingException e) {
        }

        return Boolean.TRUE;
    }
}
