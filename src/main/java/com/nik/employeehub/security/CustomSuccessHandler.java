package com.nik.employeehub.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.nik.employeehub.enums.EmployeeRole;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		String redirectUrl = "/employee/dashboard";
		
		for(GrantedAuthority auth: authorities) {
			
			if(auth.getAuthority().equals(EmployeeRole.ADMIN.name())) {
				redirectUrl = "/admin/dashboard";
				break;
			}
			else if(auth.getAuthority().equals(EmployeeRole.MANAGER.name())){
				redirectUrl = "/manager/dashboard";
				break;
			}
		}
		response.sendRedirect(redirectUrl);
	}

}
