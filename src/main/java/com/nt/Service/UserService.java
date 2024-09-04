package com.nt.Service;

import java.util.List;

import com.nt.Entity.AnswerEntity;
import com.nt.Entity.QuizEntity;
import com.nt.binding.LoginBind;
import com.nt.binding.userBind;

public interface UserService {
	
	public boolean register(userBind bind) throws Exception ;
	
	public boolean login(LoginBind bind) throws Exception;
	
	public List<QuizEntity> userDashboard();
	
	public List<AnswerEntity> showoption(Long queId);
	
	public boolean checkAnswer(Long queId,String selectoption);

}
