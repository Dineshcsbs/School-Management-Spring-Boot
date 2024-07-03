package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entities.TutorCourse;


@Repository
public interface TutorCourseRepository extends JpaRepository<TutorCourse, Long>{

	@Query("SELECT tc FROM TutorCourse tc " +
	           "JOIN tc.tutor t " +
	           "WHERE (:schoolId IS NULL OR t.school.id = :schoolId) " +
	           "AND (:tutorName IS NULL OR t.name LIKE %:tutorName%) " +
	           "AND (:tutorAddress IS NULL OR t.address LIKE %:tutorAddress%)")
	    List<TutorCourse> findTutorCoursesByFilter(@Param("schoolId") Long schoolId,
	                                               @Param("tutorName") String tutorName,
	                                               @Param("tutorAddress") String tutorAddress);
	
	
	@Query(value = "SELECT tc.tutor_id, ts.salary FROM tutor_course tc JOIN tutor_salary ts ON tc.tutor_id = ts.tutor_id ", nativeQuery = true)
	List<Object[]> findTutorsWithSalaryAboveNative();
	

}
