package com.school.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.management.dto.LoginUserDto;
import com.school.management.dto.RegisterUserDto;
import com.school.management.entities.Admin;
import com.school.management.entities.Role;
import com.school.management.entities.School;
import com.school.management.entities.Student;
import com.school.management.entities.Tutor;
import com.school.management.entities.User;
import com.school.management.exception.BadRequestServiceAlertException;
import com.school.management.repository.AdminRepository;
import com.school.management.repository.StudentRepository;
import com.school.management.repository.TutorRepository;
import com.school.management.repository.UserRepository;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class AuthenticationService {
	 @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private AdminRepository adminRepository;
	    @Autowired
	    private TutorRepository tutorRepository;
	    @Autowired
	    private StudentRepository studentRepository;

    public User signup(RegisterUserDto input,Long roll) {
        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(new Role(roll))
                .build();
        
        updateTable(input,roll,userRepository.save(user));
        return user;
    }


    private void updateTable(RegisterUserDto input, Long roll,User user) {
    	School school=new School();
		switch(roll.intValue()) {
		case 1:
			Admin admin=new Admin();
			admin.setEmail(input.getEmail());
			admin.setUser(user);
			admin.setName(input.getFullName());
			admin.setSchool(school.id(input.getSchoolId()));
			adminRepository.saveAndFlush(admin);
			break;
		case 2:
			Tutor tutor=new Tutor();
			tutor.setEmail(input.getEmail());
			tutor.setUser(user);
			tutor.setName(input.getFullName());
			tutor.setSchool(school.id(input.getSchoolId()));
			tutorRepository.saveAndFlush(tutor);
			break;
		case 3:
			Student student=new Student();
			student.setEmail(input.getEmail());
			student.setUser(user);
			student.setName(input.getFullName());
			student.setSchool(school.id(input.getSchoolId()));
			studentRepository.saveAndFlush(student);
			break;
		default :
			new  BadRequestServiceAlertException("Data is not match");
		}
	}


	public User authenticate(LoginUserDto input) throws Exception {
        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new BadRequestServiceAlertException("User not found with email: " + input.getEmail()));

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
        } catch (Exception e) {
            throw new BadRequestServiceAlertException("Authentication failed: " + e.getMessage());
        }

        return user;
    }
}