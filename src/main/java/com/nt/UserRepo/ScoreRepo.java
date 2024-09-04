package com.nt.UserRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.Entity.ScoreEntity;

public interface ScoreRepo extends JpaRepository<ScoreEntity ,Long> {

}
