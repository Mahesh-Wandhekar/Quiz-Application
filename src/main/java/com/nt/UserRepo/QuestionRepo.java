package com.nt.UserRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nt.Entity.QuestionEntity;

public interface QuestionRepo extends JpaRepository<QuestionEntity, Long>{

	
	
}
