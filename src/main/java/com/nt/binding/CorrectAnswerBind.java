package com.nt.binding;

import org.springframework.stereotype.Component;

@Component
public class CorrectAnswerBind {

	private Long queId;
	private String selectedOption;
	
	private String rightanswer;
	
	
	

	
	public String getRightanswer() {
		return rightanswer;
	}




	public void setRightanswer(String rightanswer) {
		this.rightanswer = rightanswer;
	}




	public Long getQueId() {
		return queId;
	}




	public void setQueId(Long queId) {
		this.queId = queId;
	}




	public String getSelectedOption() {
		return selectedOption;
	}




	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}




	@Override
	public String toString() {
		return "CorrectAnswerBind [queId=" + queId + ", selectedOption=" + selectedOption + "]";
	}
	
	
	
	
	
	
}
