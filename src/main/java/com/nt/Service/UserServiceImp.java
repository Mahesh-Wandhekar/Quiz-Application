package com.nt.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Entity.AnswerEntity;
import com.nt.Entity.QuestionEntity;
import com.nt.Entity.QuizEntity;
import com.nt.Entity.UserEntity;
import com.nt.UserRepo.AnswerRepo;
import com.nt.UserRepo.QuestionRepo;
import com.nt.UserRepo.QuizRepo;
import com.nt.UserRepo.UserRepo;
import com.nt.binding.LoginBind;
import com.nt.binding.userBind;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private QuizRepo quizRepo;

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private AnswerRepo answerRepo;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean register(userBind bind) throws Exception {
		UserEntity userentity = repo.findByEmail(bind.getEmail());

		if (userentity != null) {
			return false;
		}

		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(bind, user);
		String pwd = bind.getPassword();

		MessageDigest msgdigest = MessageDigest.getInstance("SHA-256");
		msgdigest.reset();
		msgdigest.update(pwd.getBytes());
		byte[] pwd1 = msgdigest.digest();
		byte[] ecodepwd = Base64.getEncoder().encode(pwd1);
		user.setPassword(new String(ecodepwd));
		user.setRole("USER");
		repo.save(user);
		return true;

	}

	@Override
	public boolean login(LoginBind bind) throws Exception {

		String pwd = bind.getPassword();
		MessageDigest msgdigest = MessageDigest.getInstance("SHA-256");
		msgdigest.reset();
		msgdigest.update(pwd.getBytes());
		byte[] msgpwd = msgdigest.digest();
		byte[] encodepwd = Base64.getEncoder().encode(msgpwd);

		UserEntity user = repo.findByEmailAndPassword(bind.getEmail(), new String(encodepwd));

		if (user == null) {
			return false;
		}
		session.setAttribute("userId", user.getId());
		return true;
	}

	@Override
	public List<QuizEntity> userDashboard() {
		return quizRepo.findAll();

	}

	@Override
	public List<AnswerEntity> showoption(Long queId) {
		Optional<QuestionEntity> optional = questionRepo.findById(queId);
		if (optional.isPresent()) {
			QuestionEntity questionEntity = optional.get();
			List<AnswerEntity> answerEntities = questionEntity.getAnswerEntities();
			
			return answerEntities;

		}

		return null;
	}

	@Override
	public boolean checkAnswer(Long queId, String selectoption) {
		
		return false;
	}
}
