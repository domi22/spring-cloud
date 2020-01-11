package spring.cloud.common.email;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class IMailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.from}")
    private String from;

    /**
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content, String... cc) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送HTML邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException{
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setCc(cc);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        mailSender.send(message);
    }


    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @throws MessagingException
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc)throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setCc(cc);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        FileSystemResource file = new FileSystemResource(new File(filePath));
//        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
//        helper.addAttachment(fileName, file);
//        mailSender.send(message);
    }

    /**
     * 发送正文中有静态资源的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    @Override
    public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setCc(cc);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        FileSystemResource res = new FileSystemResource(new File(rscPath));
//        helper.addInline(rscId, res);
//        mailSender.send(message);
    }
}
