package com.ezreal.trigger.controller;

import cn.hutool.core.util.StrUtil;
import com.ezreal.domain.user.model.aggregates.UserLoginAggregate;
import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;
import com.ezreal.domain.user.model.entity.UserRegisterEntity;
import com.ezreal.domain.user.service.DocPlatformUserService;
import com.ezreal.trigger.dto.*;
import com.ezreal.types.common.Constants;
import com.ezreal.types.common.Response;
import com.ezreal.types.common.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private DocPlatformUserService docPlatformUserService;

    @GetMapping()
    @ApiOperation(value = "获取用户登录状态")
    public Response<LoginStateDTO> loginState(@RequestHeader("Authorization") String token) {
        if (StrUtil.isEmpty(token)) {
            return ResultUtils.fail(Constants.ResponseCode.UN_LOGIN);
        }
        DocPlatformUserEntity loginState = docPlatformUserService.getLoginState(token);

        LoginStateDTO loginStateDTO = LoginStateDTO.builder()
                .userId(loginState.getUserId())
                .nickName(loginState.getNickName())
                .description(loginState.getDescription())
                .email(loginState.getEmail())
                .account(loginState.getAccount())
                .password(loginState.getPassword())
                .phone(loginState.getPhone())
                .avatar(loginState.getAvatar())
                .location(loginState.getLocation())
                .build();

        return ResultUtils.success(loginStateDTO);
    }

    @PutMapping
    public Response<String> updateUserInfo(@RequestBody @Validated UserUpdateDTO userUpdateDTO) {

        DocPlatformUserEntity docPlatformUser = DocPlatformUserEntity.builder()
                .userId(userUpdateDTO.getUserId())
                .nickName(userUpdateDTO.getNickName())
                .description(userUpdateDTO.getDescription())
                .email(userUpdateDTO.getEmail())
                .account(userUpdateDTO.getAccount())
                .password(userUpdateDTO.getPassword())
                .phone(userUpdateDTO.getPhone())
                .avatar(userUpdateDTO.getAvatar())
                .location(userUpdateDTO.getLocation())
                .build();

        boolean updated = docPlatformUserService.updateUserInfo(docPlatformUser);
        if (updated) {
            return ResultUtils.success("update success");
        }
        return ResultUtils.fail(Constants.ResponseCode.UPDATE_FAIL);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Response<LoginResponseDTO> userLogin(@RequestBody LoginRequestDTO requestDTO) {

        UserLoginAggregate userLoginAggregate = new UserLoginAggregate();
        userLoginAggregate.setLoginType(requestDTO.getLoginType());
        userLoginAggregate.setAccount(requestDTO.getAccount());
        userLoginAggregate.setPassword(requestDTO.getPassword());

        UserAuthEntity userAuthEntity = docPlatformUserService.login(userLoginAggregate);

        if (userAuthEntity.getCode().equals(Constants.ResponseCode.SUCCESS.getCode())) {
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setUserId(userAuthEntity.getUserId());
            loginResponseDTO.setToken(userAuthEntity.getToken());
            return ResultUtils.success(loginResponseDTO);
        } else {
            return ResultUtils.fail(userAuthEntity.getCode(), userAuthEntity.getInfo());
        }
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Response<LoginResponseDTO> userRegister(@RequestBody @Validated LoginRegisterDTO registerDTO) {

        UserRegisterEntity registerEntity = UserRegisterEntity.builder()
                .nickName(registerDTO.getNickName())
                .email(registerDTO.getEmail())
                .password(registerDTO.getPassword())
                .phone(registerDTO.getPhone())
                .build();

        UserAuthEntity userAuthEntity = docPlatformUserService.register(registerEntity);

        if (userAuthEntity.getCode().equals(Constants.ResponseCode.SUCCESS.getCode())) {
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setUserId(userAuthEntity.getUserId());
            loginResponseDTO.setToken(userAuthEntity.getToken());
            return ResultUtils.success(loginResponseDTO);
        } else {
            return ResultUtils.fail(userAuthEntity.getCode(), userAuthEntity.getInfo());
        }
    }
}
