package com.school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.entities.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

}
