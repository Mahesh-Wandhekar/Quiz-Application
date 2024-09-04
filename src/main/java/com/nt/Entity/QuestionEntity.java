package com.nt.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queId;

    private String question;
    
    @ManyToOne
    @JoinColumn(name="quiz_Id")
    private QuizEntity quizentity;
    
    @OneToMany(mappedBy = "questionEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AnswerEntity> answerEntities;

	public Long getQueId() {
		return queId;
	}

	public void setQueId(Long queId) {
		this.queId = queId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public QuizEntity getQuizentity() {
		return quizentity;
	}

	public void setQuizentity(QuizEntity quizentity) {
		this.quizentity = quizentity;
	}

	public List<AnswerEntity> getAnswerEntities() {
		return answerEntities;
	}

	public void setAnswerEntities(List<AnswerEntity> answerEntities) {
		this.answerEntities = answerEntities;
	}

    
    
    
}
