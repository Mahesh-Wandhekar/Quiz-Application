package com.nt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.Service.AdminService;

import com.nt.binding.LoginBind;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("adminLogin")
	public String adminLogin( LoginBind loginbind,Model model) {
	
		model.addAttribute("loginbind", loginbind);
		return "adminLogin";
	}
	
	
	@PostMapping("adminLogin")
	public String adminLoginData( @ModelAttribute("loginbind") LoginBind loginbind,Model model) throws Exception {
		
		boolean status=adminService.adminLogin(loginbind);
		if(status) {
			return "redirect:/viewquiz";
		}else {
			model.addAttribute("errMsg","Invalid Admin Deteils..!" );
		}
		return "adminLogin";
	}
	
	@GetMapping("dashboardAdmin")
	public String adminDashboard(Model model) {
		
		return "dashboardAdmin";
	}

}
