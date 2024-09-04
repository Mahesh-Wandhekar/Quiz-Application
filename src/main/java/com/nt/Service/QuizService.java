package com.nt.Service;

import java.util.List;

import com.nt.Entity.QuestionEntity;
import com.nt.Entity.QuizEntity;
import com.nt.Entity.ScoreEntity;
import com.nt.binding.AnswerBind;
import com.nt.binding.CorrectAnswerBind;
import com.nt.binding.QuestionBind;
import com.nt.binding.QuizBind;
import com.nt.binding.ScoreBind;

public interface QuizService {

	
	public boolean addquiz(QuizBind quizBind);
	
	public List<QuizEntity> viewquiz();
	
	public boolean addque( QuestionBind questionBind);
	
	public List<QuestionEntity> viewque(Long quizId);
	
	public boolean addoption(AnswerBind answerBind);
	
	public boolean deleteque(Long queId);
	
	public boolean deletequiz(Long quizId);
	
	public QuizEntity  editquiz(Long quizId);
	
	public boolean editOrSave(QuizEntity entity);
	
	public boolean checkanswer(CorrectAnswerBind  answerBind);
	
	public ScoreBind getscore();
	
}
