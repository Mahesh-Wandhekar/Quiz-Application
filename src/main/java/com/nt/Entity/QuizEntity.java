package com.nt.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    private String title;

    @OneToMany(mappedBy = "quizentity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<QuestionEntity> questions ;

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<QuestionEntity> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionEntity> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "QuizEntity [quizId=" + quizId + ", title=" + title + ", questions=" + questions + "]";
	}

    
    
}	