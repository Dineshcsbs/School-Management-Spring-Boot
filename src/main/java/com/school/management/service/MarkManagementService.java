package com.school.management.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entities.Course;
import com.school.management.entities.MarkManagement;
import com.school.management.entities.Student;
import com.school.management.entities.StudentAnswer;
import com.school.management.repository.MarkManagementRepository;
import com.school.management.repository.StudentAnswerRepository;



@Service
public class MarkManagementService {


	@Autowired
	private StudentAnswerRepository studentAnswerRepository;
	@Autowired
	private MarkManagementRepository markManagementRepository;

	
	public MarkManagement retriveSingleRecord(final Long studentId,final Long courseId) {
		return markManagementRepository.findByStudentIdAndCourseId(studentId,courseId);
	}
	
	public Map<String,String> studentMarkUpdated(){
		final Map<String,String> markResponce=new HashMap<>();
		final List<StudentAnswer> studentAns=studentAnswerRepository.findAll();
		final List<MarkManagement> markDetails=new LinkedList<>();
		for(int i=0;i<studentAns.size();i++) {		
			final Student student1=studentAns.get(i).getStudent();
			final Course course1=studentAns.get(i).getCourse();
			final String stuAns=studentAns.get(i).getStudentAns();
			final String questionAns=studentAns.get(i).getQuestion().getAns();
			if(stuAns.equals(questionAns)) {
				Object type=this.findStudentList(student1,course1,markDetails);
				if(!(type.equals(false))) {
					final MarkManagement m=(MarkManagement)type;
					m.setMark(m.getMark()+1);
				}
				else {
					final MarkManagement markManagement=new MarkManagement();
					markManagement.setMark(1);
					markManagement.setStudent(student1);
					markManagement.setCourse(course1);
					markDetails.add(markManagement);
				}
			}	
		}	
		markResponce.put("Update", "Success");
		this.markManagementRepository.saveAll(markDetails);
		return markResponce;
	}
	private Object findStudentList(final Student student1, final Course course1, final List<MarkManagement> markDetails) {
		for(MarkManagement markTable:markDetails) {
			if(Objects.equals(markTable.getStudent(), student1)&&Objects.equals(markTable.getCourse(), course1)) {
				return markTable;
			}
		}
		return false;
	}	
}
