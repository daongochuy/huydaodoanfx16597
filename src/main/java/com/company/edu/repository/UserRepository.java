package com.company.edu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.edu.entity.Program;
import com.company.edu.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	public boolean existsByEmail(String email);

	@Query("""
			    SELECT p FROM User p
			    WHERE  p.email LIKE %:keyword%

			""")
	User findByEmail(@Param("keyword") String keyword);
	@Query("""
		    SELECT u FROM User u
		    WHERE u.email = :email AND u.mobileNumber = :mobile
		""")
		User findByEmailAndMobile(@Param("email") String email,
		                                    @Param("mobile") String mobile);

	Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

}
