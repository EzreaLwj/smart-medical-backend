package java.com.ezreal.infrastructure.repository;

import cn.hutool.core.util.RandomUtil;
import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;
import com.ezreal.domain.user.model.entity.UserRegisterEntity;
import com.ezreal.domain.user.repository.DocPlatformUserRepository;
import com.ezreal.infrastructure.mapper.DocPlatformUserMapper;
import com.ezreal.infrastructure.po.DocPlatformUser;
import com.ezreal.types.common.Constants;
import com.ezreal.types.exception.BusinessException;
import com.ezreal.types.uitls.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Slf4j
@Repository
public class DocPlatformUserRepositoryImpl implements DocPlatformUserRepository {

    @Resource
    private DocPlatformUserMapper userMapper;

    @Override
    public UserAuthEntity userRegister(UserRegisterEntity registerEntity) {
        DocPlatformUser docPlatformUser = new DocPlatformUser();

        String userId = null;
        try {
            userId = RandomUtil.randomNumbers(10);
            docPlatformUser.setUserId(userId);
            docPlatformUser.setNickName(registerEntity.getNickName());
            docPlatformUser.setEmail(registerEntity.getEmail());
            docPlatformUser.setPassword(registerEntity.getPassword());
            docPlatformUser.setPhone(registerEntity.getPhone());
            docPlatformUser.setAvatar("");
            userMapper.insert(docPlatformUser);
        } catch (DuplicateKeyException e) {
            log.error("用户已存在：{}", registerEntity);
            throw new BusinessException(Constants.ResponseCode.USER_EXIST);
        }

        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.setUserId(userId);
        HashMap<String, String> claimMap = new HashMap<>();
        claimMap.put("userId", userId);
        String token = JwtUtils.encode(docPlatformUser.getEmail(), claimMap, 10);
        userAuthEntity.setToken(token);
        userAuthEntity.setCode(Constants.ResponseCode.SUCCESS.getCode());
        userAuthEntity.setInfo(Constants.ResponseCode.SUCCESS.getInfo());
        return userAuthEntity;
    }

    @Override
    public boolean updateUserInfo(DocPlatformUserEntity docPlatformUserEntity) {

        DocPlatformUser docPlatformUser = new DocPlatformUser();
        docPlatformUser.setUserId(docPlatformUserEntity.getUserId());
        docPlatformUser.setNickName(docPlatformUserEntity.getNickName());
        docPlatformUser.setDescription(docPlatformUserEntity.getDescription());
        docPlatformUser.setEmail(docPlatformUserEntity.getEmail());
        docPlatformUser.setAccount(docPlatformUserEntity.getAccount());
        docPlatformUser.setPassword(docPlatformUserEntity.getPassword());
        docPlatformUser.setPhone(docPlatformUserEntity.getPhone());
        docPlatformUser.setAvatar(docPlatformUserEntity.getAvatar());
        docPlatformUser.setLocation(docPlatformUserEntity.getLocation());

        int update = userMapper.updateSelective(docPlatformUser);
        return update > 0;
    }

    @Override
    public UserAuthEntity checkLoginByAccount(String account, String password) {
        DocPlatformUser docPlatformUser = userMapper.selectByAccountAndPassword(account, password);
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        return getUserAuthEntity(account, docPlatformUser, userAuthEntity);
    }

    @Override
    public UserAuthEntity checkLoginByEmail(String email, String password) {
        DocPlatformUser docPlatformUser = userMapper.selectByEmailAndPassword(email, password);
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        return getUserAuthEntity(email, docPlatformUser, userAuthEntity);
    }

    private UserAuthEntity getUserAuthEntity(String account, DocPlatformUser docPlatformUser, UserAuthEntity userAuthEntity) {
        if (docPlatformUser == null) {
            userAuthEntity.setCode(Constants.ResponseCode.LOGIN_FAIL.getCode());
            userAuthEntity.setInfo(Constants.ResponseCode.LOGIN_FAIL.getInfo());
            return userAuthEntity;
        }
        userAuthEntity.setCode(Constants.ResponseCode.SUCCESS.getCode());
        userAuthEntity.setInfo(Constants.ResponseCode.SUCCESS.getInfo());
        HashMap<String, String> claimMap = new HashMap<>();
        claimMap.put("userId", docPlatformUser.getUserId());
        String token = JwtUtils.encode(account, claimMap, 10);
        userAuthEntity.setToken(token);
        userAuthEntity.setUserId(docPlatformUser.getUserId());
        return userAuthEntity;
    }


    @Override
    public DocPlatformUserEntity queryUserInfo(String userId) {
        DocPlatformUser docPlatformUser = userMapper.selectByUserId(userId);
        DocPlatformUserEntity userEntity = new DocPlatformUserEntity();
        userEntity.setUserId(docPlatformUser.getUserId());
        userEntity.setNickName(docPlatformUser.getNickName());
        userEntity.setDescription(docPlatformUser.getDescription());
        userEntity.setEmail(docPlatformUser.getEmail());
        userEntity.setAccount(docPlatformUser.getAccount());
        userEntity.setPassword(docPlatformUser.getPassword());
        userEntity.setPhone(docPlatformUser.getPhone());
        userEntity.setAvatar(docPlatformUser.getAvatar());
        userEntity.setLocation(docPlatformUser.getLocation());
        return userEntity;
    }

}
