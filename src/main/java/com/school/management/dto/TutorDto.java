package com.school.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TutorDto {

	private String name;
	private String address;
	private String course;
	private String schoolName;
	
}
