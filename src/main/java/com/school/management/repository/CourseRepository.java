package com.school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.entities.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	
}
