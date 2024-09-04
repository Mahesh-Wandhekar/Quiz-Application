package com.nt.UserRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.Entity.AnswerEntity;

public interface AnswerRepo extends JpaRepository<AnswerEntity, Long> {

	
}
