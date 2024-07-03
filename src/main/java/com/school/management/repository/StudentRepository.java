package com.school.management.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{


	Optional<Student> findByEmail(String email);


	@Query("SELECT s FROM Student s LEFT JOIN s.school school WHERE " +
		       "s.name LIKE %:search% OR " +
		       "s.address LIKE %:search% OR " +
		       "s.email LIKE %:search% OR " +
		       "school.name LIKE %:search%")
		Page<Student> searchStudents(@Param("search") String search, Pageable pageable);


}
