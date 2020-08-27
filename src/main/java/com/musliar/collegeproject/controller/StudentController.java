package com.musliar.collegeproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musliar.collegeproject.dto.StudentInfoDto;
import com.musliar.collegeproject.exception.DataNotFoundException;
import com.musliar.collegeproject.exception.SuccessMessage;
import com.musliar.collegeproject.service.StudentInfoService;

@RestController
@RequestMapping(value = "/")
public class StudentController {
	@Autowired
	StudentInfoService studentInfoService;

	@PostMapping(value = "studentAdmissionEnrollment")
	public ResponseEntity<StudentInfoDto> readStudentAdmissionInfo(@RequestBody StudentInfoDto studentInfoDto) {
		// StudentDepositDto studentDepositDto=studentInfoDto.getStudentDepositDto();
		StudentInfoDto studentInfoDto2 = studentInfoService.saveStudentEnrollmentInfo(studentInfoDto);
		return new ResponseEntity<StudentInfoDto>(studentInfoDto2, HttpStatus.OK);
	}

	@GetMapping(value = "getAdmissionDetails/{id}")
	public ResponseEntity<StudentInfoDto> getData(@PathVariable Integer id) throws DataNotFoundException {
		System.out.println(id);
		StudentInfoDto studentInfoDto2 = studentInfoService.getStudentData(id);
		return new ResponseEntity<StudentInfoDto>(studentInfoDto2, HttpStatus.OK);
	}

	@GetMapping(value = "getAllAdmissionDetails")
	public ResponseEntity<List<StudentInfoDto>> getAllData() throws DataNotFoundException {
		List<StudentInfoDto> studentInfoDto = studentInfoService.getAllStudentData();
		return new ResponseEntity<List<StudentInfoDto>>(studentInfoDto, HttpStatus.OK);
	}

	@GetMapping(value = "removeEnrollment/{id}")
	public ResponseEntity<Object> removeData(@PathVariable Integer id) throws DataNotFoundException {
		studentInfoService.removeStudentData(id);
		SuccessMessage successMessage = new SuccessMessage("Data removed successfully");
		return new ResponseEntity<Object>(successMessage, HttpStatus.OK);
	}

	@PostMapping(value = "updateEnrollement")
	public ResponseEntity<StudentInfoDto> updateData(@RequestBody StudentInfoDto studentInfoDto)
			throws DataNotFoundException {
		StudentInfoDto stuDto = studentInfoService.updateStudentData(studentInfoDto);
		return new ResponseEntity<StudentInfoDto>(stuDto, HttpStatus.OK);
	}
}
