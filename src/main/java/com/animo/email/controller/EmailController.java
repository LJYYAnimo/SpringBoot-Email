package com.animo.email.controller;

import com.animo.email.Service.IMailService;
import com.animo.email.Service.impl.IMailServiceImpl;
import com.animo.email.result.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description :
 * ---------------------------------
 * @Author : Animo QQ:1151757358
 * @Date : 2018/8/30
 */
@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private IMailService mailService;//注入发送邮件的各种实现方法
    @Autowired
    private TemplateEngine templateEngine;//注入模板引擎

    /**
     * 发送普通邮件
     * @return
     */
    @RequestMapping("/sendSimpleMail")
    public ServerResponse sendSimpleMail(){
        try {
            mailService.sendSimpleMail("3040003213@qq.com","SpringBoot Email","这是一封普通的SpringBoot测试邮件");
        }catch (Exception ex){
            ex.printStackTrace();
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        return ServerResponse.createBySuccess();
    }

    /**
     * 发送HTMl邮件
     * @return
     */
    @RequestMapping("/htmlEmail")
    public ServerResponse htmlEmail(){
        try {
            mailService.sendHtmlMail("3040003213@qq.com","IJPay让支付触手可及","<body style=\"text-align: center;margin-left: auto;margin-right: auto;\">\n"
                    + " <div id=\"welcome\" style=\"text-align: center;position: absolute;\" >\n"
                    +"      <h3>欢迎使用IJPay -By Javen</h3>\n"
                    +"      <span>https://github.com/Javen205/IJPay</span>"
                    + "     <div\n"
                    + "         style=\"text-align: center; padding: 10px\"><a style=\"text-decoration: none;\" href=\"https://github.com/Javen205/IJPay\" target=\"_bank\" ><strong>IJPay 让支付触手可及,欢迎Start支持项目发展:)</strong></a></div>\n"
                    + "     <div\n" + "         style=\"text-align: center; padding: 4px\">如果对你有帮助,请任意打赏</div>\n"
                    + "     <img width=\"180px\" height=\"180px\"\n"
                    + "         src=\"https://javen205.gitbooks.io/ijpay/content/assets/wxpay.png\">\n"
                    + " </div>\n" + "</body>");
        }catch (Exception ex){
            ex.printStackTrace();
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        return ServerResponse.createBySuccess();
    }

    /**
     * 发送附件
     * @return
     */
    @RequestMapping("/attachmentsMail")
    public ServerResponse attachmentsMail(){
        try {
            String filePath = "D:\\ReadMe.txt";
            mailService.sendAttachmentsMail("3040003213@qq.com", "这是一封带附件的邮件", "邮件中有附件，请注意查收！", filePath);
        }catch (Exception ex){
            ex.printStackTrace();
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        return ServerResponse.createBySuccess();
    }

    /**
     * 发送静态资源
     * @return
     */
    @RequestMapping("/resourceMail")
    public ServerResponse resourceMail(){
        try {
            String rscId = "IJPay";
            String content = "<html><body>这是有图片的邮件<br/><img src=\'cid:" + rscId + "\' ></body></html>";
            String imgPath = "D:\\BaiduNetdiskDownload\\[极品网红资源合集] (1)\\极品网红资源合集 (1)\\园田海未旗袍\\15 (1).jpg";
            mailService.sendResourceMail("3040003213@qq.com", "这邮件中含有图片", content, imgPath, rscId);

        }catch (Exception ex){
            ex.printStackTrace();
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        return ServerResponse.createBySuccess();
    }

    @RequestMapping("/templateMail")
    public ServerResponse templateMail(){
        try {
            Context context = new Context();
            context.setVariable("project", "IJPay");
            context.setVariable("author", "Javen");
            context.setVariable("url", "https://github.com/Javen205/IJPay");
            String emailContent = templateEngine.process("sendMailModel", context);
            mailService.sendHtmlMail("3040003213@qq.com", "这是模板邮件", emailContent);
        }catch (Exception ex){
            ex.printStackTrace();
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        return ServerResponse.createBySuccess();
    }
}
