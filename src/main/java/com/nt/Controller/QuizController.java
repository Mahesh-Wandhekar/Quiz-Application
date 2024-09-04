package com.nt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.Entity.AnswerEntity;
import com.nt.Entity.QuestionEntity;
import com.nt.Entity.QuizEntity;
import com.nt.Service.QuizService;
import com.nt.Service.UserService;
import com.nt.binding.AnswerBind;
import com.nt.binding.CorrectAnswerBind;
import com.nt.binding.QuestionBind;
import com.nt.binding.QuizBind;
import com.nt.binding.ScoreBind;

@Controller
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private UserService userservice;
	
	private static Long passqueId;
	private static Long passquizId;
	
	@GetMapping("addquiz")
    public String createQuizForm( QuizBind quizBind,Model model) {
        
		model.addAttribute("quizBind", quizBind);
        return "quizform";
    }
	@PostMapping("addquiz")
	public String createQuizFormData( @ModelAttribute("quizBind") QuizBind quizBind,Model model) {
		
		boolean status=quizService.addquiz(quizBind);
		if(status) {
			model.addAttribute("sucMsg", "Quiz Added Successfully..!");
		} else {

			model.addAttribute("errMsg", "Quiz Added Failed..!");
		}
		return "quizform";
	}
	
	@GetMapping("viewquiz")
	public String viewquiz(Model model) {
		
		List<QuizEntity> quizEntities=quizService.viewquiz();
		model.addAttribute("quizs", quizEntities);
		return "viewquiz";
	}
	
	@GetMapping("addque")
	public String addque( @RequestParam("quizId")Long quizId,QuestionBind questionBind,Model model) {
		
		
		questionBind.setQuizId(quizId);
		model.addAttribute("addques", questionBind);
		model.addAttribute("quizId", quizId);
		return "addque";
	}
	
	@PostMapping("addque")
	public String addqueData(@ModelAttribute("addques") QuestionBind questionBind,Model model) {
		boolean status=quizService.addque( questionBind);
		if(status) {
			model.addAttribute("sucMsg", "One Question Added Successfully");
		}else {
		model.addAttribute("errMsg", " Question Added Failed");
		}
		return "addque";
	}
	
	@GetMapping("viewque")
	public String viewquestion(@RequestParam("quizId")Long quizId,Model model) {
		List<QuestionEntity> questionEntities=quizService.viewque(quizId);
		model.addAttribute("questions", questionEntities);
		return "viewque";
	}
	
	
	@GetMapping("addoptions")
	public String addoption(@RequestParam("queId") Long queId, Model model) {
		passqueId=queId;
	    AnswerBind answerBind = new AnswerBind(); // Initialize a new object
	    answerBind.setQueId(queId);
	    model.addAttribute("options", answerBind);
	    model.addAttribute("queId", queId);
	    return "addoption";
	}

	@PostMapping("addoptions")
	public String addoptionData(@ModelAttribute("options") AnswerBind answerBind, Model model) {
		
	    boolean status = quizService.addoption(answerBind);
	    if (status) {
	        model.addAttribute("sucMsg", "Options Added Successfully..!");
	    } else {
	        model.addAttribute("sucMsg", "Failed to Add Options..!");
	    }
	    // Return to the form with existing data
	    model.addAttribute("options", answerBind);
	    return "addoption";
	}

	
	@GetMapping("deleteque")
	public String deleteQuestion(@RequestParam("queId") Long queId,Model model) {
		
		boolean status=quizService.deleteque(queId);
		if(status) {
			model.addAttribute("sucMsg"," Question Deleted  Successfully" );
		}else {
			model.addAttribute("errMsg"," Question Deleted  Failed" );
			
		}
		return "redirect:/viewquiz";
	}
	
	@GetMapping("deletequiz")
	public String deleteQuiz(@RequestParam("quizId") Long quizId,Model model) {
		
		boolean status=quizService.deletequiz(quizId);
		if(status) {
			model.addAttribute("sucMsg"," One Quiz Deleted  Successfully" );
		}else {
			model.addAttribute("errMsg"," Quiz Deleted  Failed" );
			
		}
		return "redirect:/viewquiz";
	}
	
	@GetMapping("editquiz")
	public String editQuiz(@RequestParam("quizId") Long quizId,Model model) {
		QuizEntity quizEntity=quizService.editquiz(quizId);
		model.addAttribute("quizId", quizId);
		model.addAttribute("quiz",quizEntity );
		return "editquiz";
	}
	@PostMapping("editquiz")
	public String editorSaveQuiz(@ModelAttribute("quiz") QuizEntity entity,Model model) {
		boolean status=quizService.editOrSave(entity);
		System.out.println(entity.getQuizId());
		if(status) {
			model.addAttribute("sucMsg", "Quiz Updated...!");
		}else {
			model.addAttribute("srrMsg", "Quiz Updated failed please Try ...!");
			
		}
		
		return "editquiz";
	}
	
	
	
	@GetMapping("start-quiz")
	public String startquiz(@RequestParam("quizId")Long quizId,Model model) {
		passquizId=quizId;
		List<QuestionEntity> questionEntities=quizService.viewque(quizId);
		model.addAttribute("questions", questionEntities);
		return "start-quiz";
	}
	
	@GetMapping("showoption")
	public String showOption(@RequestParam("queId") Long queId, Model model) {
		
	    
		List<AnswerEntity> answerEntities = userservice.showoption(queId);
	    
		model.addAttribute("answer", new CorrectAnswerBind()); // Create a new instance of CorrectAnswerBind
	    model.addAttribute("options", answerEntities);
	    model.addAttribute("queId", queId);
	    model.addAttribute("quizId", passquizId);
	    return "showoption";
	}
	
	@PostMapping("checkanswer")
	public String checkAnswer(@ModelAttribute("answer") CorrectAnswerBind answerBind, Model model) {
	boolean status=quizService.checkanswer(answerBind);
	    if(status) {
	    	model.addAttribute("sucMsg", "Right Answer");
	    	model.addAttribute("smsg", " You Are Selected Right Answer please Go With Next Question");
	    }else {
	    	model.addAttribute("errMsg", "Wrong Answer");
	    	model.addAttribute("emsg", "You Are Selected Wrong Answer Please Try Again");
	    	
	    }
	   
	    model.addAttribute("queId", passqueId);
	    model.addAttribute("quizId", passquizId);
		   return "result";
	}
	
	@GetMapping("score")
	public String score(Model model) {
		ScoreBind bind=quizService.getscore();
		model.addAttribute("score", bind);
		return "score1";
	}
}
