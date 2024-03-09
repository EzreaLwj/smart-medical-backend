package com.ezreal.domain.manager.service.impl;

import com.ezreal.domain.manager.model.aggregate.AddAccountAggregate;
import com.ezreal.domain.manager.model.aggregate.AddDoctorInfoAggregate;
import com.ezreal.domain.manager.model.entity.MedicalUserEntity;
import com.ezreal.domain.manager.repository.ManagerRepository;
import com.ezreal.domain.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public MedicalUserEntity addAccount(AddAccountAggregate addAccountAggregate) {
        return managerRepository.addAccount(addAccountAggregate);
    }

    @Override
    public MedicalUserEntity addDoctorInfo(AddDoctorInfoAggregate addDoctorInfoAggregate) {
        return managerRepository.addDoctorInfo(addDoctorInfoAggregate);
    }

}
