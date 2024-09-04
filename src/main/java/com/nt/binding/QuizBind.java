package com.nt.binding;

import org.springframework.stereotype.Component;

@Component
public class QuizBind {

	private Long quizId;

    private String title;

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
    
    
}
