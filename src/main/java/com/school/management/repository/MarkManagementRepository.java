package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.entities.MarkManagement;



@Repository
public interface MarkManagementRepository extends JpaRepository<MarkManagement, Long>{

	List<MarkManagement> findByStudentIdAndCourseId(Long studentId, Long courseId);

	
}
