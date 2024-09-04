package com.nt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

	
	@GetMapping("/")
	public String index(Model model) {
		
		return "index";
	} 
	
	
}
