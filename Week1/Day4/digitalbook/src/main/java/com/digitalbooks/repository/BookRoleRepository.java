package com.digitalbooks.repository;

import com.digitalbooks.entity.BRole;
import com.digitalbooks.entity.BookRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRoleRepository extends JpaRepository<BookRole, Long> {
	Optional<BookRole> findByName(BRole name);
}
