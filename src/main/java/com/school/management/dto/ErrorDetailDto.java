package com.school.management.dto;

import java.util.Date;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetailDto {

	@Nullable
    private int status;
	@Nullable
    private String message;
    @Nullable
    private Date stamp;
    @Nullable
    private String description;
    
}