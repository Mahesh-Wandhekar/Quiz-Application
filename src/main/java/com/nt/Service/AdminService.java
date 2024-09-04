package com.nt.Service;

import org.springframework.stereotype.Service;


import com.nt.binding.LoginBind;


public interface AdminService {

	
	public boolean adminLogin(LoginBind loginbind) throws Exception;
	
}
