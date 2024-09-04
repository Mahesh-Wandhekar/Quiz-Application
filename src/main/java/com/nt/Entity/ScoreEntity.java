package com.nt.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ScoreEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scoreid;
	
	private String right;
	private String wrong;
	
	@ManyToOne()
	@JoinColumn(name="id")
	private UserEntity userId;

	public Long getScoreid() {
		return scoreid;
	}

	public void setScoreid(Long scoreid) {
		this.scoreid = scoreid;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getWrong() {
		return wrong;
	}

	public void setWrong(String wrong) {
		this.wrong = wrong;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	
	
}
