package com.musliar.collegeproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musliar.collegeproject.dto.StudentInfoDto;
import com.musliar.collegeproject.entity.StudentInfoEntity;
import com.musliar.collegeproject.exception.DataNotFoundException;
import com.musliar.collegeproject.repository.AdmissionEnrollmentRepo;

@Service
public class StudentInfoService {
	@Autowired
	AdmissionEnrollmentRepo admissionEnrollmentRepo;
	
	ModelMapper modelMapper=new ModelMapper();
	final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	public StudentInfoDto saveStudentEnrollmentInfo(StudentInfoDto studentInfoDto) {
		StudentInfoEntity studentInfoEntity=new StudentInfoEntity();
		modelMapper.map(studentInfoDto, studentInfoEntity);
		StudentInfoEntity ret=admissionEnrollmentRepo.save(studentInfoEntity);
		studentInfoDto=modelMapper.map(ret, StudentInfoDto.class);
		//Id is not returned to UI due to security reasons.
		studentInfoDto.setId(null);
		studentInfoDto.setStatus("success");
		
		logger.info("Data stored into database");
		
		return studentInfoDto;
	}
	public StudentInfoDto getStudentData(Integer id) throws DataNotFoundException{
		StudentInfoDto studentInfoDto;
		Optional<StudentInfoEntity> ret=admissionEnrollmentRepo.findById(id);
		if (ret.isPresent()) {
			studentInfoDto=modelMapper.map(ret.get(), StudentInfoDto.class);
			studentInfoDto.setStatus("success");
			return studentInfoDto;
		} else {
//			StudentInfoDto studentInfoDtoo=new StudentInfoDto();
//			studentInfoDtoo.setStatus("No record found for this id");
//			return studentInfoDtoo;
			throw new DataNotFoundException("No Record Exists!");
		}
	}
	public List<StudentInfoDto> getAllStudentData() throws DataNotFoundException {
		StudentInfoDto studentInfoDto=new StudentInfoDto();
		List<StudentInfoEntity> studentInfoEntity1=admissionEnrollmentRepo.findAll();
		if(studentInfoEntity1.isEmpty()) {
			throw new DataNotFoundException("No Record Exists!");
		}
		else
		{
			return ((List<StudentInfoEntity>) admissionEnrollmentRepo.findAll()).stream()
					.map(this::convertEntityToDto).collect(Collectors.toList());
		}
	}
	private StudentInfoDto convertEntityToDto(StudentInfoEntity studentInfoEntity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		StudentInfoDto studentInfoDto=modelMapper.map(studentInfoEntity, StudentInfoDto.class);
		studentInfoDto.setStatus("success");
		return studentInfoDto;
	}
	public void removeStudentData(Integer id) throws DataNotFoundException {
		Optional<StudentInfoEntity> studentInfoEntity=admissionEnrollmentRepo.findById(id);
		if(studentInfoEntity.isPresent()) {
			admissionEnrollmentRepo.deleteById(id);
		}
		else
		{
			throw new DataNotFoundException("No Record Exists!");
		}
	}
	public StudentInfoDto updateStudentData(StudentInfoDto studentInfoDto) throws DataNotFoundException {
		Optional<StudentInfoEntity> studentInfoEntity=admissionEnrollmentRepo.findById(studentInfoDto.getId());
		if(studentInfoEntity.isPresent()) {
			//Also possible to convert new dto to entity that will write later.
			StudentInfoEntity s=studentInfoEntity.get();
			s.setName(studentInfoDto.getName());
			s.setPhone(studentInfoDto.getPhone());
			s.setCourse(studentInfoDto.getCourse());
			s.setPlace(studentInfoDto.getPlace());
			StudentInfoEntity studentInfoEntity2=admissionEnrollmentRepo.save(s);
			StudentInfoDto studentInfoDto1=modelMapper.map(studentInfoEntity2, StudentInfoDto.class);
			studentInfoDto1.setStatus("success");
			return studentInfoDto1;
		}
		else {
			//Here throws an exception status. will be put later.
			//studentInfoDto.setStatus("failed");
			//return studentInfoDto;
			throw new DataNotFoundException("Invalid Id");
		}
	}
}
