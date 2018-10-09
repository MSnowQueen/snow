package com.msnowqueen.mqueen.common;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by zn* on 2018/9/30
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
