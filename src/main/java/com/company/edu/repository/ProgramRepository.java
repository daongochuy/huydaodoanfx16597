package com.company.edu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.edu.entity.Program;

public interface ProgramRepository extends JpaRepository<Program, Long> {

	List<Program> findByStatus(String status);

	List<Program> findByCountry(String country);

	List<Program> findByCategory_Id(Long categoryId);

	@Query("""
			    SELECT p FROM Program p
			    WHERE (:keyword IS NULL OR p.title LIKE %:keyword% OR p.jobName LIKE %:keyword%)
			      AND (:country IS NULL OR p.country = :country)
			      AND (:status IS NULL OR p.status = :status)
			""")
	List<Program> search1(@Param("keyword") String keyword, @Param("country") String country,
			@Param("status") String status);

	@Query("""
			    SELECT p FROM Program p
			    WHERE
			        (:keyword IS NULL OR
			         LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
			         LOWER(p.country) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
			         LOWER(p.jobName) LIKE LOWER(CONCAT('%', :keyword, '%'))
			        )
			""")
	Page<Program> searchPage(@Param("keyword") String keyword, Pageable pageable);
	@Query("""
		    SELECT p FROM Program p
		    WHERE
		        (:keyword IS NULL OR
		         LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
		         LOWER(p.country) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
		         LOWER(p.jobName) LIKE LOWER(CONCAT('%', :keyword, '%'))
		        )
		""")
	List<Program> search(@Param("keyword") String keyword);
	@Query("""
			   SELECT p
			   FROM Program p
			   JOIN FETCH p.category c
			   WHERE p.status = 'OPEN'
			   AND c.status = 'ACTIVE'
			""")
			List<Program> findActivePrograms();
	Page<Program> findByCategoryId(Long categoryId,Pageable pageable);
}
