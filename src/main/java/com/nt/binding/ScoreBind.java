package com.nt.binding;

import org.springframework.stereotype.Component;

@Component
public class ScoreBind {

	private Integer attened;
	private Integer right;
	private Integer wrong;
	public Integer getAttened() {
		return attened;
	}
	public void setAttened(Integer attened) {
		this.attened = attened;
	}
	public Integer getRight() {
		return right;
	}
	public void setRight(Integer right) {
		this.right = right;
	}
	public Integer getWrong() {
		return wrong;
	}
	public void setWrong(Integer wrong) {
		this.wrong = wrong;
	}
	
	
}
