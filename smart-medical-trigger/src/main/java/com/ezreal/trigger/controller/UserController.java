package com.ezreal.trigger.controller;

import cn.hutool.core.util.StrUtil;
import com.ezreal.domain.user.model.aggregates.UserLoginAggregate;
import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;
import com.ezreal.domain.user.service.MedicalUserService;
import com.ezreal.trigger.dto.*;
import com.ezreal.types.common.Constants;
import com.ezreal.types.common.Response;
import com.ezreal.types.common.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private MedicalUserService medicalUserService;

    @GetMapping()
    @ApiOperation(value = "获取用户登录状态")
    public Response<LoginStateDTO> loginState(@RequestHeader("Authorization") String token) {
        if (StrUtil.isEmpty(token)) {
            return ResultUtils.fail(Constants.ResponseCode.UN_LOGIN);
        }
        DocPlatformUserEntity loginState = medicalUserService.getLoginState(token);

        LoginStateDTO loginStateDTO = LoginStateDTO.builder()
                .userId(loginState.getUserId())
                .type(loginState.getType())
                .email(loginState.getEmail())
                .phone(loginState.getPhone())
                .avatar(loginState.getAvatar())
                .build();

        return ResultUtils.success(loginStateDTO);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Response<LoginResponseDTO> userLogin(@RequestBody LoginRequestDTO requestDTO) {

        UserLoginAggregate userLoginAggregate = new UserLoginAggregate();
        userLoginAggregate.setLoginType(requestDTO.getLoginType());
        userLoginAggregate.setEmail(requestDTO.getEmail());
        userLoginAggregate.setPassword(requestDTO.getPassword());

        UserAuthEntity userAuthEntity = medicalUserService.login(userLoginAggregate);

        if (userAuthEntity.getCode().equals(Constants.ResponseCode.SUCCESS.getCode())) {
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setUserId(userAuthEntity.getUserId());
            loginResponseDTO.setToken(userAuthEntity.getToken());
            loginResponseDTO.setType(userAuthEntity.getType());
            return ResultUtils.success(loginResponseDTO);
        } else {
            return ResultUtils.fail(userAuthEntity.getCode(), userAuthEntity.getInfo());
        }
    }
}
