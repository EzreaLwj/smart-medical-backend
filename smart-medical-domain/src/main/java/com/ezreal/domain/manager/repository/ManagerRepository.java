package com.ezreal.domain.manager.repository;

import com.ezreal.domain.manager.model.aggregate.AddAccountAggregate;
import com.ezreal.domain.manager.model.aggregate.AddDoctorInfoAggregate;
import com.ezreal.domain.manager.model.entity.MedicalUserEntity;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
public interface ManagerRepository {

    MedicalUserEntity addAccount(AddAccountAggregate addAccountAggregate);


    MedicalUserEntity addDoctorInfo(AddDoctorInfoAggregate addDoctorInfoAggregate);
}
