package com.ezreal.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezreal.infrastructure.po.DocPlatformUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Ezreal
* @description 针对表【doc_platform_user(用户信息表)】的数据库操作Mapper
* @createDate 2024-02-03 00:01:20
* @Entity com.ezreal.infrastructure.po.DocPlatformUser
*/
@Mapper
public interface DocPlatformUserMapper extends BaseMapper<DocPlatformUser> {

    DocPlatformUser selectByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    DocPlatformUser selectByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    DocPlatformUser selectByUserId(@Param("userId") String userId);

    int updateSelective(DocPlatformUser docPlatformUser);
}




