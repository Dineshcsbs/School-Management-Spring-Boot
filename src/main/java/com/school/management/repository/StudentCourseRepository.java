package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entities.StudentCourse;


@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long>{


	List<StudentCourse> findAllByStudentId(Long id);

	@Query("SELECT sc FROM StudentCourse sc WHERE sc.student.id = :studentId AND sc.course.id = :courseId")
    StudentCourse findByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

}
