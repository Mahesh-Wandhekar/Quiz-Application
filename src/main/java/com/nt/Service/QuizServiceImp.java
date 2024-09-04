package com.nt.Service;

import java.beans.Beans;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Entity.AnswerEntity;
import com.nt.Entity.QuestionEntity;
import com.nt.Entity.QuizEntity;
import com.nt.Entity.ScoreEntity;
import com.nt.Entity.UserEntity;
import com.nt.UserRepo.AnswerRepo;
import com.nt.UserRepo.QuestionRepo;
import com.nt.UserRepo.QuizRepo;
import com.nt.UserRepo.ScoreRepo;
import com.nt.UserRepo.UserRepo;
import com.nt.binding.AnswerBind;
import com.nt.binding.CorrectAnswerBind;
import com.nt.binding.QuestionBind;
import com.nt.binding.QuizBind;
import com.nt.binding.ScoreBind;

import jakarta.servlet.http.HttpSession;

@Service
public class QuizServiceImp implements QuizService {

	@Autowired
	private QuizRepo quizRepo;

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private AnswerRepo answerRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ScoreRepo scoreRepo;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean addquiz(QuizBind quizBind) {
		QuizEntity quizEntity = new QuizEntity();
		BeanUtils.copyProperties(quizBind, quizEntity);
		quizRepo.save(quizEntity);
		return true;
	}

	@Override
	public List<QuizEntity> viewquiz() {
		return quizRepo.findAll();
	}

	@Override
	public boolean addque(QuestionBind questionBind) {
		Optional<QuizEntity> optionalQuizEntity = quizRepo.findById(questionBind.getQuizId());
		if (!optionalQuizEntity.isPresent()) {
			return false;
		}

		QuizEntity quizEntity = optionalQuizEntity.get();
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setQuestion(questionBind.getQuestion());
		questionEntity.setQuizentity(quizEntity);

		questionRepo.save(questionEntity);

		return true;
	}

	@Override
	public List<QuestionEntity> viewque(Long quizId) {

		Optional<QuizEntity> optional = quizRepo.findById(quizId);

		if (optional.isPresent()) {
			QuizEntity entity = optional.get();
			List<QuestionEntity> allQue = entity.getQuestions();
			return allQue;
		}

		return null;

	}

	@Override
	public boolean addoption(AnswerBind answerBind) {
		Optional<QuestionEntity> answer = questionRepo.findById(answerBind.getQueId());
		if (!answer.isPresent()) {
			return false;
		}
		QuestionEntity questionEntity = answer.get();
		AnswerEntity answerEntity = new AnswerEntity();
		answerEntity.setOptionA(answerBind.getOptionA());
		answerEntity.setOptionB(answerBind.getOptionB());
		answerEntity.setOptionC(answerBind.getOptionC());
		answerEntity.setOptionD(answerBind.getOptionD());
		answerEntity.setAnswer(answerBind.getAnswer());
		answerEntity.setQuestionEntity(questionEntity);
		answerRepo.save(answerEntity);

		return true;
	}

	@Override
	public boolean deleteque(Long queId) {
		questionRepo.deleteById(queId);
		return true;
	}

	@Override
	public boolean deletequiz(Long quizId) {
		quizRepo.deleteById(quizId);
		return true;
	}

	@Override
	public QuizEntity editquiz(Long quizId) {
		return quizRepo.findByQuizId(quizId);

	}

	@Override
	public boolean editOrSave(QuizEntity entity) {
		quizRepo.save(entity);
		return true;
	}

	@Override
	public boolean checkanswer(CorrectAnswerBind answerBind) {

		Integer count = 0;
		Integer rightcount = 0;
		Integer wrongcount = 0;

		if (answerBind.getSelectedOption().equals(answerBind.getRightanswer())) {
			
			Integer userId=(Integer)session.getAttribute("userId");
			
			Optional<UserEntity> user=userRepo.findById(userId);
			if(user.isPresent()){
				
				UserEntity get=user.get();
				ScoreEntity scoreEntity=new ScoreEntity();
				scoreEntity.setUserId(get);
				scoreEntity.setRight("RIGHT");
				scoreRepo.save(scoreEntity);
			}
			
			return true;
		}
		
		Integer userId=(Integer)session.getAttribute("userId");
		
		Optional<UserEntity> user=userRepo.findById(userId);
		if(user.isPresent()){
			
			UserEntity get=user.get();
			ScoreEntity scoreEntity=new ScoreEntity();
			scoreEntity.setUserId(get);
			scoreEntity.setWrong("WRONG");
			scoreRepo.save(scoreEntity);
		}
		
		return false;
	}
	
	@Override
	public ScoreBind getscore() {
		ScoreBind bind=new ScoreBind();
		Integer userId=(Integer)session.getAttribute("userId");
		Optional<UserEntity> user=userRepo.findById(userId);
		if(user.isPresent()){
			
			UserEntity entity=user.get();
			List<ScoreEntity> allscore=entity.getScoreEntities();
			
			Integer attened = allscore.size();
	        Integer right = 0;
	        Integer wrong = 0;
	        
	        for(ScoreEntity e : allscore) {
	        	
	        	if("RIGHT".equals(e.getRight())){
	        		right++;
	        	}else if("WRONG".equals(e.getWrong())) {
	        		wrong++;
	        	}
	        }
	        
	        
	        bind.setAttened(attened);
	        bind.setRight(right);
	        bind.setWrong(wrong);
		}else {
			 bind.setAttened(0);
		        bind.setRight(0);
		        bind.setWrong(0);
		}
		return bind;
	}

}
