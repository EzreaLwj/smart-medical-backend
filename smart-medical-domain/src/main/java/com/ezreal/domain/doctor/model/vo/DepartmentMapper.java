package com.ezreal.domain.doctor.model.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
public class DepartmentMapper {

    private static Map<Integer, String> departmentMap = new HashMap<>();

    static {
        departmentMap.put(1, "内科");
        departmentMap.put(2, "外科");
        departmentMap.put(3, "皮肤科");
    }

    public static String getDepartName(Integer departmentId) {
        return departmentMap.get(departmentId);
    }
}
