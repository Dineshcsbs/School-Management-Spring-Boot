package com.school.management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

	private String name;
	private String address;
	private String email;
	@ManyToOne
	private School school;

	@OneToOne
	private User user;
}
