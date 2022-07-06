package com.example.sagicoupon.services;

import com.example.sagicoupon.converters.PurchasesDtoToPurchasesConverter;
import com.example.sagicoupon.converters.PurchasesToPurchasesDtoConverter;
import com.example.sagicoupon.model.Purchase;
import com.example.sagicoupon.repositories.PurchasesRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private PurchasesRepository purchasesRepository;
    private PurchasesToPurchasesDtoConverter purchasesToPurchasesDtoConverter;
    private PurchasesDtoToPurchasesConverter purchasesDtoToPurchasesConverter;

    @Autowired
    public PurchaseServiceImpl(PurchasesRepository purchasesRepository,
                               @Lazy PurchasesToPurchasesDtoConverter purchasesToPurchasesDtoConverter,
                               @Lazy PurchasesDtoToPurchasesConverter purchasesDtoToPurchasesConverter) {
        this.purchasesRepository = purchasesRepository;
        this.purchasesToPurchasesDtoConverter = purchasesToPurchasesDtoConverter;
        this.purchasesDtoToPurchasesConverter = purchasesDtoToPurchasesConverter;
    }

    public Purchase addPurchase(Purchase purchase) {
        return Optional.ofNullable(purchasesToPurchasesDtoConverter.convert(purchase))
                .map(purchasesRepository::save)
                .map(purchasesDtoToPurchasesConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save purchase"));
    }

    public List<Purchase> getAllPurchases() {
        return purchasesRepository.findAll()
                .stream()
                .map(purchasesDtoToPurchasesConverter::convert)
                .collect(Collectors.toList());
    }

    public Purchase findPurchaseById(Long id) {
        return purchasesRepository.findById(id)
                .map(purchasesDtoToPurchasesConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find purchase by id"));
    }

    public Purchase updatePurchase(Purchase purchase) {
        Purchase existingPurchase = null;
        try {
            existingPurchase = findPurchaseById(purchase.getId());
        } catch (RuntimeException e) {
            throw new RuntimeException("Could not update purchase because purchase not found ");
        }
        return Optional.ofNullable(purchasesToPurchasesDtoConverter.convert(existingPurchase))
                .map(purchasesRepository::save)
                .map(purchasesDtoToPurchasesConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot update purchase"));
    }

    public void deletePurchaseById(Long id) {
        Optional.ofNullable(purchasesToPurchasesDtoConverter.convert(findPurchaseById(id)))
                .ifPresent(purchasesRepository::delete);
    }
}
