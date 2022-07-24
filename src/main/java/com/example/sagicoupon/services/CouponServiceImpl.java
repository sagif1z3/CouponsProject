package com.example.sagicoupon.services;

import com.example.sagicoupon.dto.CouponDto;
import com.example.sagicoupon.model.Coupon;
import com.example.sagicoupon.converters.CouponToCouponDtoConverter;
import com.example.sagicoupon.converters.CouponDtoToCouponConverter;
import com.example.sagicoupon.repositories.CouponRepository;
import com.example.sagicoupon.validators.CouponValidators;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {

    private CouponRepository couponRepository;
    private CouponValidators couponValidators;
    private CouponToCouponDtoConverter couponToCouponDtoConverter;
    private CouponDtoToCouponConverter couponDtoToCouponConverter;

    @Override
    public Coupon addCoupon(Coupon coupon) {
        return Optional.ofNullable(coupon)
                .filter(couponValidators::addCouponValidation)
                .map(couponToCouponDtoConverter::convertSave)
                .map(couponRepository::save)
                .map(couponDtoToCouponConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save coupon"));
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll()
                .stream()
                .map(couponDtoToCouponConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Coupon findCouponById(long id) {
        return couponRepository.findById(id)
                .map(couponDtoToCouponConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find coupon by id"));
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        return Optional.ofNullable(coupon)
                .filter(couponValidators::updateCouponValidation)
                .map(couponToCouponDtoConverter::convertUpdate)
                .map(couponRepository::save)
                .map(couponDtoToCouponConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot update coupon"));
    }

    @Override
    public void deleteCouponById(long id) {
        Optional.ofNullable(findCouponById(id))
                .map(couponToCouponDtoConverter::convertUpdate)
                .ifPresent(couponRepository::delete);
    }

    @Override
    public List<CouponDto> getExpiredCoupons() {
        return couponRepository.getExpiredCoupons();
    }

    @Override
    public CouponDto getCouponDtoById(long id) {
        return couponRepository.findById(id)
                .orElse(null);
    }
}
