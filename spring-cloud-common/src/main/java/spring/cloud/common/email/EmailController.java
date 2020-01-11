package spring.cloud.common.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private IMailServiceImpl mailService;//注入发送邮件的各种实现方法
//    @Autowired
//    private RocketMQProperties rocketMQProperties;
//    @Autowired
//    private TestPOJO testPOJO;

    @GetMapping("/normal")
    public String index(){
        try {
            mailService.sendSimpleMail("491431567@qq.com","SpringBoot Email","这是一封普通的SpringBoot测试邮件");
            String[] cc = {"491431567@qq.com","491431567@qq.com"};
            mailService.sendAttachmentsMail("491431567@qq.com","附件发送测试","这是附件","C:\\Users\\Domi\\Desktop\\ls.txt",cc);
            mailService.sendHtmlMail("491431567@qq.com","html测试","<body style=\"text-align: center;margin-left: auto;margin-right: auto;\">\n"
                    + "	<div id=\"welcome\" style=\"text-align: center;position: absolute;\" >\n"
                    +"		<h3>欢迎使用IJPay -By Javen</h3>\n"
                    +"		<span>https://github.com/Javen205/IJPay</span>"
                    + "		<div\n"
                    + "			style=\"text-align: center; padding: 10px\"><a style=\"text-decoration: none;\" href=\"https://github.com/Javen205/IJPay\" target=\"_bank\" ><strong>IJPay 让支付触手可及,欢迎Start支持项目发展:)</strong></a></div>\n"
                    + "		<div\n" + "			style=\"text-align: center; padding: 4px\">如果对你有帮助,请任意打赏</div>\n"
                    + "		<img width=\"180px\" height=\"180px\"\n"
                    + "			src=\"https://javen205.gitbooks.io/ijpay/content/assets/wxpay.png\">\n"
                    + "	</div>\n" + "</body>",cc);
        }catch (Exception ex){
            ex.printStackTrace();
            return "失败";
        }
        return "成功";
    }

    @GetMapping("/normal2")
    public String index2() throws Exception{

        for (int i = 0; i < 8; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "====");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "====");

                    //System.out.println(Thread.currentThread().getName() + "获得boolean" + testPOJO.atoTest + "==>" + testPOJO.do2());
                }
            }).start();

        }
        return "成功";
    }
}
