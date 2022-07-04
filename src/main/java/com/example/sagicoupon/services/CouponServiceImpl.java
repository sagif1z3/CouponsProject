package com.example.sagicoupon.services;

import com.example.sagicoupon.model.Coupon;
import com.example.sagicoupon.converters.CouponToCouponDtoConverter;
import com.example.sagicoupon.converters.CouponDtoToCouponConverter;
import com.example.sagicoupon.repositories.CouponRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@NoArgsConstructor
public class CouponServiceImpl implements CouponService {

        private CouponRepository couponRepository;
        private CouponToCouponDtoConverter couponToCouponDtoConverter;
        private CouponDtoToCouponConverter couponDtoToCouponConverter;

        @Autowired
    public CouponServiceImpl(CouponRepository couponRepository,
                             @Lazy CouponToCouponDtoConverter couponToCouponDtoConverter,
                             @Lazy CouponDtoToCouponConverter couponDtoToCouponConverter) {
        this.couponRepository = couponRepository;
        this.couponToCouponDtoConverter = couponToCouponDtoConverter;
        this.couponDtoToCouponConverter = couponDtoToCouponConverter;
    }

    public Coupon addCoupon(Coupon coupon) {
        return Optional.ofNullable(couponToCouponDtoConverter.convert(coupon))
                .map(couponRepository::save)
                .map(couponDtoToCouponConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save coupon"));
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll()
                .stream()
                .map(couponDtoToCouponConverter::convert)
                .collect(Collectors.toList());
    }

    public Coupon findCouponById(Long id) {
        return couponRepository.findById(id)
                .map(couponDtoToCouponConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find coupon by id"));
    }

    public Coupon updateCoupon(Coupon coupon) {
        Coupon existingCoupon = null;
        try {
            existingCoupon = findCouponById(coupon.getId());
        } catch (RuntimeException e) {
            throw new RuntimeException("Could not update coupon because coupon not found ");
        }
        return Optional.ofNullable(couponToCouponDtoConverter.convert(existingCoupon))
                .map(couponRepository::save)
                .map(couponDtoToCouponConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot update coupon"));
    }

    public void deleteCouponById(Long id) {
        Optional.ofNullable(couponToCouponDtoConverter.convert(findCouponById(id)))
                .ifPresent(couponRepository::delete);
    }
}
