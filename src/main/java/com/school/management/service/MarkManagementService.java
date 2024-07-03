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

	
	public List<MarkManagement> retriveSingleRecord(Long studentId,Long courseId) {
		return markManagementRepository.findByStudentIdAndCourseId(studentId,courseId);
	}
	
	public Map<String,String> studentMarkUpdated(){
		Map<String,String> markResponce=new HashMap<>();
		List<StudentAnswer> studentAns=studentAnswerRepository.findAll();
		List<MarkManagement> markDetails=new LinkedList<>();
		for(int i=0;i<studentAns.size();i++) {		
			Student student1=studentAns.get(i).getStudent();
			Course course1=studentAns.get(i).getCourse();
			String stuAns=studentAns.get(i).getStudentAns();
			String questionAns=studentAns.get(i).getQuestion().getAns();
			if(stuAns.equals(questionAns)) {
				Object type=this.findStudentList(student1,course1,markDetails);
				if(!(type.equals(false))) {
					MarkManagement m=(MarkManagement)type;
					m.setMark(m.getMark()+1);
				}
				else {
					MarkManagement markManagement=new MarkManagement();
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
	private Object findStudentList(Student student1, Course course1, List<MarkManagement> markDetails) {
		for(MarkManagement markTable:markDetails) {
			if(Objects.equals(markTable.getStudent(), student1)&&Objects.equals(markTable.getCourse(), course1)) {
				return markTable;
			}
		}
		return false;
	}	
}
