package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.CompanyDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyDto, Long> {
}
