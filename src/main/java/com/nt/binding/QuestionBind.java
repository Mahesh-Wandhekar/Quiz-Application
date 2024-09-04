package com.nt.binding;

import org.springframework.stereotype.Component;

@Component
public class QuestionBind {

	private Long quizId;
	
	private String question;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	
	
	
}
