package com.musliar.collegeproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musliar.collegeproject.entity.StudentInfoEntity;
@Repository
public interface AdmissionEnrollmentRepo extends JpaRepository<StudentInfoEntity, Integer>{

}
