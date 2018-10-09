package com.msnowqueen.mqueen.mail;

import java.util.Map;

/**
 * Created by zn* on 2018/10/8
 */
public interface MessageContentBuilder {

    String buildMessage(String templateName, Map<String, Object> datas, String imageResourceName);

}
