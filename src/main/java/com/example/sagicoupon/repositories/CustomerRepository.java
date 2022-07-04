package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDto, Long> {
}
