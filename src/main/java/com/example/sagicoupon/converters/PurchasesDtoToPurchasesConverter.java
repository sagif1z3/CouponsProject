package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.PurchaseDto;
import com.example.sagicoupon.model.Purchase;
import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchasesDtoToPurchasesConverter implements Converter<PurchaseDto, Purchase> {

    @Override
    public Purchase convert(@NotNull PurchaseDto source) {
        Purchase purchase = new Purchase();
        purchase.setId(source.getId());
        purchase.setUserId(source.getUserDto().getId());
        purchase.setCouponId(source.getCouponDto().getId());
        purchase.setDate(source.getDate());
        purchase.setAmount(source.getAmount());
        purchase.setTotalPrice(source.getTotalPrice());
        return purchase;
    }
}
