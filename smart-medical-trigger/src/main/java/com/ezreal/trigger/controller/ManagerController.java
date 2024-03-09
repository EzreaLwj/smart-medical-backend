package com.ezreal.trigger.controller;

import com.ezreal.domain.manager.model.aggregate.AddAccountAggregate;
import com.ezreal.domain.manager.model.entity.MedicalUserEntity;
import com.ezreal.domain.manager.service.ManagerService;
import com.ezreal.trigger.dto.manager.AddAccountRequest;
import com.ezreal.trigger.dto.manager.AddAccountResponse;
import com.ezreal.types.common.Response;
import com.ezreal.types.common.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Api(tags = "管理员模块")
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @ApiOperation("增加用户账号")
    @PostMapping("/addAccount")
    public Response<AddAccountResponse> addUserAccount(@RequestBody AddAccountRequest addAccountRequest) {

        AddAccountAggregate addAccountAggregate = new AddAccountAggregate();
        addAccountAggregate.setEmail(addAccountRequest.getEmail());
        addAccountAggregate.setPassword(addAccountRequest.getPassword());
        addAccountAggregate.setPhone(addAccountRequest.getPhone());
        addAccountAggregate.setType(addAccountRequest.getType());

        MedicalUserEntity medicalUserEntity = managerService.addAccount(addAccountAggregate);
        AddAccountResponse addAccountResponse = new AddAccountResponse();
        addAccountResponse.setUserId(medicalUserEntity.getUserId());
        addAccountResponse.setEmail(medicalUserEntity.getEmail());
        addAccountResponse.setPassword(medicalUserEntity.getPassword());
        return ResultUtils.success(addAccountResponse);
    }
}
