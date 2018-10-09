package com.msnowqueen.mqueen.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * Created by zn* on 2018/10/8
 */
@Service
public class MailService {

    private static final boolean ISHTML = true;
    private static final boolean ISMULTIPART = true;
    private static final String encoding = "UTF-8";

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MessageContentBuilder contentBuilder;

    @Value("${spring.mail.username}")
    private String username;

    /**
     * 发送模板邮件并且包含内置图片
     *
     * @param recipients        收件人
     * @param subject           主题
     * @param templateName      模板名称
     * @param datas             模板内需要的数据信息
     * @param attachments       附件
     * @param imageResourceName 照片源名称
     * @param imageFileName     照片路径
     */
    public void sendMailWithInlineImage(String[] recipients, String subject, String templateName,
                                        Map<String, Object> datas, String[] attachments,
                                        String imageResourceName, String imageFileName) {
        final FileSystemResource image = new FileSystemResource(new File(imageFileName));

        MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, ISMULTIPART, encoding);
                composeMessageHeader(recipients, subject, attachments, messageHelper);
                messageHelper.setText(contentBuilder.buildMessage(templateName, datas, imageResourceName), ISHTML);
                messageHelper.addInline(imageResourceName, image);
            }
        };

        mailSender.send(mimeMessagePreparator);
    }

    /**
     * 组成信息头
     *
     * @param recipients    收件人
     * @param subject       主题
     * @param attachments   附件
     * @param messageHelper MimeMessageHelper类是MimeMessage的装饰类，
     *                      它提供了更多的开发人员友好界面，并为类的许多属性添加了输入验证.
     */
    private void composeMessageHeader(String[] recipients, String subject,
                                      String[] attachments, MimeMessageHelper messageHelper) throws MessagingException {
        messageHelper.setFrom(username);
        messageHelper.setTo(recipients);
        messageHelper.setSubject(subject);
        if (attachments.length != 0) {
            for (String filename :
                    attachments) {
                FileSystemResource file = new FileSystemResource(filename);
                messageHelper.addAttachment(file.getFilename(), file);
            }
        }
    }

}
