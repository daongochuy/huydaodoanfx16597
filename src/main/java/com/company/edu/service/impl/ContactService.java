package com.company.edu.service.impl;
import com.company.edu.entity.Contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {
	Page<Contact> getAll(Pageable pageable);

    Page<Contact> search(String keyword, Pageable pageable);

    Contact save(Contact contact);

    Contact findById(Long id);
    void deleteById(Long id);
}
