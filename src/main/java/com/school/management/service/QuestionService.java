package com.school.management.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.school.management.dto.QuestionDTO;
import com.school.management.entities.Question;
import com.school.management.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public Question createQuestion(Question question) {
		return questionRepository.save(question);
	}

	public List<QuestionDTO> displayQuestion(final Long courseId) {

		final List<QuestionDTO> listOfQuestion = new LinkedList<>();

		final List<Question> question = questionRepository.findAllByCourseId(courseId);
																					

		final Iterator<Question> questionIterator = question.iterator();

		while (questionIterator.hasNext()) {
			listOfQuestion.add(convertToDTO(questionIterator.next()));
		}
		return listOfQuestion;
	}

	public List<QuestionDTO> pageConcept(final int pageNo, final int pageSize, final String fieldName, final Sort.Direction direction,
			final Long courseId) {

		final Page<Question> response = questionRepository.findByCourseId(courseId,
				PageRequest.of(pageNo, pageSize, Sort.by(direction, fieldName)));

		final Page<QuestionDTO> responseDTO = response.map(this::convertToDTO);
		return responseDTO.getContent();

	}

	public QuestionDTO convertToDTO(final Question question) {

		return QuestionDTO.builder().questionId(question.getId()).question(question.getQuestion())
				.option1(question.getOption1()).option2(question.getOption2()).build();

	}

}
