package com.school.management.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.entities.Question;



@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

	Page<Question> findByCourseId(Long courseId, PageRequest pageRequest);

	List<Question> findAllByCourseId(Long id);

	
	List<Question> findALLByIdIn(List<Long> questionId);

}
