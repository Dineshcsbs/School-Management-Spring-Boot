package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entities.Tutor;
import com.school.management.entities.TutorCourse;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

	Tutor findByEmail(String email);
	@Query("SELECT tc FROM TutorCourse tc JOIN tc.tutor t WHERE t.email = :email")
	List<TutorCourse> findTutorAndCoursesByEmail(@Param("email") String email);
	
//	@Query(value="select tc.* from tutor_course tc join tutor t on t.id=tc.tutor_id where t.email =:email",nativeQuery = true)
//	List<TutorCourse> findTutorAndCoursesByEmail(@Param("email") String email);

}
