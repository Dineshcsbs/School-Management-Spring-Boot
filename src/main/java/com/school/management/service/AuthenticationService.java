package com.school.management.service;

import java.util.regex.Pattern;

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

	public User signup(final RegisterUserDto input, final Long roll) {
		if(!(emailValidation(input.getEmail()))) {
			throw new BadRequestServiceAlertException(400,"email is not format");
		}
		if(!(passwordValidation(input.getPassword()))){
			throw new BadRequestServiceAlertException(400,"password is not valid");
		}
		
		final User user = User.builder().fullName(input.getFullName()).email(input.getEmail())
				.password(passwordEncoder.encode(input.getPassword())).role(new Role(roll)).build();

		updateTable(input, roll, userRepository.save(user));
		return user;
	}

	

	private void updateTable(final RegisterUserDto input, final Long roll, final User user) {
		final School school = new School();
		switch (roll.intValue()) {
		case 1:
			final Admin admin = new Admin();
			admin.setEmail(input.getEmail());
			admin.setUser(user);
			admin.setName(input.getFullName());
			admin.setSchool(school.id(input.getSchoolId()));
			adminRepository.saveAndFlush(admin);
			break;
		case 2:
			final Tutor tutor = new Tutor();
			tutor.setEmail(input.getEmail());
			tutor.setUser(user);
			tutor.setName(input.getFullName());
			tutor.setSchool(school.id(input.getSchoolId()));
			tutorRepository.saveAndFlush(tutor);
			break;
		case 3:
			final Student student = new Student();
			student.setEmail(input.getEmail());
			student.setUser(user);
			student.setName(input.getFullName());
			student.setSchool(school.id(input.getSchoolId()));
			studentRepository.saveAndFlush(student);
			break;
		default:
			new BadRequestServiceAlertException(400,"Data is not match");
		}
	}

	public User authenticate(final LoginUserDto input) throws Exception {
		final User user = userRepository.findByEmail(input.getEmail()).orElseThrow(
				() -> new BadRequestServiceAlertException(404,"User not found with email: " + input.getEmail()));

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
		} catch (Exception e) {
			throw new BadRequestServiceAlertException(400,"Authentication failed: " + e.getMessage());
		}

		return user;
	}

	public boolean emailValidation(String email) {
		return Pattern.compile("^[a-z0-9+_.-]+@(gmail|yahoo|outlook|zoho)\\.com$").matcher(email).matches();
	}
	private boolean passwordValidation(String password) {
		String pass = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		return Pattern.compile(pass).matcher(password).matches();
	}
}