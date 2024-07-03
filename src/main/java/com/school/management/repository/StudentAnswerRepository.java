package com.school.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entities.StudentAnswer;


@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long>{

	@Query(value="SELECT * FROM STUDENTANSWER "
			+"WHERE (:studentId IS NULL OR student_id =:studentId)"
			+"AND (:courseId IS NULL OR course_id = :courseId)"
			,nativeQuery=true)
	List<StudentAnswer> searchStudentAnswer(@Param("studentId")Long studentId,@Param("courseId") Long courseId);

	List<StudentAnswer> findByOrderByQuestionAsc();

	Optional<StudentAnswer> findByStudentIdAndCourseId(Long studentId, Long courseId);

	List<StudentAnswer> findAllByStudentId(Long studentId);

	


}
