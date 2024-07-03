package com.school.management.dto;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class StudentDTO {

	private String schoolName;
	private String name;
	private String address;
	private String email;
	
}
