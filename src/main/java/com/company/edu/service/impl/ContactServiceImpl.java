package com.company.edu.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.edu.entity.Contact;
import com.company.edu.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{
	@Autowired
	 private  ContactRepository contactRepository ;
	@Override
	public Page<Contact> getAll(Pageable pageable) {
		 return contactRepository.findAll(pageable);
	}

	@Override
	public Page<Contact> search(String keyword, Pageable pageable) {
		  return contactRepository
		            .findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
		                    keyword, keyword, pageable);
	}

	@Override
	public Contact save(Contact contact) {
		 
	        return contactRepository.save(contact);
	}

	@Override
	public Contact findById(Long id) {
		   return contactRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
	
		contactRepository.deleteById(id);
	}

}
