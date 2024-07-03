package com.school.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDTO {

	private Long questionId;
	private String question;
	private String option1;
	private String option2;
	
}
