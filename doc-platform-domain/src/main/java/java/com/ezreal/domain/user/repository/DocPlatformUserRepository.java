package java.com.ezreal.domain.user.repository;

import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;
import com.ezreal.domain.user.model.entity.UserRegisterEntity;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
public interface DocPlatformUserRepository {

    /**
     * 账号密码登录
     * @param account 账号
     * @param password 密码
     * @return
     */
    UserAuthEntity checkLoginByAccount(String account, String password);

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return
     */
    DocPlatformUserEntity queryUserInfo(String userId);

    /**
     * 用户注册
     * @param registerEntity
     * @return
     */
    UserAuthEntity userRegister(UserRegisterEntity registerEntity);

    /**
     * 通过邮箱登录
     * @param account
     * @param password
     * @return
     */
    UserAuthEntity checkLoginByEmail(String account, String password);

    /**
     * 更新用户信息
     * @param docPlatformUser
     */
    boolean updateUserInfo(DocPlatformUserEntity docPlatformUser);
}
