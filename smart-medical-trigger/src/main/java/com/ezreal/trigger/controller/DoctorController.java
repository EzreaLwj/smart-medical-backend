package com.ezreal.trigger.controller;

import com.ezreal.domain.doctor.model.resp.DoctorQueryListResponse;
import com.ezreal.domain.doctor.service.DoctorService;
import com.ezreal.trigger.dto.doctor.QueryDoctorRequest;
import com.ezreal.trigger.dto.doctor.QueryDoctorResponse;
import com.ezreal.types.common.Response;
import com.ezreal.types.common.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Api("医生模块")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @ApiOperation("查询医生列表")
    @GetMapping("/doctor")
    public Response<QueryDoctorResponse> queryDoctorList(QueryDoctorRequest queryDoctorRequest) {
        Integer pageNo = queryDoctorRequest.getPageNo();
        Integer pageSize = queryDoctorRequest.getPageSize();

        DoctorQueryListResponse response = doctorService.queryDoctorList(pageNo, pageSize);

        QueryDoctorResponse queryDoctorResponse = new QueryDoctorResponse();
        queryDoctorResponse.setDoctorEntityList(response.getDoctorEntityList());
        queryDoctorResponse.setTotal(response.getTotal());
        return ResultUtils.success(queryDoctorResponse);
    }
}
