package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.CouponDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CouponRepository extends JpaRepository<CouponDto, Long> {
}
