package com.example.sagicoupon.repositories;

import com.example.sagicoupon.dto.CouponDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<CouponDto, Long> {

    @Query(value = "SELECT * FROM coupons c WHERE c.end_date < current_date ",nativeQuery = true)
    List<CouponDto> getExpiredCoupons ();
    
    Optional<CouponDto> findFirstByTitle (String title);

    Optional<CouponDto> findFirstByDescription (String description);

    Optional<CouponDto> findFirstByStartDate (Date startDate);

    Optional<CouponDto> findFirstByAmount (long amount);

    Optional<CouponDto> findFirstByPrice (double price);

    Optional<CouponDto> findFirstByImage (String image);
}
