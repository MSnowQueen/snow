package com.msnowqueen.mqueen.dao;

import com.msnowqueen.mqueen.pojo.MUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by zn* on 2018/9/28
 */
@Component
public interface MUserMapper {

    @Insert(" INSERT INTO muser(muser_name, muser_createtime) " +
            " VALUES(#{muserName}, #{muserCreatetime}) ")
    int insertUser(MUser user);

}
