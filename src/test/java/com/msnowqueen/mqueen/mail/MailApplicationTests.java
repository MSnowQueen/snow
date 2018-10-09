package com.msnowqueen.mqueen.mail;

import com.msnowqueen.mqueen.MqueenApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zn* on 2018/9/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MqueenApplication.class)
public class MailApplicationTests {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngineBean velocityEngine;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private MailService mailService;

    @Ignore
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("zhounan328@163.com");
        message.setTo("2064556784@qq.com");
        message.setSubject("主题: 简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

    @Test
    public void sendAttachmentsMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("zhounan328@163.com");
        helper.setTo("2064556784@qq.com");
        helper.setSubject("主题: 有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("D:/mm.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);

        mailSender.send(mimeMessage);
    }

    @Test
    public void sendInlineMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("zhounan328@163.com");
        helper.setTo("2064556784@qq.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html>" +
                "<body>" +
                "<img src=\"cid:wei\">" +
                "</body>" +
                "</html>", true);

        FileSystemResource file = new FileSystemResource(new File("D:/mm.jpg"));
        helper.addInline("wei", file);

        mailSender.send(mimeMessage);
    }

    @Test
    public void sendTemplateMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("zhounan328@163.com");
        helper.setTo("2064556784@qq.com");
        helper.setSubject("主题：模板邮件");

        //创建邮件正文
        //是导这个包import org.thymeleaf.context.Context;
        Context context = new Context();
        context.setVariable("title", "绑定邮箱");
        context.setVariable("description", "请确认你是否想要绑定邮箱!");
        context.setVariable("date", "2018年10月8日");
        context.setVariable("username", "爱丽丝");
        //获取thymeleaf的html模板
        String emailContent = templateEngine.process("template", context);
        helper.setText(emailContent, true);

        mailSender.send(mimeMessage);
    }

    @Test
    public void mailServiceTest(){
        String[] recipients = {"2064556784@qq.com"};
        String subject = "主题：模板邮件";
        String templateName = "template";
        Map<String, Object> datas = new HashMap<>();
        datas.put("title", "绑定邮箱");
        datas.put("description", "请确认你是否想要绑定邮箱!");
        datas.put("date", "2018年10月8日");
        datas.put("username", "爱丽丝");
        String[] attachments = {};
        String imageResourceName = "mm.jpg";
        String imageFileName = "D:/mm.jpg";

        mailService.sendMailWithInlineImage(recipients, subject, templateName,
                datas, attachments, imageResourceName, imageFileName);
    }

    @Test
    public void sendTemplateMail2() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("zhounan328@163.com");
        helper.setTo("2064556784@qq.com");
        helper.setSubject("主题：模板邮件");

        Map<String, Object> model = new HashMap<>();
        model.put("username", "爱丽丝");
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "template.vm", "UTF-8", model);
        helper.setText(text, true);

        mailSender.send(mimeMessage);
    }

    @Test
    public void sendRegist() {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");   // 设置 utf-8防止出现乱码
                message.setTo("2064556784@qq.com");
                message.setFrom("zhounan328@163.com");
                message.setSubject("主题：模板邮件");
                Map model = new HashMap<>();
                model.put("username", "爱丽丝");
                message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template.vm", "utf-8", model), true);
            }
        };
        try {
            mailSender.send(preparator);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
