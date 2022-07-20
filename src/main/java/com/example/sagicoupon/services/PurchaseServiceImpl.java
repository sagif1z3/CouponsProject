package com.example.sagicoupon.services;

import com.example.sagicoupon.converters.PurchasesDtoToPurchasesConverter;
import com.example.sagicoupon.converters.PurchasesToPurchasesDtoConverter;
import com.example.sagicoupon.model.Coupon;
import com.example.sagicoupon.model.Purchase;
import com.example.sagicoupon.repositories.PurchasesRepository;
import com.example.sagicoupon.validators.PurchaseValidators;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private CouponService couponService;
    private PurchasesRepository purchasesRepository;
    private PurchaseValidators purchaseValidators;
    private PurchasesToPurchasesDtoConverter purchasesToPurchasesDtoConverter;
    private PurchasesDtoToPurchasesConverter purchasesDtoToPurchasesConverter;

    @Override
    @Transactional
    public Purchase addPurchase(Purchase purchase) {
        Coupon coupon = couponService.findCouponById(purchase.getCouponId());
        Optional.of(purchase)
                .filter(purchaseValidators::addPurchaseValidation)
                .map(purchasesToPurchasesDtoConverter::convertSave)
                .map(purchasesRepository::save)
                .map(purchasesDtoToPurchasesConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save purchase"));

        coupon.setAmount(coupon.getAmount() - purchase.getAmount());
        couponService.updateCoupon(coupon);

        return purchase;
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchasesRepository.findAll()
                .stream()
                .map(purchasesDtoToPurchasesConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Purchase findPurchaseById(Long id) {
        return purchasesRepository.findById(id)
                .map(purchasesDtoToPurchasesConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find purchase by id"));
    }

    @Override
    @Transactional
    public void deletePurchaseById(Long id) {
        Optional.ofNullable(findPurchaseById(id))
                .map(purchasesToPurchasesDtoConverter::convert)
                .ifPresent(purchasesRepository::delete);

        Purchase purchase = findPurchaseById(id);
        Coupon coupon = couponService.findCouponById(purchase.getCouponId());
        coupon.setAmount(coupon.getAmount() + purchase.getAmount());
        couponService.updateCoupon(coupon);
    }
}
