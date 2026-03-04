package com.company.edu.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.company.edu.entity.Registration;

public interface RegistrationService {

            Page<Registration> GetAll(Pageable pageable);
            
            Page<Registration> findByStatus(String status, Pageable pageable);
            
		    Registration save(Registration registration);

		    Registration findById(Long id);
		    
		    void DeleteById(Long id);
		    
		    public void approve(Long id);
		    
		    void updateStatus(Long id, String status, String note);
}
