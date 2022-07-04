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
        Purchase purchaseDto = new Purchase();
        purchaseDto.setId(source.getId());
        purchaseDto.setCouponTitle(source.getCouponTitle());
        purchaseDto.setDate(source.getDate());
        purchaseDto.setAmount(source.getAmount());
        purchaseDto.setPrice(source.getPrice());
        purchaseDto.setTotalPrice(source.getTotalPrice());
        return purchaseDto;
    }
}
