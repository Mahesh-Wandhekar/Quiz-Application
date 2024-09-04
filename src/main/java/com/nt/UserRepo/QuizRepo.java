package com.nt.UserRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nt.Entity.QuizEntity;

public interface QuizRepo extends JpaRepository<QuizEntity, Long> {

	Optional<QuizEntity> findById(Long quidId);
	
	public QuizEntity findByQuizId(Long quizId);

}
