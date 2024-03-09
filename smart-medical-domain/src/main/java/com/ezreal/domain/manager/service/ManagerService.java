package com.ezreal.domain.manager.service;

import com.ezreal.domain.manager.model.aggregate.AddAccountAggregate;
import com.ezreal.domain.manager.model.entity.MedicalUserEntity;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
public interface ManagerService {

    public MedicalUserEntity addAccount(AddAccountAggregate addAccountAggregate);
}
