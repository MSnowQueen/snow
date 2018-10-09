package com.msnowqueen.mqueen.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * Created by zn* on 2018/10/8
 * 创建邮件正文
 */
@Component
public class ThymeleafMessageContentBuilder implements MessageContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String buildMessage(String templateName, Map<String, Object> datas, String imageResourceName) {
        Context context = new Context();
        for (Map.Entry<String, Object> entry : datas.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        context.setVariable("imageResourceName", imageResourceName);
        return templateEngine.process(templateName, context);
    }
}
