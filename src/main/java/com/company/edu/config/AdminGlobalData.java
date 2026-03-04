package com.company.edu.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.company.edu.repository.RegistrationRepository;
@ControllerAdvice(basePackages = "com.company.edu.controller.admin")
public class AdminGlobalData {
	 @Autowired
	    private RegistrationRepository registrationRepository;

	    @ModelAttribute("pendingRegistrationCount")
	    public long pendingRegistrationCount() {
	        return registrationRepository.countByStatus("PENDING");
	    }
	    @ModelAttribute("currentUser")
	    public String getCurrentUser() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	        if (auth != null && auth.isAuthenticated() 
	            && !"anonymousUser".equals(auth.getPrincipal())) {
	            return auth.getName(); // username
	        }

	        return null;
	    }

}
