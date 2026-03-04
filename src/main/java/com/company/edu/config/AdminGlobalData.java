package com.company.edu.config;
import org.springframework.beans.factory.annotation.Autowired;
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

}
