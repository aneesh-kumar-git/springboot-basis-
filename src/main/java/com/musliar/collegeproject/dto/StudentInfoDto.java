package com.musliar.collegeproject.dto;
import lombok.Data;

@Data
public class StudentInfoDto {
	private Integer id;
	private String name;
	private String place;
	private String course;
	private Long phone;
	private String status;
	@Override
	public String toString() {
		return "StudentInfoDto [id=" + id + ", name=" + name + ", place=" + place + ", course=" + course + ", phone="
				+ phone + ", status=" + status + "]";
	}
	
}
