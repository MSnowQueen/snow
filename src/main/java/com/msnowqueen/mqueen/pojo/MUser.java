package com.msnowqueen.mqueen.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zn* on 2018/9/28
 */
@Data
@Component
public class MUser {

    private int muserId;
    private String muserName;
    private String muserPassword;
    private String muserPhone;
    private String muserEmail;
    private int muserRight;
    private String muserQuestion;
    private String muserAnswer;
    private Date muserCreatetime;
    private Date muserUpdatetime;

}
