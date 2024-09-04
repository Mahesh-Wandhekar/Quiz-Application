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
import com.nt.Entity.QuizEntity;
import com.nt.Service.UserService;
import com.nt.binding.CorrectAnswerBind;
import com.nt.binding.LoginBind;
import com.nt.binding.userBind;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private HttpSession session;
	
	
	@GetMapping("register")
	public String register(userBind userbind ,Model model) {
		model.addAttribute("userbind", userbind);
		return "register";
	}
	
	@PostMapping("register")
	public String registerData(@ModelAttribute("userbind") userBind userbind,Model model) throws Exception {
		boolean status=userservice.register(userbind);
		if(status) {
			model.addAttribute("sucMsg", "Register Successfully...Please login..!");
		}else {
			model.addAttribute("errMsg", "Already Used This Email Please User Another Email..!");
			
		}
		
		return "register";
	}
	
	
	@GetMapping("login")
	public String login( LoginBind loginbind,Model model) {
		model.addAttribute("loginbind",loginbind);
		return "login";
	}
	
	@PostMapping("login")
	public String loginData( @ModelAttribute("loginbind") LoginBind loginbind,Model model) throws Exception {
		boolean status=userservice.login(loginbind);
		if(status) {
			
			  return "redirect:/dashboard"; 
			  
		}else {
			model.addAttribute("errMsg", "Invalid Credentials");
		}
		
		return "login";
		
	}
	
	@GetMapping("dashboard")
	public String dashBoard(Model model) {		
		List<QuizEntity> quiz=userservice.userDashboard();
		model.addAttribute("allquiz", quiz);
		return "dashboard";
	}
	
	
	@GetMapping("logout")
	public String userlogout(Model model) {
		session.invalidate();
		return "index";
	}
	

	

	
	
}
