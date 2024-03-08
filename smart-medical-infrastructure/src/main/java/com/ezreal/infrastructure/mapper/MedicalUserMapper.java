package com.ezreal.infrastructure.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ezreal.infrastructure.po.MedicalUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Ezreal
* @description 针对表【medical_user(用户登录表)】的数据库操作Mapper
* @createDate 2024-03-09 00:37:57
* @Entity com.ezreal.infrastructure.po.MedicalUser
*/
@Mapper
public interface MedicalUserMapper extends BaseMapper<MedicalUser> {
    MedicalUser selectByEmailAndPasswordAndType(@Param("email") String email, @Param("password") String password, @Param("type") Integer type);

    MedicalUser selectByUserId(@Param("userId") Long userId);
}




