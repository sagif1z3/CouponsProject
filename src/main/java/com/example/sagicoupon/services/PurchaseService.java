package com.example.sagicoupon.services;

import com.example.sagicoupon.model.Purchase;
import java.util.List;

public interface PurchaseService {

    Purchase addPurchase(Purchase purchase);

    List<Purchase> getAllPurchases();

    Purchase findPurchaseById(long id);

    void deletePurchaseById(long id);
}
