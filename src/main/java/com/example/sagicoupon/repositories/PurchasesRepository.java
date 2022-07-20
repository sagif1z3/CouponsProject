package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.PurchaseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasesRepository extends JpaRepository<PurchaseDto, Long> {

}
