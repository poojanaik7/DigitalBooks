package com.digitalbooks.repository;

import com.digitalbooks.entity.BRole;
import com.digitalbooks.entity.BookRole;
import com.digitalbooks.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	public List<Payment> findByUserUserId(Integer userId);

}
