package com.ezreal.infrastructure.repository;

import com.ezreal.domain.doctor.model.entity.DoctorEntity;
import com.ezreal.domain.doctor.model.vo.DepartmentMapper;
import com.ezreal.domain.doctor.repository.DoctorRepository;
import com.ezreal.infrastructure.mapper.MedicalDoctorMapper;
import com.ezreal.infrastructure.po.MedicalDoctor;
import com.ezreal.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Repository
public class DoctorRepositoryImpl implements DoctorRepository {

    @Resource
    private MedicalDoctorMapper doctorMapper;

    @Override
    public List<DoctorEntity> queryDoctorList(Integer pageNo, Integer pageSize) {

        pageNo = (pageNo - 1) * pageSize;
        List<MedicalDoctor> medicalDoctors = doctorMapper.selectAll(pageNo, pageSize);
        return medicalDoctors.stream().map((medicalDoctor -> {
            DoctorEntity doctorEntity = new DoctorEntity();
            doctorEntity.setUserId(medicalDoctor.getUserId());
            doctorEntity.setName(medicalDoctor.getName());
            doctorEntity.setGender(Constants.Gender.getByCode(medicalDoctor.getGender()).getInfo());
            doctorEntity.setPosition(medicalDoctor.getPosition());
            doctorEntity.setDescription(medicalDoctor.getDescription());
            doctorEntity.setPhone(medicalDoctor.getPhone());
            doctorEntity.setDepartmentName(DepartmentMapper.getDepartName(medicalDoctor.getDepartment()));
            return doctorEntity;
        })).collect(Collectors.toList());
    }

    @Override
    public Long queryDoctorListTotal() {
        return doctorMapper.queryDoctorListTotal();
    }

    @Override
    public DoctorEntity queryDoctorEntity(Long userId) {
        MedicalDoctor medicalDoctor = doctorMapper.queryByUserId(userId);
        if (medicalDoctor == null) {
            return null;
        }

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setUserId(medicalDoctor.getUserId());
        doctorEntity.setName(medicalDoctor.getName());
        doctorEntity.setGender(Constants.Gender.getByCode(medicalDoctor.getGender()).getInfo());
        doctorEntity.setPosition(medicalDoctor.getPosition());
        doctorEntity.setDepartmentName(DepartmentMapper.getDepartName(medicalDoctor.getDepartment()));
        doctorEntity.setDescription(medicalDoctor.getDescription());
        doctorEntity.setPhone(medicalDoctor.getPhone());
        return doctorEntity;
    }
}
