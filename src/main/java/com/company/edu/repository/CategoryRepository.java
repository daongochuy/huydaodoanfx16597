package com.company.edu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.edu.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

	List<Category> findByStatus(String status);

	@Query("""
			    SELECT DISTINCT c
			    FROM Category c
			    LEFT JOIN FETCH c.programs p
			    WHERE c.status = 'ACTIVE'
			    AND p.status = 'OPEN'
			""")
	List<Category> findAllWithPrograms();

	@Query("""
			    SELECT c
			    FROM Category c
			    WHERE c.type = :type
			    
			""")
	List<Category> findByType(@Param("type")String type);
}
