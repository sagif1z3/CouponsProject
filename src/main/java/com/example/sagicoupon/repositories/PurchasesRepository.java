package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.PurchaseDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesRepository extends JpaRepository<PurchaseDto, Long> {
}
